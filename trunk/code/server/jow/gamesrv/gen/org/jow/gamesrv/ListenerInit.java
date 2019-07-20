package org.jow.gamesrv;
import org.jow.core.support.observer.Observer;
import org.jow.core.support.function.*;
import org.jow.core.gen.JowGenFile;

@JowGenFile
public final class ListenerInit {
	public static void init(Observer ob) {
		ob.reg("9244", (JowFunction1<org.jow.core.interfaces.IEvent>)org.jow.gamesrv.HumanManager::onHumanLogout, 1);
	}
}

