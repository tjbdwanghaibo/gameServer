package org.jow.gamesrv.event;

import org.jow.core.interfaces.IEvent;
import org.jow.core.Parms;
import org.jow.gamesrv.HumanObject;
import org.jow.common.event.EventKey;
import org.jow.core.gen.JowGenFile;


@JowGenFile
public final class OnHumanLogin implements IEvent {


	/** 玩家数据 */
	private HumanObject humanObj;
	/** 最后登陆时间 */
	private long timeLoginLast;

	@Override
	public int getKey() {
		return EventKey.HUMAN_LOGIN;
	}
	
	@Override
	public Parms toParam() {
		Parms param = new Parms();
		param.put("humanObj", humanObj);
		param.put("timeLoginLast", timeLoginLast);
		return param;
	}
	
	public OnHumanLogin(HumanObject humanObj,long timeLoginLast)  {
		this.humanObj = humanObj;
		this.timeLoginLast = timeLoginLast;
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
	/**
	 * 最后登陆时间  
	 */
	public long getTimeLoginLast() {
		return timeLoginLast;
	}

	public void setTimeLoginLast(long timeLoginLast) {
		this.timeLoginLast = timeLoginLast;
	}

}