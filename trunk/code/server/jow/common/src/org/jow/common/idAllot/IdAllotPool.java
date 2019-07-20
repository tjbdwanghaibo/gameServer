package org.jow.common.idAllot;

import org.jow.core.Parms;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.config.DBConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.support.log.LogCore;
import org.jow.dbsrv.RPCProxy.IdAllotServiceProxy;

/**
 * UUID池, 用来分配UUID 此类不是线程安全的
 * 
 * UUID用long型，约定UUID是非负数，这样去掉符号位还有 63bit; 2^63 = 92 2337 2036854775808 十进制一共十九位
 * 这里将十九位做一个合理的分配： 18-19：平台Id，有效值0-99 14-17：服务器Id，有效值0-9999
 * 1-13：逻辑Id，有效值0-9999999999999； 通过这样的划分,将平台和服务器Id加入UUID中，使得UUID是全局唯一的，方便合服
 * 
 * 逻辑Id最大位9999999999999,假设一个服务器运营5年，计算一下每秒分配的Id数 9999999999999/(365 * 24 * 60 *
 * 5) = 63419 6万多，绰绰有余。
 * 
 * @author Yangxianmeng
 * 
 */
public class IdAllotPool {

	/** 表示id的位数(十进制的位数) */
	public static final int ID_DECIMAL_NUM = 13;
	/** 表示server的位数(十进制的位数) */
	public static final int SERVER_DECIMAL_NUM = 4;

	// 每次申请的数量
	private static final int MAX_CACHE = 10000;
	// 剩余警戒值，小于该值立刻申请
	private static final int LOW_WATER_MARK = 5000;

	// 服务器id，0-9999
	private long serverId;

	// 当前id池
	private long currentIDBegin = 0;
	private long currentIDEnd = -1;

	// 备用id池
	private long backupIDBegin = 0;
	private long backupIDEnd = -1;

	// 是否正在分配Id
	private boolean applying = false;

	// 所属port
	private Port port;

	public IdAllotPool(Port port) {
		this.port = port;
		this.serverId = JowDistr.SERVER_INDEX * (long) Math.pow(10, ID_DECIMAL_NUM);

		// 初始化id池
		port.addTask(new PortTask() {
			@Override
			public void execute(Port port) {
				// port 启动时，同步申请一次
				applySyn();
				loadBackupPool();
			}
		});
	}

	protected void loadBackupPool() {
		currentIDBegin = backupIDBegin;
		currentIDEnd = backupIDEnd;

		backupIDBegin = 0;
		backupIDEnd = -1;
	}

	/**
	 * 同步申请id
	 */
	private void applySyn() {
		// rpc去dbServer申请可用id
		IdAllotServiceProxy proxy = IdAllotServiceProxy.newInstance(DBConfig.NODE_DB, DBConfig.PORT_ID_ALLOT,
				DBConfig.SERV_ID_ALLOT);
		proxy.apply(MAX_CACHE * 20);
		Parms results = proxy.waitForResult();

		// 分配到的Id范围
		backupIDBegin = results.get("begin");
		backupIDEnd = results.get("end");

		// 日志
		LogCore.core.info("ID apply sync successed, port={}, begin={}, end={}", port.getPortId(), backupIDBegin,
				backupIDEnd);
	}

	/**
	 * 异步申请id
	 */
	private void applyAsyn() {
		// 设置申请状态
		applying = true;
		IdAllotServiceProxy proxy = IdAllotServiceProxy.newInstance(DBConfig.NODE_DB, DBConfig.PORT_ID_ALLOT,
				DBConfig.SERV_ID_ALLOT);
		proxy.apply(MAX_CACHE);
		proxy.listenResult(this::_result_applyAsyn);

		// 日志
		LogCore.core.info("ID apply async start, port={}", port.getPortId());
	}

	private void _result_applyAsyn(boolean timeOut, Parms results, Parms context) {
		// 设置申请状态
		applying = false;

		if (timeOut) {
			LogCore.core.info("Id apply asyn timeout, port = {}", port.getPortId());
			return;
		}

		// 分配到的Id范围
		backupIDBegin = results.get("begin");
		backupIDEnd = results.get("end");

		// 日志
		LogCore.core.info("ID apply asyn successed, port={}, begin={}, end={}", port.getPortId(), backupIDBegin,
				backupIDEnd);

	}

	/**
	 * 分配一个UUID
	 * 
	 * @return
	 */
	public long applyId() {
		if (currentIDBegin > currentIDEnd) {
			loadBackupPool();
			if (currentIDBegin > currentIDEnd) {
				LogCore.core.error("Id allocte feild, pool is empty, thread will block, port = {}", port.getPortId(),
						new Throwable());
				applying = false;
				// 正常Id池数量小于警戒值时就会自动申请id
				// 此处无id可分配也许是申请结果还没有返回
				// 这时没有别的办法，只能同步等待
				applySyn();
				loadBackupPool();
			}
			return applyId();
		}

		//小于警戒值，申请新的Id
		if((currentIDEnd - currentIDBegin < LOW_WATER_MARK) && !applying) {
			applyAsyn();
		}
		
		//获取Id，加上平台和服务器标识
		long id = currentIDBegin ++ ;
		return serverId + id;
	}

}
