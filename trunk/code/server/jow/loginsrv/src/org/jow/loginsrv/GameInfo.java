package org.jow.loginsrv;

import org.jow.core.support.TimeUtils;

public class GameInfo {

	/** 多长时间接收不到ping , 就认为是未激活状态 */
	private static final long PING_TIMEOUT = 10 * TimeUtils.SEC;
	
	/** 逻辑服编号 */
	private int index;
	/** 该逻辑服注册的人数 */
	private int registerCount;
	/** 该逻辑服注册的人数 */
	private int onlineCount;
	/** 上次逻辑服ping的时刻 */
	private long lastPingTime;
	
	
	/**
	 * 是否是激活状态
	 * @param now
	 * @return
	 */
	public boolean isActive(long now) {
		return now - lastPingTime < PING_TIMEOUT;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getRegisterCount() {
		return registerCount;
	}


	public void setRegisterCount(int registerCount) {
		this.registerCount = registerCount;
	}


	public int getOnlineCount() {
		return onlineCount;
	}


	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}


	public long getLastPingTime() {
		return lastPingTime;
	}


	public void setLastPingTime(long lastPingTime) {
		this.lastPingTime = lastPingTime;
	}
	
}
