package org.jow.common.event;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.jow.core.support.SysException;

/**
 * @author Yangxianmeng
 *
 * 事件id定义
 */
public final class EventKey {
	//------------------------------------------------------------------------------
	// Platform使用的事件1000-2000

	/** http请求 */
	public static final int HTTP_RECEIVE 					= 1000;
	
	
	//------------------------------------------------------------------------------
	// Central使用的事件2000-3000
	
	
	//------------------------------------------------------------------------------
	// 下面是World相关
	
	//------------------------------------------------------------------------------
	// 下面是通用事件，100一个段
	
	/** 游戏启动前的事件 */
	public static final int GAME_STARTUP_BEFORE 			= 9001;
	/** 游戏启动完成的事件 */
	public static final int GAME_STARTUP_FINISH 			= 9002;
	
	/** GM命令事件 */
	public static final int GM 								= 9100;
	
	/** 角色通用 */
	/** 新角色创建 */
	public static final int CHARACTER_CREATED 				= 9200;
	/** 角色删除 */
	public static final int CHARACTER_DELETED 				= 9201;
	
	/** 角色改名事件 */
	public static final int HUMAN_CHANGE_NAME 				= 9210;
	/** 角色外观发生改变事件 */
	public static final int HUMAN_MODEL_CHANGE 				= 9211;
	/** 用户VipLevel发生改变 */
	public static final int HUMAN_VIPLEVEL_CHANGE 			= 9212;
	
	/** 玩家掉线 */
	public static final int HUMAN_CONNECTION_DROPPED 		= 9220;
	/** 玩家重连上了 */
	public static final int HUMAN_RECONNECTED 				= 9221;
	
	/** 角色数据加载开始事件 */
	public static final int HUMAN_DATA_LOAD_BEGIN 			= 9230;
	/** 角色数据加载开始一个事件 */
	public static final int HUMAN_DATA_LOAD_BEGIN_ONE 		= 9231;
	/** 用户玩家数据全部加载完成 */
	public static final int HUMAN_DATA_LOAD_FINISH 			= 9232;
	/** 角色数据加载完成一个事件 */
	public static final int HUMAN_DATA_LOAD_FINISH_ONE 		= 9233;
	
	/** 角色这辈子首次登陆事件 */
	public static final int HUMAN_FRIST_LOGIN 				= 9240;
	/** 角色登陆事件 */
	public static final int HUMAN_LOGIN 					= 9241;
	/** 玩家登录结束，可以开始接收消息 */
	public static final int HUMAN_LOGIN_FINISH 				= 9242;
	/** 玩家今日首次登录结束 */
	public static final int HUMAN_LOGIN_FINISH_FIRST_TODAY 	= 9243;
	/** 角色登出游戏事件 */
	public static final int HUMAN_LOGOUT 					= 9244;
	
	/** 角色进入地图事件（切换地图时会触发） */
	public static final int HUMAN_STAGE_ENTER 				= 9250;
	/** 角色进入地图之前一点点（切换地图时会触发）事件 */
	public static final int HUMAN_STAGE_ENTER_BEFORE 		= 9251;
	/** 角色离开地图之前一点点（切换地图时会触发）事件 */
	public static final int HUMAN_STAGE_LEAVE_BEFORE 		= 9252;
	
	/** 充值事件 */
	public static final int PAY 							= 9260;
	
	//------------------------------------------------------------------------------
	// 下面是自定义事件，逻辑相关，100一个段
	
	/** 宝箱开启 */
	public static final int BOX_OPEN 						= 10000;
	/** 采集物品 */
	public static final int PICK_OBJ 						= 10001;
	
	/** 穿装备 */
	public static final int EQUIPMENT_PUT_ON 				= 10200;
	/** 脱装备 */
	public static final int EQUIPMENT_TAKE_OFF 				= 10201;
	/** 装备强化 */
	public static final int EQUIPMENT_INTENSIFY 			= 10210;
	
	/** 镖车进入地图信息的事件 */
	public static final int ESCORT_OBJECT_ENTER 			= 10300;
	
	/** 时装激活 */
	public static final int FASHION_OPEN 					= 10400;
	/** 脱时装 */
	public static final int FASHION_PUT_OFF 				= 10401;
	/** 穿时装 */
	public static final int FASHION_PUT_ON 					= 10402;
	/** 时装强化 */
	public static final int FASHION_STRONG 					= 10403;

	/** 添加好友 */
	public static final int FRIEND_ADD 						= 10502;
	
	/** 新手引导状态改变 */
	public static final int GUIDE_STATUS_CHANGE 			= 10600;
	
	/** 玩家攻击 */
	public static final int HUMAN_ATTACK 					= 10700;
	/** 玩家受攻击 */
	public static final int HUMAN_BE_ATTACKED 				= 10701;
	/** 角色死亡事件 */
	public static final int HUMAN_BE_KILLED 				= 10702;
	/** 玩家受到伤害 */
	public static final int HUMAN_HPLOSS 					= 10703;
	/** 怪物攻击 */
	public static final int MONSTER_ATTACK 					= 10704;
	/** 怪物被击杀 */
	public static final int MONSTER_BE_KILLED 				= 10705;
	/** 怪物被击杀前一刻 */
	public static final int MONSTER_BE_KILLED_BEFORE 		= 10706;
	/** 怪物出生 */
	public static final int MONSTER_BORN 					= 10707;
	/** 怪物组中的怪全部死亡 */
	public static final int MONSTER_GROUP_CLEAR 			= 10708;
	/** 怪物受到伤害 */
	public static final int MONSTER_HPLOSS 					= 10709;
	/** 怪物受到伤害;攻击者为null */
	public static final int MONSTER_HPLOSS_BY_NO_HUMAN 		= 10710;
	/** 加buff */
	public static final int UNIT_ADD_BUFF_STATE 			= 10711;
	/** 战斗单元攻击 */
	public static final int UNIT_ATTACK 					= 10712;
	/** 战斗单元受攻击 */
	public static final int UNIT_BE_ATTACKED 				= 10713;
	/** 战斗单元死亡 */
	public static final int UNIT_BE_KILLED 					= 10714;
	/** 释放技能 */
	public static final int UNIT_DO_SKILL 					= 10715;
	/** 战斗单元受到伤害 */
	public static final int UNIT_HPLOSS 					= 10716;
	/** 加skillState */
	public static final int UNIT_ADD_SKILL_STATE 			= 10719;
	/** 加unitFlag */
	public static final int UNIT_ADD_FLAG 					= 10720;
	/** 移动 */
	public static final int UNIT_MOVE						= 10721;
	/** 有CmdAI的战斗单元，ai脚本执行完毕 */
	public static final int UNIT_CMD_AI_COMPLETE			= 10722;
	/** 玩家离开战斗状态 */
	public static final int HUMAN_LEAVE_FIGHT				= 10723;
	/** 玩家进入战斗状态 */
	public static final int HUMAN_ENTER_FIGHT				= 10724;
	
	/** 地图对象离开地图 */
	public static final int OBJECT_STAGE_LEAVE_BEFORE 		= 10800;
	
	/** 玩家货币增加 */
	public static final int HUMAN_CURRENCY_GRANT 			= 10900;
	/** 玩家货币减少 */
	public static final int HUMAN_CURRENCY_SPEND 			= 10901;
	
	/** 角色进入活动副本事件 */
	public static final int HUMAN_ENTER_INSTANCE_ACT 		= 11000;
	/** 角色进入剧情副本 */
	public static final int HUMAN_ENTER_INSTANCE_DRA 		= 11001;
	/** 副本完成 */
	public static final int INSTANCE_COMPLETE 				= 11002;
	/** 副本离开 */
	public static final int INSTANCE_LEAVE 					= 11003;
	/** 副本通关 */
	public static final int INSTANCE_PASS 					= 11004;
	
	/** 角色功能开放 */
	public static final int HUMAN_FUNC_OPENED 				= 11100;
	
	/** 用户升级，一次性升多级时只会抛出一次事件 */
	public static final int HUMAN_LEVELUP 					= 11200;
	/** 用户升级，每次升一级抛出一次 */
	public static final int HUMAN_LEVELUP_ONCE 				= 11201;
	
	/** 每天发生的定时事件 */
	public static final int HUMAN_RESET_DAY 				= 11300;
	/** 每周发生的定时事件 */
	public static final int HUMAN_RESET_WEEK 				= 11301;
	
	/** 玩家复活前一点点(在玩家复活时会触发) */
	public static final int HUMAN_REVIVE_BEFORE 			= 11400;
	
	/** 玩家设置PK状态 */
	public static final int HUMAN_SET_PKSTATUS 				= 11500;
	
	/** 签到 */
	public static final int HUMAN_SIGN 						= 11600;
	
	/** 角色加入队伍事件 */
	public static final int HUMAN_TEAM_IN 					= 11700;
	/** 角色离开队伍事件 */
	public static final int HUMAN_TEAM_OUT 					= 11701;
		
	/** 角色加入帮派事件 */
	public static final int HUMAN_UNION_IN 					= 11800;
	/** 角色离开帮派事件 */
	public static final int HUMAN_UNION_OUT 				= 11801;
	/** 角色帮派改名事件 */
	public static final int HUMAN_UNION_RENAME 				= 11802;
	
	/** 获得新物品 */
	public static final int ITEM_GET_NEW 					= 11900;
	/** 获得新物品 */
	public static final int ITEM_GRANT 						= 11901;
	/** 扣除道具 */
	public static final int ITEM_SPEND 						= 11902;
	/** 道具使用 */
	public static final int ITEM_USE 						= 11903;
	
	/** 按模块发送待办事件 */
	public static final int POCKET_LINE_HANDLE 				= 12000;
	/** 待办事件处理结束 */
	public static final int POCKET_LINE_HANDLE_END 			= 12001;
	/** 用户待办事件逐条发送 */
	public static final int POCKET_LINE_HANDLE_ONE 			= 12002;
	
	/** 排位赛战斗结束或时间到 */
	public static final int QUALIFYING_FIGHT_END 			= 12100;
	
	/** 任务已接受 */
	public static final int QUEST_ACCEPTED 					= 12200;
	/** 任务对话 */
	public static final int QUEST_DIALOG 					= 12201;
	/** 任务已完成 */
	public static final int QUEST_FINISHED 					= 12202;
	/** 任务目标事件 */
	public static final int QUEST_GOAL 						= 12203;
	/** 任务已提交 */
	public static final int QUEST_SUBMITED 					= 12204;
	/** 任务放弃 */
	public static final int QUEST_GIVE_UP					= 12205;
	/** 环任务接受 */
	public static final int QUEST_CIRCLE_ACCEPT				= 12206;
	/** 环任务完成 */
	public static final int QUEST_CIRCLE_SUBMIT				= 12207;
	
	/** 剧情结束 */
	public static final int SCENARIO_END 					= 12300;
	/** 宝箱开启 */
	public static final int SCENARIO_START 					= 12301;
	/** 斩杀触发剧情 */
	public static final int BEHEADED_SCENARIO				= 12302;
	
	/** 引导完成 */
	public static final int GUIDE_COMPLETE					= 12310;
	
	/** 技能升级 */
	public static final int SKILL_BAR_LEVELUP 				= 12400;
	
	/** 增加一个称号 */
	public static final int TITLE_ADD 						= 12500;
	
	/** 挖宝 */
	public static final int TREASURE_OBJ 					= 12600;
	
	/** 玩家发来聊天协议 */
	public static final int CHAT_SEND						= 12700;
	
	/** 切换天赋页 */
	public static final int TALENT_CHANGE_PAGE				= 12800;
	/** 天赋升级 */
	public static final int TALENT_LEVELUP					= 12801;
	/** 重置天赋 */
	public static final int TALENT_RESET					= 12802;
	
	/** 宠物升级 */
	public static final int PET_LEVEL_UP					= 12901;
	/** 成功抓宠物 */
	public static final int PET_CAPTURE_SUCCESS			= 12902;
	/** 宠物洗髓 */
	public static final int PET_WASH						= 12903;
	
	/** 麻豆相册拍照完成事件 */
	public static final int HUMAN_EASY_PHOTO				= 12950;
	
	/** 连跳结束 */
	public static final int HUMAN_SO_END					= 13000;
	
	/** 角色拍照开始 */
	public static final int HUMAN_PHOTO_START				= 13100;
	/** 角色拍照结束 */
	public static final int HUMAN_PHOTO_OVER				= 13101;
	
	/** 轻功起跳 */
	public static final int HUMAN_FLY_UP					= 13200;
	
	/** 上坐骑 */
	public static final int HUMAN_RIDING_ON					= 13300;
	
	static {
		try {
			check();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}
	
	/**
	 * 检查是有有key重复
	 * @throws Exception
	 */
	public static void check() throws Exception {
		Set<Integer> keys = new HashSet<>();
		for (Field field : EventKey.class.getFields()) {
			if (!Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			
			int key = field.getInt(null);
			if (keys.contains(key)) {
				throw new SysException("Key conflicted, conflict key={}", field.getName());
			}
			
			keys.add(key);
		}
	}
	
}
