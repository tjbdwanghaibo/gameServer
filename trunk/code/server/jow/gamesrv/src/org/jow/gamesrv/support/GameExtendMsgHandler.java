package org.jow.gamesrv.support;

import org.jow.common.support.MsgHandler;
import org.jow.core.Parms;
import org.jow.core.support.observer.MsgSender;
import org.jow.gamesrv.HumanObject;

import com.google.protobuf.GeneratedMessage;

public class GameExtendMsgHandler extends MsgHandler {

	@Override
	public void fire(GeneratedMessage msg, Parms param) {
		HumanObject humanObj = param.get("humanObj");
		int msgId = param.getInt("msgId");
		// 用户还没有登陆完毕，却受到了客户端消息，丢弃
		if (humanObj.isLogining()) {
			Log.game.info("Drop message, human is logining, msg={}", msg.getClass().getSimpleName());
			return;
		}
		
		MsgParamGame mp = new MsgParamGame(msg);
		mp.setHumanObj(humanObj);
		MsgSender.fire(msgId, mp);
	}
}
