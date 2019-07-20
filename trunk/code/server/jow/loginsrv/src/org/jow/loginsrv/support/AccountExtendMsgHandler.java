package org.jow.loginsrv.support;

import org.jow.common.support.MsgHandler;
import org.jow.core.CallPoint;
import org.jow.core.Parms;
import org.jow.core.support.observer.MsgSender;
import org.jow.loginsrv.AccountMsgHandler;
import org.jow.loginsrv.AccountService;

import com.google.protobuf.GeneratedMessage;

public class AccountExtendMsgHandler extends MsgHandler{

	@Override
	public void fire(GeneratedMessage msg, Parms param) {
		int msgId = param.getInt("msgId");
		//忽略不是本阶段要关心的协议
		if(!AccountMsgHandler.protos.contains(msgId)) {
			return;
		}
		
		MsgParamAccount mp = new MsgParamAccount(msg);
		mp.setConnPoint(param.<CallPoint>get("connPoint"));
		mp.setService(param.<AccountService>get("serv"));
		
		MsgSender.fire(msgId, mp);
	}

}
