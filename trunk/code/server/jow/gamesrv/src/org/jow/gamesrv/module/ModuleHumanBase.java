package org.jow.gamesrv.module;

import org.jow.gamesrv.HumanObject;

/**
 * 玩家模块基类
 * 
 * @author Yangxianmeng
 *
 */
public abstract class ModuleHumanBase {

	/** 所属玩家对象 */
	private HumanObject humanObject;
	
	public ModuleHumanBase(HumanObject humanObject) {
		this.humanObject = humanObject;
	}
	
	/**
	 * 从DB加载完后，初始化模块
	 */
	public void initData() {
		
		
	}
	
	/**
	 * 心跳
	 * @param now
	 */
	public void pulse(long now) {
		
		
	}

	public HumanObject getHumanObject() {
		return humanObject;
	}
}
