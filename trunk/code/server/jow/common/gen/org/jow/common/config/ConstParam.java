package org.jow.common.config;

import org.jow.core.gen.excel.ExcelUtils;

/**
 * @author gaopan
 *
 * Param表中的常量
 */
public class ConstParam {
		/** 最大同时登陆人数 */
	public static int LOGIN_MAX_QUEUE;
	/** 最大同时在线人数 */
	public static int LOGIN_MAX_ONLINE;
	/** 给登陆队列中角色的提示间隔(秒) */
	public static int LOGIN_INTERVAL_TIPS;
	/** 关注列表上限 */
	public static int FRIEND_NUM_MAX;
	/** 默认的小屋角色 */
	public static int DEFAULT_HUT_ITEM_CHARACTER;
	/** 最近战斗列表上限 */
	public static int RECENTPLAYER_NUM_MAX;
	/** 玩家身上的邮件最大数量 */
	public static int MAIL_MAX_NUMBER;
	/** 玩家身上的邮件失效自动删除时间：有附件（单位天） */
	public static int MAIL_EXPIREDTIME_ATTACHED;
	/** 玩家身上的邮件失效自动删除时间：无附件，已读（单位天） */
	public static int MAIL_EXPIREDTIME_NOATTACH_READ;
	/** 玩家身上的邮件失效自动删除时间：无附件，未读（单位天） */
	public static int MAIL_EXPIREDTIME_NOATTACH_NOREAD;
	/** 一封邮件里最大附件数量，超出时邮件将分拆 */
	public static int MAIL_ITEMS_MAX_NUMBER;
	/** 公共邮件默认失效时间（单位天）。在失效前玩家可以收到一次该邮件，收到后在玩家身上的停留时间走玩家身上邮件的规则 */
	public static int SYSMAIL_EXPIREDTIME_DEFAULT;
	/** 公共邮件失效时间上限（单位天）。公共邮件有指定失效时间的，会被约束在这个值以内 */
	public static int SYSMAIL_EXPIREDTIME_MAX;
	/** 公共邮件最大数量。超过后无法发送新的公共邮件 */
	public static int SYSMAIL_MAX_NUMBER;
	/** 单次上线发送完全相同的消息（经过屏蔽字过滤的；含标点符号；频道不限）达到条数时会被禁言 */
	public static int CHAT_CONTENT_SAME_MAX;
	/** 发送相同消息而被禁言时，提示玩家的经验理由字符串（会拼到str表的禁言提示里发送给玩家） */
	public static String CHAT_CONTENT_SAME_FORBIDDEN_REASON;
	/** 两次发言之间的间隔时间（单位是秒） */
	public static int CHAT_CD;
	/** 需要判定发言间隔时间的频道号 */
	public static int[] CHAT_CD_CHANNEL;
	/** 允许在世界频道中发消息的最低等级（大于等于这个等级可以在世界聊天中发消息） */
	public static int CHAT_JOIN_WORLD_LEVEL;
	/** 喇叭广播需要消耗的道具sn */
	public static int CHAT_LOADSPEAKER_ITEM_SN;
	/** 玩家被禁言后的解封时间（单位小时） */
	public static int CHAT_FORBIDDEN_TIME;
	/** 玩家每日发送好友离线留言总数量(玩家可以发送的离线消息数量比这个数值多1) */
	public static int CHAT_OFFLINE_FRIEND_MSG_COUNT;
	/** 玩家每日发送陌生人离线留言总数量(玩家可以发送的离线消息数量比这个数值多1) */
	public static int CHAT_OFFLINE_STRANGER_MSG_COUNT;
	/** 玩家离线留言保存天数 */
	public static int CHAT_OFFLINE_MSG_CLEAR_DAY;
	/** 玩家名字最大长度（中文2 英文1） */
	public static int HUMAN_INFO_NAME_LENGTH;
	/** 玩家签名最大长度（中文2 英文1） */
	public static int HUMAN_INFO_SIGNATURE_LENGTH;
	
	public static void reload() {
		/** 最大同时登陆人数 */
		LOGIN_MAX_QUEUE = ExcelUtils.toInt("LOGIN_MAX_QUEUE", ConfParam.get("LOGIN_MAX_QUEUE").value);
		/** 最大同时在线人数 */
		LOGIN_MAX_ONLINE = ExcelUtils.toInt("LOGIN_MAX_ONLINE", ConfParam.get("LOGIN_MAX_ONLINE").value);
		/** 给登陆队列中角色的提示间隔(秒) */
		LOGIN_INTERVAL_TIPS = ExcelUtils.toInt("LOGIN_INTERVAL_TIPS", ConfParam.get("LOGIN_INTERVAL_TIPS").value);
		/** 关注列表上限 */
		FRIEND_NUM_MAX = ExcelUtils.toInt("FRIEND_NUM_MAX", ConfParam.get("FRIEND_NUM_MAX").value);
		/** 默认的小屋角色 */
		DEFAULT_HUT_ITEM_CHARACTER = ExcelUtils.toInt("DEFAULT_HUT_ITEM_CHARACTER", ConfParam.get("DEFAULT_HUT_ITEM_CHARACTER").value);
		/** 最近战斗列表上限 */
		RECENTPLAYER_NUM_MAX = ExcelUtils.toInt("RECENTPLAYER_NUM_MAX", ConfParam.get("RECENTPLAYER_NUM_MAX").value);
		/** 玩家身上的邮件最大数量 */
		MAIL_MAX_NUMBER = ExcelUtils.toInt("MAIL_MAX_NUMBER", ConfParam.get("MAIL_MAX_NUMBER").value);
		/** 玩家身上的邮件失效自动删除时间：有附件（单位天） */
		MAIL_EXPIREDTIME_ATTACHED = ExcelUtils.toInt("MAIL_EXPIREDTIME_ATTACHED", ConfParam.get("MAIL_EXPIREDTIME_ATTACHED").value);
		/** 玩家身上的邮件失效自动删除时间：无附件，已读（单位天） */
		MAIL_EXPIREDTIME_NOATTACH_READ = ExcelUtils.toInt("MAIL_EXPIREDTIME_NOATTACH_READ", ConfParam.get("MAIL_EXPIREDTIME_NOATTACH_READ").value);
		/** 玩家身上的邮件失效自动删除时间：无附件，未读（单位天） */
		MAIL_EXPIREDTIME_NOATTACH_NOREAD = ExcelUtils.toInt("MAIL_EXPIREDTIME_NOATTACH_NOREAD", ConfParam.get("MAIL_EXPIREDTIME_NOATTACH_NOREAD").value);
		/** 一封邮件里最大附件数量，超出时邮件将分拆 */
		MAIL_ITEMS_MAX_NUMBER = ExcelUtils.toInt("MAIL_ITEMS_MAX_NUMBER", ConfParam.get("MAIL_ITEMS_MAX_NUMBER").value);
		/** 公共邮件默认失效时间（单位天）。在失效前玩家可以收到一次该邮件，收到后在玩家身上的停留时间走玩家身上邮件的规则 */
		SYSMAIL_EXPIREDTIME_DEFAULT = ExcelUtils.toInt("SYSMAIL_EXPIREDTIME_DEFAULT", ConfParam.get("SYSMAIL_EXPIREDTIME_DEFAULT").value);
		/** 公共邮件失效时间上限（单位天）。公共邮件有指定失效时间的，会被约束在这个值以内 */
		SYSMAIL_EXPIREDTIME_MAX = ExcelUtils.toInt("SYSMAIL_EXPIREDTIME_MAX", ConfParam.get("SYSMAIL_EXPIREDTIME_MAX").value);
		/** 公共邮件最大数量。超过后无法发送新的公共邮件 */
		SYSMAIL_MAX_NUMBER = ExcelUtils.toInt("SYSMAIL_MAX_NUMBER", ConfParam.get("SYSMAIL_MAX_NUMBER").value);
		/** 单次上线发送完全相同的消息（经过屏蔽字过滤的；含标点符号；频道不限）达到条数时会被禁言 */
		CHAT_CONTENT_SAME_MAX = ExcelUtils.toInt("CHAT_CONTENT_SAME_MAX", ConfParam.get("CHAT_CONTENT_SAME_MAX").value);
		/** 发送相同消息而被禁言时，提示玩家的经验理由字符串（会拼到str表的禁言提示里发送给玩家） */
		CHAT_CONTENT_SAME_FORBIDDEN_REASON = ExcelUtils.toString("CHAT_CONTENT_SAME_FORBIDDEN_REASON", ConfParam.get("CHAT_CONTENT_SAME_FORBIDDEN_REASON").value);
		/** 两次发言之间的间隔时间（单位是秒） */
		CHAT_CD = ExcelUtils.toInt("CHAT_CD", ConfParam.get("CHAT_CD").value);
		/** 需要判定发言间隔时间的频道号 */
		CHAT_CD_CHANNEL = ExcelUtils.toIntArray("CHAT_CD_CHANNEL", ConfParam.get("CHAT_CD_CHANNEL").value, 0);
		/** 允许在世界频道中发消息的最低等级（大于等于这个等级可以在世界聊天中发消息） */
		CHAT_JOIN_WORLD_LEVEL = ExcelUtils.toInt("CHAT_JOIN_WORLD_LEVEL", ConfParam.get("CHAT_JOIN_WORLD_LEVEL").value);
		/** 喇叭广播需要消耗的道具sn */
		CHAT_LOADSPEAKER_ITEM_SN = ExcelUtils.toInt("CHAT_LOADSPEAKER_ITEM_SN", ConfParam.get("CHAT_LOADSPEAKER_ITEM_SN").value);
		/** 玩家被禁言后的解封时间（单位小时） */
		CHAT_FORBIDDEN_TIME = ExcelUtils.toInt("CHAT_FORBIDDEN_TIME", ConfParam.get("CHAT_FORBIDDEN_TIME").value);
		/** 玩家每日发送好友离线留言总数量(玩家可以发送的离线消息数量比这个数值多1) */
		CHAT_OFFLINE_FRIEND_MSG_COUNT = ExcelUtils.toInt("CHAT_OFFLINE_FRIEND_MSG_COUNT", ConfParam.get("CHAT_OFFLINE_FRIEND_MSG_COUNT").value);
		/** 玩家每日发送陌生人离线留言总数量(玩家可以发送的离线消息数量比这个数值多1) */
		CHAT_OFFLINE_STRANGER_MSG_COUNT = ExcelUtils.toInt("CHAT_OFFLINE_STRANGER_MSG_COUNT", ConfParam.get("CHAT_OFFLINE_STRANGER_MSG_COUNT").value);
		/** 玩家离线留言保存天数 */
		CHAT_OFFLINE_MSG_CLEAR_DAY = ExcelUtils.toInt("CHAT_OFFLINE_MSG_CLEAR_DAY", ConfParam.get("CHAT_OFFLINE_MSG_CLEAR_DAY").value);
		/** 玩家名字最大长度（中文2 英文1） */
		HUMAN_INFO_NAME_LENGTH = ExcelUtils.toInt("HUMAN_INFO_NAME_LENGTH", ConfParam.get("HUMAN_INFO_NAME_LENGTH").value);
		/** 玩家签名最大长度（中文2 英文1） */
		HUMAN_INFO_SIGNATURE_LENGTH = ExcelUtils.toInt("HUMAN_INFO_SIGNATURE_LENGTH", ConfParam.get("HUMAN_INFO_SIGNATURE_LENGTH").value);
	}
}
