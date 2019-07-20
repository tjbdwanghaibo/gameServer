package org.jow.gamesrv;

import java.util.HashMap;
import java.util.Map;

import org.jow.common.constant.ErrorCode;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.CallPoint;
import org.jow.core.Port;
import org.jow.core.Service;
import org.jow.core.config.GameConfig;
import org.jow.core.gen.proxy.DistrClass;
import org.jow.core.gen.proxy.DistrMethod;
import org.jow.core.support.ConnectionStatus;
import org.jow.gamesrv.support.GameExtendMsgHandler;
import org.jow.gamesrv.support.Log;

/**
 * 游戏服务器，一个游戏Port有且仅有一个
 * @author Yangxianmeng
 *
 */

@DistrClass(importClass = {CallPoint.class})
public class GameService extends Service{

	/** 协议处理类 */
	private static GameExtendMsgHandler msgHandler = new GameExtendMsgHandler();
	
	/** 玩家对象 */
	private Map<Long, HumanObject> humanObjs = new HashMap<>();
	
	public GameService(Port port) {
		super(port);
	}

	@Override
	public Object getId() {
		return GameConfig.SERV_GAME;
	}

	@Override
	protected void pulseOverride() {
		super.pulseOverride();
		
		long nowTime = port.getTimeCurrent();
		
		for(HumanObject humanObj : humanObjs.values()) {
			humanObj.pulse(nowTime);
		}
	}
	
	@DistrMethod
	public void msgHandler(long connId, long humanId, byte[] msgbuf) {
		
		HumanObject humanObj = humanObjs.get(humanId);
		
		if(humanObj == null) {
			Log.game.warn("HumanObject not exist, humanId={}", humanId);
			
			ConnectionProxy prx = ConnectionProxy.newInstance(port.getCallFromNodeId(), port.getCallFromPortId(), connId);
			prx.closeImmediate();
			return;
		}
		
		
		long currConnId = (long) humanObj.getConnPoint().serviceId;
		//连接点不匹配，关闭连接
		if(currConnId != connId) {
			Log.game.warn("ConnId not match, connId={}, currConnId={}", connId, currConnId);
			
			ConnectionProxy prx = ConnectionProxy.newInstance(port.getCallFromNodeId(), port.getCallFromPortId(), connId);
			prx.closeImmediate();
			return;
		}
		
		boolean result = msgHandler.handle(msgbuf, "humanObj", humanObj);
		if (!result) {
			humanObj.sendHint(ErrorCode.HUMAN_FUNC_CLOSED);
		}
		
	}
	
	@DistrMethod
	public void connClosed(long connId, long humanId) {
		HumanObject humanObj = humanObjs.get(humanId);
		// 玩家对象不存在
		if (humanObj == null) {
			Log.game.warn("HumanObject not exist, humanId={}", humanId);
			return;
		}
		
		long currConnId = (long)humanObj.getConnPoint().serviceId;
		// 连接点不匹配
		if (currConnId != connId) {
			Log.game.warn("ConnId not match, connId={}, currConnId={}", connId, currConnId);
			return;
		}
		
		humanObj.onConnClosed();
		
		// 移除玩家对象
		humanObjs.remove(humanId);
	}
	
	@DistrMethod
	public void connDropped(long connId, long humanId) {
		HumanObject humanObj = humanObjs.get(humanId);
		// 玩家对象不存在
		if (humanObj == null) {
			Log.game.warn("HumanObject not exist, humanId={}", humanId);
			return;
		}
		
		long currConnId = (long)humanObj.getConnPoint().serviceId;
		// 连接点不匹配
		if (currConnId != connId) {
			Log.game.warn("ConnId not match, connId={}, currConnId={}", connId, currConnId);
			return;
		}
		
		humanObj.onConnDropped(connId, humanId);
	}
	
	@DistrMethod
	public void connCheck(long connId, long humanId) {
		HumanObject humanObj = humanObjs.get(humanId);
		// 玩家对象不存在
		if (humanObj == null) {
			port.returns(false);
			return;
		}
		
		long currConnId = (long)humanObj.getConnPoint().serviceId;
		// 连接点不匹配
		if (currConnId != connId) {
			port.returns(false);
			return;
		}
		
		port.returns(true);
	}
	
	@DistrMethod
	public void login(CallPoint connPoint, long humanId, String account) {
		HumanObject humanObj = humanObjs.get(humanId);
		if (humanObj != null) {
			Log.game.warn("Already login, humanId={}, currConn={}", humanObj.getConnPoint());
			port.returns(ErrorCode.LOGIN_ALREADY_LOGIN);
		} else {
			// 创建玩家对象
			humanObj = new HumanObject(humanId, this, connPoint);
			humanObjs.put(humanId, humanObj);
			
			// 更新连接状态为游戏中
			ConnectionStatus status = new ConnectionStatus();
			status.step = ConnectionStatus.STATUS_PLAYING;
			status.humanId = humanId;
			status.account = account;
			status.gamePoint = new CallPoint(port.getNodeId(), port.getPortId(), getId());
			ConnectionProxy prx = ConnectionProxy.newInstance(connPoint);
			prx.statusUpdate(status);
			
			port.returns(0);
		}
	}
	
	
	
	
}
