package org.jow.common.constant;

/**
 * @author System
 *
 * 服务端错误码大全，错误码统一为5位数，0表示成功
 */
public class ErrorCode {
	static final int OK = 0;
	
		/** 工程师正在熬夜加班中… */
	public static final int DEV = 1;
	/** 年 */
	public static final int YEAR = 2;
	/** 月 */
	public static final int MONTH = 3;
	/** 日 */
	public static final int DAY = 4;
	/** 天 */
	public static final int TIAN = 5;
	/** 时 */
	public static final int HOUR = 6;
	/** 分 */
	public static final int MINUTE = 7;
	/** 秒 */
	public static final int SECOND = 8;
	/** 永久 */
	public static final int FOREVER = 9;
	/** 零 */
	public static final int ZERO = 10;
	/** 一 */
	public static final int ONE = 11;
	/** 二 */
	public static final int TWO = 12;
	/** 三 */
	public static final int THREE = 13;
	/** 四 */
	public static final int FOUR = 14;
	/** 五 */
	public static final int FIVE = 15;
	/** 六 */
	public static final int SIX = 16;
	/** 七 */
	public static final int SEVEN = 17;
	/** 八 */
	public static final int EIGHT = 18;
	/** 九 */
	public static final int NINE = 19;
	/** %02d:%02d:%02d */
	public static final int TIME_HMS = 20;
	/** %02d:%02d */
	public static final int TIME_MS = 21;
	/** %01d天%02d时%02d分 */
	public static final int TIME_DHM = 22;
	/** %02d时%02d分 */
	public static final int TIME_HM = 23;
	/** 已过期 */
	public static final int OUT_OF_DATE = 24;
	/** 万 */
	public static final int NUMBER_MILLION = 25;
	/** 正在登陆中，请勿重复登陆 */
	public static final int LOGIN_REPEAT_LOGIN = 10000;
	/** 服务器已达到最大在线人数 */
	public static final int LOGIN_SERVER_FULL = 10001;
	/** 账号或密码为空，登陆失败 */
	public static final int LOGIN_ACCOUNT_PASSWORD_NULL = 10002;
	/** 密码错误，登陆失败 */
	public static final int LOGIN_PASSWORD_ERROR = 10003;
	/** 未开服 */
	public static final int LOGIN_SERVER_UNOPEN = 10004;
	/** 重复登陆 */
	public static final int LOGIN_ALREADY_LOGIN = 10005;
	/** 再其他地方已经登陆，踢出其他，请重新登陆 */
	public static final int LOGIN_ALREADY_IN_OTHER_PLACE = 10006;
	/** 您的账号在其他地方登陆，您被踢下线 */
	public static final int LOGIN_BE_KICKED = 10007;
	/** 该功能尚未开启 */
	public static final int HUMAN_FUNC_CLOSED = 10100;
	/** 目标玩家不在线 */
	public static final int HUMAN_NOT_ONLINE = 10101;
	/** 玩家已经在队伍中，创建失败 */
	public static final int TEAM_CREATE_HAS_TEAM = 10200;
	/** 不再队伍中，无法邀请其他玩家 */
	public static final int TEAM_INVITE_REQ_IN_TEAM = 10201;
	/** 不是队长，无法邀请其他玩家 */
	public static final int TEAM_INVITE_REQ_LEADER = 10202;
	/** 被邀请者已经在组队状态 */
	public static final int TEAM_INVITED_HAS_TEAM = 10203;
	/** 玩家已经在队伍中，无法再次加入 */
	public static final int TEAM_JOIN_ALREADY_IN_TEAM = 10204;
	/** 队伍不存在，无法加入 */
	public static final int TEAM_JOIN_TEAM_NOT_EXIST = 10205;
	/** 队伍已经满员，无法加入 */
	public static final int TEAM_JOIN_TEAM_FULL = 10206;
	/** 玩家已经退队了 */
	public static final int TEAM_LEAVE_ALREADY = 10207;
	/** 玩家不再队伍中，无法踢出 */
	public static final int TEAM_KICK_NO_TEAM = 10208;
	/** 不是队长，无法踢出 */
	public static final int TEAM_KICK_REQ_LEADER = 10209;
	/** 玩家不再队伍中，无法开始匹配 */
	public static final int TEAM_MATCH_NOT_IN_TEAM = 10210;
	/** 队伍不存在，可能战斗已经开始了 */
	public static final int TEAM_MATCH_TEAM_NOT_EXIST = 10211;
	/** 只有队长能发起匹配 */
	public static final int TEAM_MATCH_REQ_LEADER = 10212;
	/** 请先组队 */
	public static final int TEAM_HUMAN_NO_TEAM = 10213;
	/** 已经准备过 */
	public static final int TEAM_PREPARE_ALREADY = 10214;
	/** 请先准备 */
	public static final int TEAM_NO_PREPARE = 10215;
	/** 玩家正在战斗中，无法加入 */
	public static final int TEAM_INVITED_IN_ROOM = 10216;
	/** 无法开始战斗，进先组队 */
	public static final int BATTLE_886_REQ_TEAM = 10300;
	/** 没有权限开始战斗 */
	public static final int BATTLE_886_REQ_LEADER = 10301;
	/** 元宝不足 */
	public static final int CURRENCY_SPEND_REQ_DIAMOND = 20000;
	/** 金币不足 */
	public static final int CURRENCY_SPEND_REQ_GOLD = 20001;
	/** 增加或减少负数 */
	public static final int CURRENCY_NUM_NEGATIVE = 20002;
	/** 货币物品id不存在 */
	public static final int CURRENCY_UNKNOWN_ITEM_SN = 20003;
	/** {0}数量不足 */
	public static final int ITEM_REQ_NUM = 21000;
	/** 物品数量非法 */
	public static final int ITEM_NUM_NEGATIVE = 21001;
	/** 物品不存在 */
	public static final int ITEM_NOT_EXIST = 21002;
	/** 物品配置不存在 */
	public static final int ITEM_CONFIG_NOT_EXIST = 21003;
	/** 关注已达上限 */
	public static final int FRIEND_NUM_MAX = 22001;
	/** 错误操作 */
	public static final int FRIEND_ERROR = 22002;
	/** 重复关注 */
	public static final int FRIEND_REPEAT = 22003;
	/** 玩家不存在 */
	public static final int FRIEND_NO_FIND = 22004;
	/** 不能关注自己 */
	public static final int FRIEND_ADD_ONESELF = 22005;
	/** 不能给自己点赞 */
	public static final int FRIEND_LAUD_ONESELF = 22006;
	/** 已经关注该玩家 */
	public static final int FRIEND_ADD_ALREADY = 22007;
	/** 碎片不足 */
	public static final int CARD_DEBRIS_REQ_NUM = 23000;
	/** 配置错误 */
	public static final int CARD_NO_CONFIG = 23001;
	/** 已经合成 */
	public static final int CARD_SYNTHESIS = 23002;
	/** 已经解锁 */
	public static final int CARD_UNLOCKED = 23003;
	/** 请先解锁 */
	public static final int CARD_NO_UNLOCKED = 23004;
	/** 请先合成 */
	public static final int CARD_NO_SYNTHESIS = 23005;
	/** 等级已达上限 */
	public static final int CARD_LEVEL_MAX = 23006;
	/** 附近没有队友 */
	public static final int BATTLE_NO_FRIEND_NEARBY = 23007;
	/** 请先购买 */
	public static final int HUT_ITEM_NO_BUY = 24000;
	/** 已过期，请续费 */
	public static final int HUT_ITEM_TIME_OUT = 24001;
	/** 已经永久拥有，无需重复购买 */
	public static final int HUT_BUY_ITEM_IS_FOREVER = 24002;
	/** 配置错误 */
	public static final int HUT_CONFIG_ERROR = 24003;
	/** 已经使用 */
	public static final int HUT_USE_ALREADY = 24004;
	/** 邮件已过期 */
	public static final int MAIL_TIME_OUT = 24101;
	/** 没有可以提取的邮件 */
	public static final int MAIL_PICKUP_NO_MAIL = 24102;
	/** 没有可以提取的附件 */
	public static final int MAIL_PICKUP_NO_ATTACHMENT = 24103;
	/** 背包已满领取附件失败 */
	public static final int MAIL_PICKUP_FULL_BAG = 24104;
	/** 所有已读邮件已删除 */
	public static final int MAIL_DELETE_ALL_SUCCESS = 24105;
	/** 有未领取的附件，无法删除 */
	public static final int MAIL_DELETE_FAILURE = 24106;
	/** 对方已经把你加入黑名单，发送邮件失败 */
	public static final int MAIL_BLACK_LIST = 24107;
	/** 邮件发送成功 */
	public static final int MAIL_NOTIFY_SEND_MAIL_OK = 24108;
	/** 邮件删除成功 */
	public static final int MAIL_DELETE_SUCCEE = 24109;
	/** 聊天频道不存在 */
	public static final int CHAT_CHANNEL_ERROR = 24200;
	/** 你没有加入组队，不能进行组队聊天 */
	public static final int CHAT_NOT_JOIN_TEAM = 24201;
	/** 你没有加入帮派，不能进行帮派聊天 */
	public static final int CHAT_NOT_JOIN_FAMILY = 24202;
	/** 10级，才可以使用世界频道哦 */
	public static final int CHAT_NOT_JOIN_WORLD = 24203;
	/** 目标玩家不在线 */
	public static final int CHAT_NOT_ONLINE = 24204;
	/** 您发言过快，请等待{0}秒后再发言 */
	public static final int CHAT_FAST = 24205;
	/** {0}，{1}小时后解封 */
	public static final int CHAT_FORBIDDEN = 24206;
	/** 您已拉黑对方/被对方拉黑，无法发送消息 */
	public static final int CHAT_BLACKLIST = 24207;
	/** 喇叭道具不足 */
	public static final int CHAT_NO_LOADSPEAKER = 24208;
	/** 使用喇叭时出错了 */
	public static final int CHAT_USE_LOADSPEAKER_FAILED = 24209;
	/** 当前场景不允许聊天 */
	public static final int CHAT_NOT_VAILD_MAP = 24210;
	/** {0}，{1}分钟后解封 */
	public static final int CHAT_FORBIDDEN_MIN = 24211;
	/** 今天给好友的留言数量已达上限！ */
	public static final int CHAT_FIREND_OFFLINE_MSG_LIMIT = 24212;
	/** 今天给陌生人的留言数量已达上限！ */
	public static final int CHAT_STRANGER_OFFLINE_MSG_LIMIT = 24213;
	/** 我的衣橱 */
	public static final int STR_HOUSE_TAB_SHOP = 1001000;
	/** 时尚商店 */
	public static final int STR_HOUSE_TAB_YICHU = 1001001;
	/** 我的衣橱 */
	public static final int STR_HOUSE_TAB_BAG_YICHU = 1001002;
	/** 武器仓库 */
	public static final int STR_HOUSE_TAB_BAG_WUQI = 1001003;
	/** 时尚商店 */
	public static final int STR_HOUSE_TAB_SHOP_YICHU = 1001004;
	/** 武器商店 */
	public static final int STR_HOUSE_TAB_SHOP_WUQI = 1001005;
	/** 角色 */
	public static final int STR_HOUSE_TAB_YICHU_ROLE = 1001006;
	/** 头饰 */
	public static final int STR_HOUSE_TAB_YICHU_HEAD = 1001007;
	/** 服饰 */
	public static final int STR_HOUSE_TAB_YICHU_CLOTHES = 1001008;
	/** 套装 */
	public static final int STR_HOUSE_TAB_YICHU_SUIT = 1001009;
	/** 全部 */
	public static final int STR_HOUSE_TAB_WUQI_ALL = 1001010;
	/** 巨斧 */
	public static final int STR_HOUSE_TAB_WUQI_JUFU = 1001011;
	/** 弓箭 */
	public static final int STR_HOUSE_TAB_WUQI_GONGJIAN = 1001012;
	/** 火药 */
	public static final int STR_HOUSE_TAB_WUQI_HUOYAO = 1001013;
	/** 购买 */
	public static final int STR_BUY = 1001014;
	/** 续费 */
	public static final int STR_CHARGE = 1001015;
	/** 通用描述信息 */
	public static final int STR_COLLECT_UNIVERSAL = 1001016;
	/** 巨斧描述信息 */
	public static final int STR_COLLECT_AXE = 1001017;
	/** 弓箭描述信息 */
	public static final int STR_COLLECT_BOW = 1001018;
	/** 火药描述信息 */
	public static final int STR_COLLECT_GUNPOWER = 1001019;
	/** 宠物描述信息 */
	public static final int STR_COLLECT_PET = 1001020;
	/** 表情描述信息 */
	public static final int STR_COLLECT_EMOTICON = 1001021;
	/** 脚印描述信息 */
	public static final int STR_COLLECT_FOOTPRINT = 1001022;
	/** 购买成功！ */
	public static final int STR_BUY_SUCC = 1001023;
	/** 试穿中… */
	public static final int STR_HOUSE_TRY_PUTON = 1001024;
	/** 穿戴中… */
	public static final int STR_HOUSE_PUTON = 1001025;
	/** 道具栏已满 */
	public static final int BATTLE_Prop_Full = 1001026;
	/** 试穿中…\n点击使用可穿戴 */
	public static final int STR_HOUSE_TRY_PUTON2 = 1001027;
	/** 体力不足 */
	public static final int STR_PowerEmpty = 1001028;
	/** %s使用了预言，所有%s将会%s。 */
	public static final int STR_USE_PREDICT = 1001029;
	/** 蘑菇仓库被抢，快去阻击蘑菇大盗。 */
	public static final int STR_MUSHROOM_ROBBER_APPEAR = 1001030;
	/** 第%s波大盗开始逃跑，快追。 */
	public static final int STR_MUSHROOM_ROBBER_APPEAR_INDEX = 1001031;
	/** 无法连接服务器 */
	public static final int STR_DISCONNECT = 1001032;
	/** 连接失败，请重新登录 */
	public static final int STR_RELOGIN = 1001033;
	/** 连接服务器失败，请重新连接 */
	public static final int STR_RECONNECT = 1001034;
	/** 与服务器断开连接，正在重连中… */
	public static final int STR_CONNECTING = 1001035;
	/** 昵称不能为空 */
	public static final int STR_CHANGENAME_NULL = 1011001;
	/** 昵称不能超过%s个字符 */
	public static final int STR_CHANGENAME_MAX = 1011002;
	/** 签名不能超过%s个字符 */
	public static final int STR_SIGNATURE_MAX = 1011003;
	/** 这个人人很懒~什么都没有留下~ */
	public static final int STR_HUMANDES_NULL = 1011004;
	/** 段位降低时,若保护分满%s分.则会保护段位不下降，并扣除全部保护分数.保护分数满%s分，可额外提升一颗星. */
	public static final int STR_RANK_SCORE_RULE = 1021000;
	/** %s段位升降星规则： */
	public static final int STR_RANK_RULE1 = 1021001;

}
