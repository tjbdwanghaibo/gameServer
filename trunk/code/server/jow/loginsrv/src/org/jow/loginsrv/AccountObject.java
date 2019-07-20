package org.jow.loginsrv;

import org.jow.core.CallPoint;

public class AccountObject {

	/** 登陆状态 */
	/** 登陆队列中 */
	public static final int STATE_WAITINT = 0;
	/** 正在登陆中 */
	public static final int STATE_LOGING = 0;
	
	
	/** 账号ID，与角色ID相同 */
	private long id;
	/** 连接点 */
	private CallPoint connPoint;
	/** 账号 */
	private String account;
	/** 密码 */
	private String password;
	/** 设备Id */
	private String deviceId;
	/** 渠道Id */
	private String channelId;
	
	/** 登陆状态 */
	private int state = STATE_WAITINT;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getConnId() {
		return (long)connPoint.serviceId;
	}
	
	public CallPoint getConnPoint() {
		return connPoint;
	}

	public void setConnPoint(CallPoint connPoint) {
		this.connPoint = connPoint;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
