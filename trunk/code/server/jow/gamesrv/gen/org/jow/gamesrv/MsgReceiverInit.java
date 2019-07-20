package org.jow.gamesrv;
import org.jow.core.support.observer.Observer;
import org.jow.core.support.function.*;
import org.jow.core.gen.JowGenFile;

@JowGenFile
public final class MsgReceiverInit {
	public static void init(Observer ob) {
		ob.reg("2003", (JowFunction1<org.jow.gamesrv.support.MsgParamGame>)org.jow.gamesrv.HumanMsgHandler::onCSChangeHead, 1);
		ob.reg("1802", (JowFunction1<org.jow.gamesrv.support.MsgParamGame>)org.jow.gamesrv.module.chat.ChatMsgHandler::onCSChat, 1);
		ob.reg("1805", (JowFunction1<org.jow.gamesrv.support.MsgParamGame>)org.jow.gamesrv.module.chat.ChatMsgHandler::onCSCreatRoom, 1);
	}
}

