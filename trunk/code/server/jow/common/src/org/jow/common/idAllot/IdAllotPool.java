package org.jow.common.idAllot;

import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.config.DBConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.support.Param;
import org.jow.core.support.log.LogCore;
import org.jow.dbsrv.RPCProxy.IdAllotServiceProxy;

/**
 * 
 * @author Yangxianmeng
 *
 *         UUID池，用来分配UUID 此类不是线程安全的
 * 
 *         UUID 用long型，约定UUID是非负数 这样去掉符号位后，还有63bit
 * 
 *         2^63 = 92 2337 2036854775808 10进制一共是19位 这里将19位做一个合理的划分： 18-19 ：
 *         平台ID，有效值0-91 14-17 ： 服务器ID,有效值0-9999 1-13 ：
 *         服务器逻辑ID,有效值0-9999999999999 通过这样的划分，将平台和服务器ID加入UUID中，使得UUID是全局唯一的，方便合服
 * 
 *         逻辑ID最大值9999999999999，十进制十三位，假设一台服务器运营五年 每秒分配的Id数 = 9999999999999 /（60
 *         * 60 * 24 * 365 * 5） = 63419 六万多，足够用
 */
public class IdAllotPool {
	/** 表示ID的位数 */
	public static final int ID_DECIMAL_NUM = 13;
	/** 表示server的位数 */
	public static final int SERVER_DECIMAL_NUM = 4;

	// 每次申请的最大值
	private static final int MAX_CACHE = 10000;
	// 剩余警戒值 小于该值就立刻申请
	private static final int LOW_WATER_MARK = 5000;

	// 服务器Id，有效值0-9999
	private long serverId;

	// 当前id池
	private long currentIDBegin = 0;
	private long currentIDEnd = -1;

	// 备用id池
	private long backupIDBegin = 0;
	private long backupIDEnd = -1;

	// 是否正在分配ID
	private boolean applying = false;

	// 所属port
	private Port port;

	public IdAllotPool(Port port) {
		this.port = port;
		this.serverId = JowDistr.SERVER_INDEX * (long) Math.pow(10, ID_DECIMAL_NUM);
		port.addTask(new PortTask() {

			@Override
			public void execute(Port arg0) {
				// Port 启动时同步申请一次id
				applySync();
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
	 * 分配一个UUID
	 * 
	 * @return
	 */
	public long applyId() {
		if (currentIDBegin > currentIDEnd) {
			loadBackupPool();
			if (currentIDBegin > currentIDEnd) {
				LogCore.core.error("ID allocte failed, pool is empty, thread will block, port={}", port.getId(),
						new Throwable());
				applying = false;
				// 正常id池数量小于警戒值时就会自动申请id
				// 此处无id可分配也许是申请结果还没有返回
				// 这时没有别的办法，只能同步等待
				applySync();
				loadBackupPool();
			}

			return applyId();
		}

		// 小于警戒值，申请新ID
		if ((currentIDEnd - currentIDBegin < LOW_WATER_MARK) && !applying) {
			applyAsyn();
		}

		// 获取id，并加上平台和服务器标识
		long id = currentIDBegin++;
		return serverId + id;
	}

	private void applySync() {
		// rpc去dbsever申请可用id
		IdAllotServiceProxy prx = IdAllotServiceProxy.newInstance(DBConfig.NODE_DB, DBConfig.PORT_ID_ALLOT,
				DBConfig.SERV_ID_ALLOT);
		prx.apply(MAX_CACHE * 20);
		Param results = prx.waitForResult();

		// 分配到的ID范围
		backupIDBegin = results.get("begin");
		backupIDEnd = results.get("end");

		// 日志
		LogCore.core.info("ID apply sync successed, port={}, begin={}, end={}", port.getId(), backupIDBegin,
				backupIDEnd);
	}

	/**
	 * 异步申请id
	 */
	private void applyAsyn() {
		// 设置申请状态
		applying = true;

		IdAllotServiceProxy prx = IdAllotServiceProxy.newInstance(DBConfig.NODE_DB, DBConfig.PORT_ID_ALLOT,
				DBConfig.SERV_ID_ALLOT);
		prx.apply(MAX_CACHE);
		prx.listenResult(this::_result_applyAsyn);

		// 日志
		LogCore.core.info("ID apply async start, port={}", port.getId());
	}

	public void _result_applyAsyn(boolean timeout, Param results, Param context) {
		// 设置申请状态
		applying = false;

		if (timeout) {
			LogCore.core.info("ID apply async timeout, port={}", port.getId());
			return;
		}

		// 分配到的id范围
		backupIDBegin = results.get("begin");
		backupIDEnd = results.get("end");

		// 日志
		LogCore.core.info("ID apply async successed, port={}, begin={}, end={}", port.getId(), backupIDBegin,
				backupIDEnd);
	}
}
