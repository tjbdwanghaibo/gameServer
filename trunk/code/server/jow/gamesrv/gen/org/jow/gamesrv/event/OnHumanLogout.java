package org.jow.gamesrv.event;

import org.jow.core.interfaces.IEvent;
import org.jow.core.Parms;
import org.jow.gamesrv.HumanObject;
import org.jow.common.event.EventKey;
import org.jow.core.gen.JowGenFile;


@JowGenFile
public final class OnHumanLogout implements IEvent {


	/** 玩家数据 */
	private HumanObject humanObj;

	@Override
	public int getKey() {
		return EventKey.HUMAN_LOGOUT;
	}
	
	@Override
	public Parms toParam() {
		Parms param = new Parms();
		param.put("humanObj", humanObj);
		return param;
	}
	
	public OnHumanLogout(HumanObject humanObj)  {
		this.humanObj = humanObj;
	}

	/**
	 * 玩家数据  
	 */
	public HumanObject getHumanObj() {
		return humanObj;
	}

	public void setHumanObj(HumanObject humanObj) {
		this.humanObj = humanObj;
	}

}