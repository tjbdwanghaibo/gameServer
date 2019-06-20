package org.jow.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author g
 *
 * 一些全局常量
 */
public class GlobalConst {
	/** 职业定义 */
	/** 枫 */
	public static final int PRO1					= 1;
	/** 烈 */
	public static final int PRO2					= 2;
	/** 雪 */
	public static final int PRO3					= 3;
	/** 绝 */
	public static final int PRO4					= 4;
	/** 罗 */
	public static final int PRO5					= 5;
	/** 职业列表 */
	public static final List<Integer> PROFESSIONS = Arrays.asList(PRO1, PRO2, PRO3, PRO4, PRO5);
	
	/* 性别定义 */
	/**  女性 */
	public static final int SEX_FEMALE				= 0;
	/**  男性 */
	public static final int SEX_MALE				= 1;
	/**  保密 */
	public static final int SEX_SECRET				= -1;
	
	/* 玩家在线状态 */
	/**  离线 */
	public static final int ONLINE_STATUS_OFFLINE	= 0;
	/**  掉线等待重连中 */
	public static final int ONLINE_STATUS_DROPPED	= 1;
	/**  在线 */
	public static final int ONLINE_STATUS_ONLINE	= 2;
	
	/* AOI系统中，对象Appear和Disappear的方式 */
	/**  正常出现(AOI移动导致看见) */
	public static final int APPEAR_TYPE_NORMAL			= 1;
	/**  出生 */
	public static final int APPEAR_TYPE_BIRTH			= 2;
	/**  攀爬结束出现 */
	public static final int APPEAR_TYPE_CLIMB			= 3;
	/**  跳跃结束出现 */
	public static final int APPEAR_TYPE_JUMP			= 4;
	
	/**  正常消失(AOI移动导致消失) */
	public static final int DISAPPEAR_TYPE_NORMAL		= 1;
	/**  死亡后消失 */
	public static final int DISAPPEAR_TYPE_DIE			= 2;
	/**  掉落物拾取后消失 */
	public static final int DISAPPEAR_TYPE_DROP_PICKUP	= 2;
	/**  攀爬结束消失(攀爬过程中玩家在起爬点) */
	public static final int DISAPPEAR_TYPE_CLIMB		= 3;
	/**  跳跃开始消失 */
	public static final int DISAPPEAR_TYPE_JUMP			= 4;
	/**  斩杀后消失 */
	public static final int DISAPPEAR_TYPE_FINALKILLED	= 5;
	
	/* changePosition类型 */
	/**  移动 */
	public static final int CHANGE_POSITION_TYPE_MOVE	= 1;
	/**  传送 */
	public static final int CHANGE_POSITION_TYPE_PULL	= 2;
	/**  攀爬 */
	public static final int CHANGE_POSITION_TYPE_CLIMB	= 3;
	/**  跳跃 */
	public static final int CHANGE_POSITION_TYPE_JUMP	= 4;
	
	/* unitObj的战斗状态 */
	/**  空闲状态 */
	public static final int COMBAT_STATE_IDLE		= 0;
	/**  战斗状态 */
	public static final int COMBAT_STATE_FIGHT		= 1;
	/**  归位状态 */
	public static final int COMBAT_STATE_BACK		= 2;

	/* DStageHuman的状态 */
	/**  角色 */
	public static final int HUMAN_TYPE0		= 0;
	/**  镖车 */
	public static final int HUMAN_TYPE1		= 1;
	
	/** 生肖最小值 */
	public static final int CHINESEZODIAC_MIN = 1;
	/** 生肖最大值 */
	public static final int CHINESEZODIAC_MAX = 12;
}
