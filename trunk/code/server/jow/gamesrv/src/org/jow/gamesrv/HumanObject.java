package org.jow.gamesrv;

import org.jow.centralsrv.RPCProxy.HumanGlobalServiceProxy;
import org.jow.common.db.DB;
import org.jow.common.entity.game.HumanDB;
import org.jow.common.event.Event;
import org.jow.common.game.HumanCentralInfo;
import org.jow.common.game.HumanInfo;
import org.jow.common.msg.MsgCommon.SCHint;
import org.jow.common.msg.MsgDef.DHuman;
import org.jow.common.msg.MsgCommon.SCInitData;
import org.jow.common.msg.MsgIds;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.CallPoint;
import org.jow.core.Chunk;
import org.jow.core.Parms;
import org.jow.core.Port;
import org.jow.core.Record;
import org.jow.core.config.CentralConfig;
import org.jow.core.config.GameConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.support.random.RandomUtils;
import org.jow.gamesrv.RPCProxy.GameManagerServiceProxy;
import org.jow.gamesrv.event.OnHumanFristLogin;
import org.jow.gamesrv.event.OnHumanLogin;
import org.jow.gamesrv.event.OnHumanLogout;
import org.jow.gamesrv.module.chat.ModChat;
import org.jow.gamesrv.support.Log;

import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;

public class HumanObject {
	
	/** 玩家Id */
	private long id;
	/** 所属服务 */
	private GameService service;
	/** 连接点 */
	private CallPoint connPoint;
	
	/** 玩家DB */
	private HumanDB humanDB;
	/** 正在登陆中 */
	private boolean logining = true;
	
	/** 是否本心跳监控玩家属性变化 */
	private boolean isHumanInfoListen;
	
	/** 记录玩家的属性变化，并在本次心跳结束后发送变化至客户端 */
	private HumanInfoChange humanInfoChange;
	
	/** 正在加载玩家数据时的计数器 当等于0时代表加载完毕 */
	public int loadingNum = 0;
	
	//----------各种Moudles--------->>
	
	/** 聊天模块 */
	private ModChat modChat = new ModChat(this);
	
	//-----------------------------<<
	
	
	public ModChat getModChat() {
		return modChat;
	}


	public void setModChat(ModChat modChat) {
		this.modChat = modChat;
	}


	public void pulse(long now) {
		if(logining) {
			return;
		}
		
		// 历史纪录模块
//		modHistory.pulse(now);
		// 卡片管理模块
//		modCard.pulse(now);
		// 组队模块
//		modTeam.pulse(now);
		// 战斗模块
//		modBattle.pulse(now);
		
		// 发送玩家本身属性更新信息至客户端
		if (isHumanInfoListen) {
			isHumanInfoListen = false;
			humanInfoChange.resultForListen(this);
		}
	}
	
	
	public HumanObject(long id, GameService service, CallPoint connPoint) {
		this.id = id;
		this.setService(service);
		this.setConnPoint(connPoint);
		
		loadData();
	}

	// 加载
	private void loadData() {
		// 加载玩家主数据
		DB db = DB.newInstance(HumanDB.tableName);
		db.get(id);
		db.listenResult(this::_result_loadHuman);

	}
	
	private void _result_loadHuman(Parms results, Parms context) {
		Record record = results.get();
		if (record == null) {
			humanDB = new HumanDB();
			humanDB.setId(id);
			humanDB.setName("游客" + String.valueOf(id));
			humanDB.setLevel(1);
			humanDB.setSex(RandomUtils.nextInt(2));
			humanDB.setHead("Icon_role1.png");
			humanDB.setSignature("");
			humanDB.persist();
			
			HumanInfo humanInfo = new HumanInfo();
			humanInfo.setId(id);
			humanInfo.setName(humanDB.getName());
			humanInfo.setLevel(humanDB.getLevel());
			humanInfo.setSex(humanDB.getSex());
			humanInfo.setHead(humanDB.getHead());
			Port port = Port.getCurrent();
			GameManagerServiceProxy gameManagerPrx = GameManagerServiceProxy.newInstance(port.getNodeId(), 
					JowDistr.PORT_DEFAULT, GameConfig.SERV_GAME_MANAGER);
			gameManagerPrx.humanCreate(humanInfo);
			
			// 注册到全局中央服务器
			HumanCentralInfo humanCentralInfo = new HumanCentralInfo();
			humanCentralInfo.setId(id);
			humanCentralInfo.setName(humanDB.getName());
			humanCentralInfo.setSex(humanDB.getSex());
			humanCentralInfo.setHead(humanDB.getHead());
			humanCentralInfo.setLevel(humanDB.getLevel());
			HumanGlobalServiceProxy humanGlobalPrx = HumanGlobalServiceProxy.newInstance(CentralConfig.NODE_CENTRAL, 
//			HumanGlobalServiceProxy humanGlobalPrx = HumanGlobalServiceProxy.newInstance("MainStartup", 
					CentralConfig.PORT_HUMAN_GLOBAL, CentralConfig.SERV_HUMAN_GLOBAL);
			humanGlobalPrx.humanCreate(humanCentralInfo);
		} else {
			humanDB = new HumanDB(record);
		}
		HumanManager.load(this);
	}
	
	
	
	
	public void onLoadDataFinished() {
		
		//各种模块初始化
		modChat.initData();
		
		
		//上次最后登陆时间
		long timeLast = humanDB.getTimeLogin();
		
		//首次登陆
		if(timeLast == 0) {
			Event.fire(new OnHumanFristLogin(this));
		}
		
		humanDB.setTimeLogin(Port.getTime());
		
		//登陆时间
		Event.fire(new OnHumanLogin(this, timeLast));
		
		//发送SCInitData到客户端
		
		sendInitDataToClient();
		
		//注册到game manager
		Port port = Port.getCurrent();
		GameManagerServiceProxy gameManagerServiceProxy = GameManagerServiceProxy.newInstance(port.getNodeId(),
				JowDistr.PORT_DEFAULT, GameConfig.SERV_GAME_MANAGER);
		gameManagerServiceProxy.humanLogin(id, connPoint, service.getCallPoint());
		
		//注册到中央服务器
		HumanGlobalServiceProxy humanGlobalPrx = HumanGlobalServiceProxy.newInstance(CentralConfig.NODE_CENTRAL, 
//		HumanGlobalServiceProxy humanGlobalPrx = HumanGlobalServiceProxy.newInstance("MainStartup", 
				CentralConfig.PORT_HUMAN_GLOBAL, CentralConfig.SERV_HUMAN_GLOBAL);
		humanGlobalPrx.humanLogin(id, connPoint, service.getCallPoint());

		logining = false;
		
		
		
	}
	
	
	//下发玩家基本信息到客户端
	public void sendInitDataToClient() {
		
		SCInitData.Builder msg = SCInitData.newBuilder();
		DHuman.Builder dHuman = msg.getHumanBuilder();
		dHuman.setId(id);
		dHuman.setName(humanDB.getName());
		dHuman.setLevel(humanDB.getLevel());
		dHuman.setHead(humanDB.getHead());
		dHuman.setSex(humanDB.getSex());
		dHuman.setSignature(humanDB.getSignature());
		
		sendMsg(msg);
	}


	/**
	 * 发送提示语（str.xlsx） sn加负号表示不发送给客户端，服务器依然会打印日志
	 * 
	 * @param strSn
	 * @param strings
	 */
	public void sendHint(int strSn, String... strings) {
		if (strSn > 0) {
			SCHint.Builder hint = SCHint.newBuilder();
			hint.setStrId(strSn);
			for (String str : strings) {
				hint.addParam(str);
			}
			sendMsg(hint);
		} else {
			strSn = -strSn;
		}
//		ConfStr confHint = ConfStr.get(strSn);
//		if (confHint == null) {
//			Log.human.info("sendHint, humanId={}, msg={}, params={}", id, strSn, strings);
//		} else {
//			Log.human.info("sendHint, humanId={}, msg={}, params={}", id, confHint.strContent, strings);
//		}
	}
	

	/**
	 * 发送消息至玩家
	 * @param builder
	 */
	public void sendMsg(Builder builder) {
		
		if(builder != null) {
			sendMsg(builder.build());
		}
		
	}

	/**
	 * 发送消息至玩家
	 * @param msg
	 */
	public void sendMsg(Message msg) {
	
		if(msg != null) {
			int msgId = MsgIds.getIdByClass(msg.getClass());
			sendMsg(msgId, new Chunk(msg));
		}
	}

	/**
	 * 发送消息至玩家
	 * @param msgId
	 * @param chunk
	 */
	public void sendMsg(int msgId, Chunk chunk) {
		ConnectionProxy prx = ConnectionProxy.newInstance(connPoint);
		prx.sendMsg(msgId, chunk);
	}

	
	public void onConnClosed() {
		
		//发布退出事件
		Event.fire(new OnHumanLogout(this));
		
		//从 game manager中删除
		Port port = Port.getCurrent();
		GameManagerServiceProxy gameManagerPrx = GameManagerServiceProxy.newInstance(port.getNodeId(), 
				JowDistr.PORT_DEFAULT, GameConfig.SERV_GAME_MANAGER);
		gameManagerPrx.humanLogout(id);
		// 从中央服务器删除
		HumanGlobalServiceProxy humanGlobalPrx = HumanGlobalServiceProxy.newInstance(CentralConfig.NODE_CENTRAL, 
//		HumanGlobalServiceProxy humanGlobalPrx = HumanGlobalServiceProxy.newInstance("MainStartup", 
				CentralConfig.PORT_HUMAN_GLOBAL, CentralConfig.SERV_HUMAN_GLOBAL);
		humanGlobalPrx.humanLogout(id);
		
	}
	
	
	
	public void onConnDropped(long connId, long humanId) {
	
		Log.game.info("玩家掉线了，等待重连中........... connId = {}, humanId = {}",connId,humanId);
	}

	
	public void humanInfoChangeListen() {
		// 本次心跳已经添加过监听 忽略新的监听请求
		if (isHumanInfoListen) {
			return;
		}

		isHumanInfoListen = true;

		if (humanInfoChange == null) {
			humanInfoChange = new HumanInfoChange(this);
		}
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	public HumanDB getHuman() {
		return humanDB;
	}
	public boolean isLogining() {
		return logining;
	}
	public void setLogining(boolean logining) {
		this.logining = logining;
	}
	public CallPoint getConnPoint() {
		return connPoint;
	}
	public void setConnPoint(CallPoint connPoint) {
		this.connPoint = connPoint;
	}
	public GameService getService() {
		return service;
	}
	public void setService(GameService service) {
		this.service = service;
	}
}
