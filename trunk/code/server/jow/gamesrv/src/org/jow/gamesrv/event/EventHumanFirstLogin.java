package org.jow.gamesrv.event;

import org.jow.common.event.EventKey;
import org.jow.core.gen.event.Event;
import org.jow.core.gen.event.EventParameter;
import org.jow.gamesrv.HumanObject;

@Event(key = EventKey.HUMAN_FIRST_LOGIN, importClass = {HumanObject.class}, comment = "角色出生第一次登陆事件")
public enum EventHumanFirstLogin {
	
	@EventParameter(type = HumanObject.class, comment = "角色数据")
	humanObj,
}
