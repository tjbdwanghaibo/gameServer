package org.jow.gamesrv.event;

import org.jow.common.event.EventKey;
import org.jow.core.gen.event.Event;
import org.jow.core.gen.event.EventParameter;
import org.jow.gamesrv.HumanObject;

@Event(key = EventKey.HUMAN_FRIST_LOGIN, importClass = { HumanObject.class }, comment = "玩家这辈子首次登陆时间")
public enum EventHumanFristLogin {

	@EventParameter(type = HumanObject.class, comment = "角色数据")
	humanObj,
	
}
