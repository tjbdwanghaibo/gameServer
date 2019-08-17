package org.jow.centralsrv.mian;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jow.centralsrv.CommonSerializer;
import org.jow.centralsrv.human.HumanGlobalService;
import org.jow.centralsrv.support.Log;
import org.jow.common.DefaultPort;
import org.jow.common.db.InitFieldTable;
import org.jow.common.entity.DBReferenceTable;
import org.jow.common.msg.MsgSerializer;
import org.jow.core.Node;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.ReferenceTable;
import org.jow.core.config.CentralConfig;
import org.jow.core.config.DBConfig;
import org.jow.core.config.JowDistr;
import org.jow.dbsrv.main.DBStartup;

public class CentralStartup {

	public static void main(String[] args) {

		System.setProperty("logFileName", CentralConfig.NODE_CENTRAL);

		Log.central.info("========初始化环境=========");

		MsgSerializer.init();
		CommonSerializer.init();
		ReferenceTable.INSTANCE = new DBReferenceTable();

		// 创建Node
		Log.central.info("========创建Node=========");
		JowDistr.Central central = JowDistr.centralConfig;
		String centralNodeAddr = central.nodeAddr;
		Node centralNode = new Node(CentralConfig.NODE_CENTRAL, centralNodeAddr);

		// 连接到本区db服务器
		Log.central.info("======连接到本区DB服务器======");
		DBConfig.init(CentralConfig.NODE_CENTRAL, central.dbConfig);
		DBStartup.startup(centralNode);

		// 延迟1秒，在继续初始化
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Log.central.error(ExceptionUtils.getStackTrace(e));
		}

		// 创建个临时Port
		DefaultPort portDef = new DefaultPort(JowDistr.PORT_DEFAULT);
		portDef.startup(centralNode);

		Log.central.info("======加载系统数据======");
		// 加载系统数据
		final InitFieldTable servInitData = new InitFieldTable();
		portDef.addTask(new PortTask() {
			@Override
			public void execute(Port port) {
				servInitData.init();
			}
		});

		// 等待加载完成
		while (!servInitData.isCompleted()) {
			try {
				Thread.sleep(10);
				centralNode.pulse();
			} catch (InterruptedException e) {
				Log.central.error(ExceptionUtils.getStackTrace(e));
			}
		}

		// 启动全局玩家服务
		Port humanGlobalPort = new Port(CentralConfig.PORT_HUMAN_GLOBAL);
		humanGlobalPort.startup(centralNode);
		HumanGlobalService humanGlobalService = new HumanGlobalService(humanGlobalPort);
		humanGlobalService.startup();
		humanGlobalPort.addService(humanGlobalService);

		// Node正式启动
		Log.central.info("======Node正式启动======,NodeId = {}",centralNode.getNodeId());
		centralNode.startup();

		// 启动日志信息
		Log.central.info("====================");
		Log.central.info(CentralConfig.NODE_CENTRAL + " started.");
		Log.central.info("Listen:" + centralNodeAddr);
		Log.central.info("====================");

		// 系统关闭时进行清理
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				Log.central.info("======服务器关闭处理中========");

				try {
					// 通知立即刷新缓存服务器
					DBStartup.flushAll(centralNode);
					// 再等待2秒 持久化数据
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
