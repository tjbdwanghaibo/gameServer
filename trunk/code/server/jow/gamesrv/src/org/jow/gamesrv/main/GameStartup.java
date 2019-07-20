package org.jow.gamesrv.main;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jow.common.DefaultPort;
import org.jow.common.db.InitFieldTable;
import org.jow.common.entity.DBReferenceTable;
import org.jow.common.event.Event;
import org.jow.common.msg.MsgSerializer;
import org.jow.core.Node;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.ReferenceTable;
import org.jow.core.config.CentralConfig;
import org.jow.core.config.ConnConfig;
import org.jow.core.config.DBConfig;
import org.jow.core.config.GameConfig;
import org.jow.core.config.JowConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.config.JowDistr.Conn;
import org.jow.core.config.JowDistr.Game;
import org.jow.core.config.JowDistr.Room;
import org.jow.core.config.LoginConfig;
import org.jow.core.config.MatchConfig;
import org.jow.core.config.RoomConfig;
import org.jow.core.support.Utils;
import org.jow.core.support.observer.MsgSender;
import org.jow.dbsrv.main.DBStartup;
import org.jow.gamesrv.CommonSerializer;
import org.jow.gamesrv.GamePort;
import org.jow.gamesrv.GameService;
import org.jow.gamesrv.ListenerInit;
import org.jow.gamesrv.MsgReceiverInit;
import org.jow.gamesrv.manager.GameManagerService;
import org.jow.gamesrv.support.Log;

/**
 * 逻辑服
 * @author Yangxianmeng
 *
 */
public class GameStartup {
	
	public static void main(String[] args) {
		
		//ganme服编号
		int gameIndex = 0;
		if(args.length > 0) {
			gameIndex = Utils.intValue(args[0]);
		}
		//初始化配置
		GameConfig.init(gameIndex);
		
		// 配置文件路径
		JowConfig.RES_PATH = "../data/";
		if (args.length > 1) {
			JowConfig.RES_PATH = args[1];
		}
		
		Game game = JowDistr.gameConfigs.get(gameIndex);
		String gameNodeId = GameConfig.getGameNodeId(gameIndex);
		
		//设置日志文件
		System.setProperty("logFileName", gameNodeId);
	
		Log.game.info("======载入策划配置数据========");
		
		Log.game.info("=======初始化基本环境=======");
	
		
		MsgReceiverInit.init(MsgSender.instance);
		ListenerInit.init(Event.instance);
		MsgSerializer.init();
		CommonSerializer.init();
		ReferenceTable.INSTANCE = new DBReferenceTable();
//		ConstParam.reload();
		
		//创建Node
		Log.game.info("=====创建Node=====");
		String gameNodeAddr = game.nodeAddr;
		Node gameNode = new Node(gameNodeId, gameNodeAddr);
		
		//连接到本区服务器
		Log.game.info("=====连接到本区DB=====");
		DBConfig.init(gameNodeId, game.dbConfig);
		DBStartup.startup(gameNode);
		
		//延迟一秒，在继续初始化
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			Log.game.error(ExceptionUtils.getStackTrace(e));
		
		}
		
		//连接其他游戏服
		for (int i = 0; i < JowDistr.gameConfigs.size(); i++) {
			if(i == gameIndex) {
				continue;
			}
			
			Game gameOther = JowDistr.gameConfigs.get(i);
			gameNode.addRemoteNode(GameConfig.getGameNodeId(i), gameOther.nodeAddr);
		}
		
		//连接到本区登陆服务器
		Log.game.info("========连接到本区登陆服务器========");
		String loginNodeId = LoginConfig.NODE_LOGIN;
		String loginNodeAddr = JowDistr.loginConfig.nodeAddr;
		gameNode.addRemoteNode(loginNodeId, loginNodeAddr);
		
		//连接到本区连接服务器
		Log.game.info("======连接到本区连接服务器======");
		for (int i = 0; i < JowDistr.connConfigs.size(); ++i) {
			Conn conn = JowDistr.connConfigs.get(i);
			String connNodeId = ConnConfig.getConnNodeId(i);
			String connNodeAddr = conn.nodeAddr;
			gameNode.addRemoteNode(connNodeId, connNodeAddr);
		}
		
		//连接到全局匹配服
		Log.game.info("=======连接全局匹配服=======");
		gameNode.addRemoteNode(MatchConfig.NODE_MATCH, JowDistr.mathConfig.nodeAddr);
		
		//连接全局中央服
		Log.game.info("========连接到全局中央服======");
		gameNode.addRemoteNode(CentralConfig.NODE_CENTRAL, JowDistr.centralConfig.nodeAddr);
		
		
		//连接到全局房间服
		Log.game.info("=======连接到全服房间服========");
		for (int i = 0; i < JowDistr.roomConfigs.size(); ++i) {
			Room room = JowDistr.roomConfigs.get(i);
			String roomNodeId = RoomConfig.getRoomNodeId(i);
			String roomNodeAddr = room.nodeAddr;
			gameNode.addRemoteNode(roomNodeId, roomNodeAddr);
		}
		
		//创建一个临时Port
		DefaultPort portDef = new DefaultPort(JowDistr.PORT_DEFAULT);
		portDef.startup(gameNode);
		
		
		//加载系统数据
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
				gameNode.pulse();
			} catch (InterruptedException e) {
				Log.game.error(ExceptionUtils.getStackTrace(e));
			}
		}
		
		//启动游戏管理服务
		GameManagerService gameManagerService = new GameManagerService(portDef);
		gameManagerService.startup();
		portDef.addService(gameManagerService);
		
		
		//启动全部游戏逻辑服务
		for (int i = 0; i < game.portStartupNumGame; ++i) {
			GamePort gamePort = new GamePort(GameConfig.PORT_GAME_PERFIX + i);
			gamePort.startup(gameNode);
			GameService gameService = new GameService(gamePort);
			gameService.startup();
			gamePort.addService(gameService);
		}
		
		
		//启动游戏业务服务
		Port workPort = new Port(GameConfig.PORT_GAME_WORK);
		workPort.startup(gameNode);
		
		
		// Node正式启动
		Log.game.info("======Node正式启动======");
		gameNode.startup();
		
		// 启动日志信息
		Log.game.info("====================");
		Log.game.info(gameNodeId + " started.");
		Log.game.info("Listen:" + gameNodeAddr);
		Log.game.info("====================");
		
		// 系统关闭时进行清理
		Runtime.getRuntime().addShutdownHook(new Thread() { 
			public void run() {
				Log.game.info("======服务器关闭处理中========");
				
				try {
					// 通知立即刷新缓存服务器
					DBStartup.flushAll(gameNode);
					// 再等待2秒 持久化数据
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			} 
		});
	}
}
