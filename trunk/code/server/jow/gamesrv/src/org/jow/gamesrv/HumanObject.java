package org.jow.gamesrv;

import org.jow.core.CallPoint;

public class HumanObject {

	
	/** 玩家Id */
	private long id;
	/** 所属服务 */
	private GameService service;
	/** 连接点 */
	private CallPoint connPoint;
	
	public HumanObject(long humanId, GameService gameService, CallPoint connPoint2) {
		// TODO Auto-generated constructor stub
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
