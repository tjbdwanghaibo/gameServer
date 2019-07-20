package org.jow.gamesrv.event;

import org.jow.core.interfaces.IEvent;
import org.jow.core.Parms;
import org.jow.gamesrv.HumanObject;
import org.jow.common.event.EventKey;
import org.jow.core.gen.JowGenFile;


@JowGenFile
public final class OnHumanFristLogin implements IEvent {


	/** 角色数据 */
	private HumanObject humanObj;

	@Override
	public int getKey() {
		return EventKey.HUMAN_FRIST_LOGIN;
	}
	
	@Override
	public Parms toParam() {
		Parms param = new Parms();
		param.put("humanObj", humanObj);
		return param;
	}
	
	public OnHumanFristLogin(HumanObject humanObj)  {
		this.humanObj = humanObj;
	}

	/**
	 * 角色数据  
	 */
	public HumanObject getHumanObj() {
		return humanObj;
	}

	public void setHumanObj(HumanObject humanObj) {
		this.humanObj = humanObj;
	}

}