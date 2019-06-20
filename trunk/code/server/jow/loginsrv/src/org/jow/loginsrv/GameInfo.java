package org.jow.loginsrv;

import org.jow.core.support.TimeUtils;

/**
 * @author gaopan
 *
 * 各逻辑服的状态信息
 */
public class GameInfo {
	/** 多长时间收不到ping，就认为是未激活状态 */
	private static final long PING_TIMEOUT = 10 * TimeUtils.SEC;
	
	/** 逻辑服编号 */
	private int index;
	/** 该逻辑服的注册人数 */
	private int registerCount;
	/** 该逻辑服的在线人数 */
	private int onlineCount;
	/** 上次收到逻辑服ping的时刻 */
	private long lastPingTime;
	
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
