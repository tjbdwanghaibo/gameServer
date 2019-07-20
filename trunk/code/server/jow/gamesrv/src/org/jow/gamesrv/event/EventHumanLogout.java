package org.jow.gamesrv.event;

import org.jow.common.event.EventKey;
import org.jow.core.gen.event.Event;
import org.jow.core.gen.event.EventParameter;
import org.jow.gamesrv.HumanObject;

@Event(key = EventKey.HUMAN_LOGOUT, importClass = { HumanObject.class }, comment = "玩家登出事件")
public enum EventHumanLogout {

	@EventParameter(type = HumanObject.class, comment = "玩家数据")
	humanObj,
	
}
