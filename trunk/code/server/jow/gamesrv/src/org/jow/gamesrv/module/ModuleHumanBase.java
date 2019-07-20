package org.jow.gamesrv.module;

import org.jow.gamesrv.HumanObject;

public class ModuleHumanBase {
	
	//所属对象玩家
	protected HumanObject humanObj;
	
	public ModuleHumanBase(HumanObject humanObj) {
		this.humanObj = humanObj;
	}
	
	
	/**
	 * 从db记载完后，初始化模块
	 */
	public void initData() {
		
		
	}
	
	/**
	 * 心跳
	 * @param now
	 */
	public void pulse(long now) {
		
	}
	
	
	public HumanObject getHumanObj() {
		
		return humanObj;
	}
	
}
