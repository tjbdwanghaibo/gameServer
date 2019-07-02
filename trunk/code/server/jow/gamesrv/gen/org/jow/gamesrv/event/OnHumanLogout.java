package org.jow.gamesrv.event;

import org.jow.core.interfaces.IEvent;
import org.jow.core.support.Param;
import org.jow.gamesrv.HumanObject;
import org.jow.common.event.EventKey;
import org.jow.core.gen.JowGenFile;


@JowGenFile
public final class OnHumanLogout implements IEvent {


	/** 角色数据 */
	private HumanObject humanObj;

	@Override
	public int getKey() {
		return EventKey.HUMAN_LOGOUT;
	}
	
	@Override
	public Param toParam() {
		Param param = new Param();
		param.put("humanObj", humanObj);
		return param;
	}
	
	public OnHumanLogout(HumanObject humanObj)  {
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