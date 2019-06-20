package org.jow.loginsrv.support;

import org.jow.core.CallPoint;
import org.jow.core.support.observer.MsgParamBase;
import org.jow.loginsrv.AccountService;

import com.google.protobuf.GeneratedMessage;

/**
 * 登陆阶段客户端的消息都通过这个类来封装
 * @author Yangxianmeng
 *
 */

public class MsgParamAccount extends MsgParamBase {

	/** 发送消息连接点 */
	private CallPoint connPoint;
	/** 所属服务 */
	private AccountService service;
	
	public MsgParamAccount(GeneratedMessage msg) {
		super(msg);
	}

	public CallPoint getConnPoint() {
		return connPoint;
	}

	public void setConnPoint(CallPoint connPoint) {
		this.connPoint = connPoint;
	}

	public AccountService getService() {
		return service;
	}

	public void setService(AccountService service) {
		this.service = service;
	}
}
