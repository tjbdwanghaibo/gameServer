package org.jow.loginsrv;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jow.common.msg.MsgAccount.CSLogin;
import org.jow.common.msg.MsgIds;
import org.jow.core.CallPoint;
import org.jow.core.support.observer.MsgReceiver;
import org.jow.loginsrv.support.MsgParamAccount;

/**
 * @author gaopan
 *
 * 登陆消息处理
 */
public class AccountMsgHandler {
	/**  下属监听消息 */
	public static final Set<Integer> protos = new HashSet<>();

	static {
		// 寻找本类监听的消息
		Method[] mths = AccountMsgHandler.class.getMethods();
		for (Method m : mths) {
			// 不是监听函数的忽略
			if (!m.isAnnotationPresent(MsgReceiver.class)) {
				continue;
			}
			// 记录
			MsgReceiver ann = m.getAnnotation(MsgReceiver.class);
			protos.add(ann.value()[0]);
		}
	}
	
	@MsgReceiver(MsgIds.CSLogin)
	public static void onCSLogin(MsgParamAccount param) {
		CSLogin msg = param.getMsg();
		CallPoint connPoint = param.getConnPoint();
		AccountService serv = param.getService();
		
		AccountObject accountObj = new AccountObject();
		accountObj.setConnPoint(connPoint);
		if (msg.hasAccount()) {
			accountObj.setAccount(StringUtils.trim(msg.getAccount()));
		}
		
		if (msg.hasPassword()) {
			accountObj.setPassword(StringUtils.trim(msg.getPassword()));
		}
		
		if (msg.hasDeviceId()) {
			accountObj.setDeviceId(StringUtils.trim(msg.getDeviceId()));
		}
		
		if (msg.hasChannelId()) {
			accountObj.setChannelId(StringUtils.trim(msg.getChannelId()));
		}
		
		// 加入登陆队列
		serv.loginApplyAdd(accountObj);
	}

}
