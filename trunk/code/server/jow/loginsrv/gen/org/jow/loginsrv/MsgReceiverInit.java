package org.jow.loginsrv;
import org.jow.core.support.observer.Observer;
import org.jow.core.support.function.*;
import org.jow.core.gen.JowGenFile;

@JowGenFile
public final class MsgReceiverInit {
	public static void init(Observer ob) {
		ob.reg("111", (JowFunction1<org.jow.loginsrv.support.MsgParamAccount>)org.jow.loginsrv.AccountMsgHandler::onCSLogin, 1);
	}
}

