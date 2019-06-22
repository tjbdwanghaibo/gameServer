package org.jow.gamesrv;

import org.jow.common.db.DB;
import org.jow.common.entity.game.HumanDB;
import org.jow.common.game.HumanCentralInfo;
import org.jow.common.game.HumanInfo;
import org.jow.core.CallPoint;
import org.jow.core.Port;
import org.jow.core.Record;
import org.jow.core.config.GameConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.support.Param;
import org.jow.core.support.TickTimer;
import org.jow.core.support.TimerQueue;
import org.jow.core.support.random.RandomUtils;
import org.jow.gamesrv.RPCProxy.GameManagerServiceProxy;
import org.jow.gamesrv.module.Chat.ModChat;

/**
 * 玩家对象
 * 
 * @author Yangxianmeng
 *
 */
public class HumanObject {
	
	/** 玩家Id */
	private long id;
	/** 所属服务 */
	private GameService gameService;
	/** 连接点 */
	private CallPoint connPoint;
	/** 玩家主db */
	private HumanDB humanDB;
	
	/** 正在加载时计时器 等于零时表示加载完毕 */
	private int loadingNum = 0;
	/** 正在登陆中 */
	private boolean logining = true;
	
	/** 本心跳是否监控玩家属性变化 */
	private boolean isHumanInfoListen;
	
	/** 记录玩家属性变化并同步给客户端 */
	private HumanInfoChange humanInfoChange;
	
	
	
	//-------------------------------------------------------
	
	//Modules
	/** 聊天模块 */
	private ModChat modChat = new ModChat(this);
	
	
	
	/** 高精度定时器 */
	private TimerQueue timerQueue = new TimerQueue();
	
	
	
	
	public HumanObject(long humanId, GameService gameService, CallPoint connPoint) {
		this.id = humanId;
		this.gameService = gameService;
		this.connPoint = connPoint;
		
		loadData();
		
	}

	/**
	 * 玩家主数据加载
	 */
	private void loadData() {
		DB db = DB.newInstance(HumanDB.tableName);
		db.get(id);
		db.listenResult(this::_result_loadHuman);
		
	}
	
	private void _result_loadHuman(Param results, Param context) {
		Record record = results.get();
		if(record == null) {
			humanDB = new HumanDB();
			humanDB.setId(id);
			humanDB.setHead("Icon_role1.png");
			humanDB.setLevel(1);
			humanDB.setName("游客123456");
			humanDB.setSex(RandomUtils.nextInt(2));
			humanDB.setSignature("");
			humanDB.persist();
			
			HumanInfo humanInfo = new HumanInfo();
			humanInfo.setId(id);
			humanInfo.setName(humanDB.getName());
			humanInfo.setLevel(humanDB.getLevel());
			humanInfo.setSignature(humanDB.getSignature());
			humanInfo.setSex(humanDB.getSex());
			humanInfo.setHead(humanDB.getHead());
			
			Port port = Port.getCurrent();
			GameManagerServiceProxy gameManagerServiceProxy = GameManagerServiceProxy.newInstance(port.getNodeId(),
					JowDistr.PORT_DEFAULT, GameConfig.SERV_GAME_MANAGER);
			gameManagerServiceProxy.humanCreate(humanInfo);
			
			//注册到全局中央服
			HumanCentralInfo humanCentralInfo = new HumanCentralInfo();
			humanCentralInfo.setId(id);
			humanCentralInfo.setName(humanDB.getName());
			humanCentralInfo.setSex(humanDB.getSex());
			humanCentralInfo.setHead(humanDB.getHead());
			humanCentralInfo.setLevel(humanDB.getLevel());
			
			
			
			
		}
		
		
	}
	
	
	public boolean isLogining() {
		// TODO Auto-generated method stub
		return false;
	}

	public void pulse(long nowTime) {
		// TODO Auto-generated method stub
		
	}

	public CallPoint getConnPoint() {
		// TODO Auto-generated method stub
		return connPoint;
	}

	public void sendHint(int humanFuncClosed) {
		// TODO Auto-generated method stub
		
	}

	public void onConnClosed() {
		// TODO Auto-generated method stub
		
	}

	public void onConnDropped(long connId, long humanId) {
		// TODO Auto-generated method stub
		
	}

}
