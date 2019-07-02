package org.jow.gamesrv.event;

import org.jow.common.event.EventKey;
import org.jow.core.gen.event.Event;
import org.jow.core.gen.event.EventParameter;
import org.jow.gamesrv.HumanObject;

@Event(key = EventKey.HUMAN_LOGIN, importClass = { HumanObject.class }, comment = "角色登陆事件")

public enum EventHumanLogin {
	
	@EventParameter(type = HumanObject.class, comment = "角色数据")
	humanObj,
	
	@EventParameter(type = long.class, comment = "最后登陆时间")
	timeLoginLast,
	
}
