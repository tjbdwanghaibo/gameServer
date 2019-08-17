package org.jow.main;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jow.centralsrv.human.HumanGlobalService;
import org.jow.common.DefaultPort;
import org.jow.common.db.InitFieldTable;
import org.jow.common.entity.DBReferenceTable;
import org.jow.common.event.Event;
import org.jow.common.msg.MsgSerializer;
import org.jow.connsrv.ConnService;
import org.jow.connsrv.b;
import org.jow.connsrv.netty.c;
import org.jow.core.Node;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.ReferenceTable;
import org.jow.core.config.CentralConfig;
import org.jow.core.config.ConnConfig;
import org.jow.core.config.GameConfig;
import org.jow.core.config.JowConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.config.LoginConfig;
import org.jow.core.config.JowDistr.Game;
import org.jow.core.support.observer.MsgSender;
import org.jow.dbsrv.main.DBStartup;
import org.jow.gamesrv.GamePort;
import org.jow.gamesrv.GameService;
import org.jow.gamesrv.manager.GameManagerService;
import org.jow.mian.support.Log;

/**
 * 单进程
 * 
 * @author Yangxianmeng
 *
 */
public class AllStartup {

	public static void main(String[] args) {
		if (args == null || args.length < 2) {
			args = new String[2];
			args[0] = "MainStartup";
			args[1] = "127.0.0.1";
		}
		// 设置文件路径
		JowConfig.RES_PATH = "../data/";
		if (args.length > 0) {
			JowConfig.RES_PATH = args[0];
		}
		// 设置日志文件名
		System.setProperty("logFileName", "main");

		org.jow.loginsrv.MsgReceiverInit.init(MsgSender.instance);
		MsgSerializer.init();
		org.jow.loginsrv.CommonSerializer.init();
		org.jow.centralsrv.CommonSerializer.init();
		ReferenceTable.INSTANCE = new DBReferenceTable();

		Log.mian.info("======连接到本区DB服务器=======");
		Node node = new Node("MainStartup","127.0.0.1");
//		JowDistr.MainTest mainTest = JowDistr.mainTestConfig;
//		DBConfig.init(node.getNodeId(), mainTest.dbConfig);
		DBStartup.startup(node);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Log.mian.error(ExceptionUtils.getStackTrace(e));
		}

		// 创建临时的Port
		DefaultPort portDef = new DefaultPort(JowDistr.PORT_DEFAULT);
		portDef.startup(node);

		Log.mian.info("=========加载系统数据=========");
		final InitFieldTable serInitData = new InitFieldTable();
		portDef.addTask(new PortTask() {
			@Override
			public void execute(Port port) {
				serInitData.init();
			}
		});

		// 等待加载完成
		while (!serInitData.isCompleted()) {
			try {
				Thread.sleep(10);
				node.pulse();
			} catch (InterruptedException e) {
				Log.mian.error(ExceptionUtils.getStackTrace(e));
			}
		}

		Log.mian.info("=======系统数据加载完成======");

		connStartup(node);

		loginStartup(node);

		centralStartup(node);

		gameStartup(node);

		Log.mian.info("======Node正式启动======");
		node.startup();

		// 系统关闭时进行清理
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				Log.mian.info("======服务器关闭处理中========");
				try {
					// 通知立即刷新缓存服务器
					DBStartup.flushAll(node);
					// 再等待2秒 持久化数据
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

	}

	private static void gameStartup(Node node) {
		int gameIndex = 0;
		// 初始化配置
		GameConfig.init(gameIndex);
		Game game = JowDistr.gameConfigs.get(gameIndex);
		// String gameNodeId = GameConfig.getGameNodeId(gameIndex);

		org.jow.gamesrv.MsgReceiverInit.init(MsgSender.instance);
		org.jow.gamesrv.ListenerInit.init(Event.instance);
		org.jow.gamesrv.CommonSerializer.init();

		// 创建一个临时Port
		DefaultPort portDef = new DefaultPort(JowDistr.PORT_DEFAULT);
		portDef.startup(node);

		// 启动游戏管理服务
		GameManagerService gameManagerService = new GameManagerService(portDef);
		gameManagerService.startup();
		portDef.addService(gameManagerService);

		// 启动全部游戏逻辑服务
		for (int i = 0; i < game.portStartupNumGame; ++i) {
			GamePort gamePort = new GamePort(GameConfig.PORT_GAME_PERFIX + i);
			gamePort.startup(node);
			GameService gameService = new GameService(gamePort);
			gameService.startup();
			gamePort.addService(gameService);
		}

		// 启动游戏业务服务
		Port workPort = new Port(GameConfig.PORT_GAME_WORK);
		workPort.startup(node);

		Log.mian.info("======游戏服启动成功======");
	}

	private static void connStartup(Node node) {
		// 初始化配置
		ConnConfig.init(0);
		// 启动Port
		int portStartupNumConn = ConnConfig.getPortStartupNumConn();
		for (int i = 0; i < portStartupNumConn; i++) {
			Port port = new b(ConnConfig.PORT_CONN_PREFIX + i);
			port.startup(node);

			// 每一个Port启动一个默认的Service
			ConnService connService = new ConnService(port);
			connService.startup();
			port.addService(connService);
		}
//		new Server(node).start();
		new c().start();

		Log.mian.info("======连接服启动成功======");
	}

	private static void centralStartup(Node node) {
		// 启动全局玩家服务
		Port humanGlobalPort = new Port(CentralConfig.PORT_HUMAN_GLOBAL);
		humanGlobalPort.startup(node);
		HumanGlobalService humanGlobalService = new HumanGlobalService(humanGlobalPort);
		humanGlobalService.startup();
		humanGlobalPort.addService(humanGlobalService);
		// 启动日志信息
		Log.mian.info("======中心服启动成功======");
	}

	/**
	 * 启动登陆服
	 * 
	 * @param node
	 */
	private static void loginStartup(Node node) {
		// 启动账号登陆服务
		org.jow.loginsrv.AccountPort accountPort = new org.jow.loginsrv.AccountPort(LoginConfig.PORT_ACCOUNT);
		accountPort.startup(node);
		
		org.jow.loginsrv.AccountService accountService = new org.jow.loginsrv.AccountService(accountPort);
		accountService.startup();
		accountPort.addService(accountService);
		// 启动日志信息
		Log.mian.info("======登陆服启动成功=======");
	}

}
