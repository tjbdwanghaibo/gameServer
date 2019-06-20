package org.jow.common.entity;

import java.util.HashMap;
import java.util.Map;

import org.jow.core.ReferenceTable;

/**
 * @author gaopan
 *
 * 用来优化Entity的序列化<br>
 * 注：该代码是自动生成的，请勿手动编辑<br>
 * 注2：该代码是增量更新，id号依次增长，永远不会删除老的；这样使得在跨服时，有的服务器更新了版本而有的没更新版本，也能做到兼容
 */
public class DBReferenceTable extends ReferenceTable {
	/** object -> referId */
	private static Map<String, Integer> OBJECT2ID = new HashMap<>();
	/** referId -> object */
	private static String[] ID2OBJECT = new String[114];

	@Override
	public int getId(Object obj) {
		Integer referId = OBJECT2ID.get(obj);
		return referId == null ? -1 : referId.intValue();
	}

	@Override
	public Object getObject(int id) {
		try {
			return ID2OBJECT[id];
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	static {
		// GENERATE BEGIN
		OBJECT2ID.put("", 0);
		ID2OBJECT[0] = "";
		OBJECT2ID.put("core_id_allot", 1);
		ID2OBJECT[1] = "core_id_allot";
		OBJECT2ID.put("id", 2);
		ID2OBJECT[2] = "id";
		OBJECT2ID.put("value", 3);
		ID2OBJECT[3] = "value";
		OBJECT2ID.put("game_card_data", 4);
		ID2OBJECT[4] = "game_card_data";
		OBJECT2ID.put("humanId", 5);
		ID2OBJECT[5] = "humanId";
		OBJECT2ID.put("sn", 6);
		ID2OBJECT[6] = "sn";
		OBJECT2ID.put("level", 7);
		ID2OBJECT[7] = "level";
		OBJECT2ID.put("debrisCount", 8);
		ID2OBJECT[8] = "debrisCount";
		OBJECT2ID.put("game_human", 9);
		ID2OBJECT[9] = "game_human";
		OBJECT2ID.put("name", 10);
		ID2OBJECT[10] = "name";
		OBJECT2ID.put("head", 11);
		ID2OBJECT[11] = "head";
		OBJECT2ID.put("sex", 12);
		ID2OBJECT[12] = "sex";
		OBJECT2ID.put("signature", 13);
		ID2OBJECT[13] = "signature";
		OBJECT2ID.put("dan", 14);
		ID2OBJECT[14] = "dan";
		OBJECT2ID.put("star", 15);
		ID2OBJECT[15] = "star";
		OBJECT2ID.put("protectScore", 16);
		ID2OBJECT[16] = "protectScore";
		OBJECT2ID.put("attention", 17);
		ID2OBJECT[17] = "attention";
		OBJECT2ID.put("fans", 18);
		ID2OBJECT[18] = "fans";
		OBJECT2ID.put("like", 19);
		ID2OBJECT[19] = "like";
		OBJECT2ID.put("diamond", 20);
		ID2OBJECT[20] = "diamond";
		OBJECT2ID.put("gold", 21);
		ID2OBJECT[21] = "gold";
		OBJECT2ID.put("openFunctions", 22);
		ID2OBJECT[22] = "openFunctions";
		OBJECT2ID.put("keyCount", 23);
		ID2OBJECT[23] = "keyCount";
		OBJECT2ID.put("openBoxCount", 24);
		ID2OBJECT[24] = "openBoxCount";
		OBJECT2ID.put("skillPoint", 25);
		ID2OBJECT[25] = "skillPoint";
		OBJECT2ID.put("gameCount", 26);
		ID2OBJECT[26] = "gameCount";
		OBJECT2ID.put("gameTimeTotal", 27);
		ID2OBJECT[27] = "gameTimeTotal";
		OBJECT2ID.put("killCount", 28);
		ID2OBJECT[28] = "killCount";
		OBJECT2ID.put("deadCount", 29);
		ID2OBJECT[29] = "deadCount";
		OBJECT2ID.put("maxScore", 30);
		ID2OBJECT[30] = "maxScore";
		OBJECT2ID.put("championCount", 31);
		ID2OBJECT[31] = "championCount";
		OBJECT2ID.put("equipedCards", 32);
		ID2OBJECT[32] = "equipedCards";
		OBJECT2ID.put("game_round_data", 33);
		ID2OBJECT[33] = "game_round_data";
		OBJECT2ID.put("rank", 34);
		ID2OBJECT[34] = "rank";
		OBJECT2ID.put("mode", 35);
		ID2OBJECT[35] = "mode";
		OBJECT2ID.put("assistCount", 36);
		ID2OBJECT[36] = "assistCount";
		OBJECT2ID.put("timestamp", 37);
		ID2OBJECT[37] = "timestamp";
		OBJECT2ID.put("game_season_data", 38);
		ID2OBJECT[38] = "game_season_data";
		OBJECT2ID.put("num", 39);
		ID2OBJECT[39] = "num";
		OBJECT2ID.put("login_account", 40);
		ID2OBJECT[40] = "login_account";
		OBJECT2ID.put("account", 41);
		ID2OBJECT[41] = "account";
		OBJECT2ID.put("password", 42);
		ID2OBJECT[42] = "password";
		OBJECT2ID.put("deviceId", 43);
		ID2OBJECT[43] = "deviceId";
		OBJECT2ID.put("channelId", 44);
		ID2OBJECT[44] = "channelId";
		OBJECT2ID.put("gameIndex", 45);
		ID2OBJECT[45] = "gameIndex";
		OBJECT2ID.put("timeLogin", 46);
		ID2OBJECT[46] = "timeLogin";
		OBJECT2ID.put("timeLogout", 47);
		ID2OBJECT[47] = "timeLogout";
		OBJECT2ID.put("demo_human_item", 48);
		ID2OBJECT[48] = "demo_human_item";
		OBJECT2ID.put("state", 49);
		ID2OBJECT[49] = "state";
		OBJECT2ID.put("game_fans_data", 50);
		ID2OBJECT[50] = "game_fans_data";
		OBJECT2ID.put("fansId", 51);
		ID2OBJECT[51] = "fansId";
		OBJECT2ID.put("game_laud_data", 52);
		ID2OBJECT[52] = "game_laud_data";
		OBJECT2ID.put("laudNum", 53);
		ID2OBJECT[53] = "laudNum";
		OBJECT2ID.put("game_friend_data", 54);
		ID2OBJECT[54] = "game_friend_data";
		OBJECT2ID.put("friendId", 55);
		ID2OBJECT[55] = "friendId";
		OBJECT2ID.put("centralsrv_human", 56);
		ID2OBJECT[56] = "centralsrv_human";
		OBJECT2ID.put("game_history_data", 57);
		ID2OBJECT[57] = "game_history_data";
		OBJECT2ID.put("robot", 58);
		ID2OBJECT[58] = "robot";
		OBJECT2ID.put("synthesis", 59);
		ID2OBJECT[59] = "synthesis";
		OBJECT2ID.put("unlock", 60);
		ID2OBJECT[60] = "unlock";
		OBJECT2ID.put("carry", 61);
		ID2OBJECT[61] = "carry";
		OBJECT2ID.put("timeLimit", 62);
		ID2OBJECT[62] = "timeLimit";
		OBJECT2ID.put("skillType", 63);
		ID2OBJECT[63] = "skillType";
		OBJECT2ID.put("containerType", 64);
		ID2OBJECT[64] = "containerType";
		OBJECT2ID.put("exp", 65);
		ID2OBJECT[65] = "exp";
		OBJECT2ID.put("bindingDiamond", 66);
		ID2OBJECT[66] = "bindingDiamond";
		OBJECT2ID.put("game_hut_item_data", 67);
		ID2OBJECT[67] = "game_hut_item_data";
		OBJECT2ID.put("use", 68);
		ID2OBJECT[68] = "use";
		OBJECT2ID.put("forever", 69);
		ID2OBJECT[69] = "forever";
		OBJECT2ID.put("game_recent_game", 70);
		ID2OBJECT[70] = "game_recent_game";
		OBJECT2ID.put("playerId", 71);
		ID2OBJECT[71] = "playerId";
		OBJECT2ID.put("time", 72);
		ID2OBJECT[72] = "time";
		OBJECT2ID.put("enemy", 73);
		ID2OBJECT[73] = "enemy";
		OBJECT2ID.put("demo_human_mail", 74);
		ID2OBJECT[74] = "demo_human_mail";
		OBJECT2ID.put("read", 75);
		ID2OBJECT[75] = "read";
		OBJECT2ID.put("pickup", 76);
		ID2OBJECT[76] = "pickup";
		OBJECT2ID.put("sender", 77);
		ID2OBJECT[77] = "sender";
		OBJECT2ID.put("senderName", 78);
		ID2OBJECT[78] = "senderName";
		OBJECT2ID.put("channel", 79);
		ID2OBJECT[79] = "channel";
		OBJECT2ID.put("removetime", 80);
		ID2OBJECT[80] = "removetime";
		OBJECT2ID.put("sysMailId", 81);
		ID2OBJECT[81] = "sysMailId";
		OBJECT2ID.put("receiver", 82);
		ID2OBJECT[82] = "receiver";
		OBJECT2ID.put("title", 83);
		ID2OBJECT[83] = "title";
		OBJECT2ID.put("detail", 84);
		ID2OBJECT[84] = "detail";
		OBJECT2ID.put("delTimestamp", 85);
		ID2OBJECT[85] = "delTimestamp";
		OBJECT2ID.put("itemJSON", 86);
		ID2OBJECT[86] = "itemJSON";
		OBJECT2ID.put("demo_human_mail_system", 87);
		ID2OBJECT[87] = "demo_human_mail_system";
		OBJECT2ID.put("vipMin", 88);
		ID2OBJECT[88] = "vipMin";
		OBJECT2ID.put("vipMax", 89);
		ID2OBJECT[89] = "vipMax";
		OBJECT2ID.put("levelMin", 90);
		ID2OBJECT[90] = "levelMin";
		OBJECT2ID.put("levelMax", 91);
		ID2OBJECT[91] = "levelMax";
		OBJECT2ID.put("serverId", 92);
		ID2OBJECT[92] = "serverId";
		OBJECT2ID.put("humanIdLimit", 93);
		ID2OBJECT[93] = "humanIdLimit";
		OBJECT2ID.put("serviceIdLimit", 94);
		ID2OBJECT[94] = "serviceIdLimit";
		OBJECT2ID.put("reserved", 95);
		ID2OBJECT[95] = "reserved";
		OBJECT2ID.put("delId", 96);
		ID2OBJECT[96] = "delId";
		OBJECT2ID.put("hasInform", 97);
		ID2OBJECT[97] = "hasInform";
		OBJECT2ID.put("vipLevel", 98);
		ID2OBJECT[98] = "vipLevel";
		OBJECT2ID.put("serviceId", 99);
		ID2OBJECT[99] = "serviceId";
		OBJECT2ID.put("demo_mail_system", 100);
		ID2OBJECT[100] = "demo_mail_system";
		OBJECT2ID.put("game_human_offline_msg", 101);
		ID2OBJECT[101] = "game_human_offline_msg";
		OBJECT2ID.put("senderId", 102);
		ID2OBJECT[102] = "senderId";
		OBJECT2ID.put("msgContent", 103);
		ID2OBJECT[103] = "msgContent";
		OBJECT2ID.put("voiceUrl", 104);
		ID2OBJECT[104] = "voiceUrl";
		OBJECT2ID.put("saveTime", 105);
		ID2OBJECT[105] = "saveTime";
		OBJECT2ID.put("timeSealChat", 106);
		ID2OBJECT[106] = "timeSealChat";
		OBJECT2ID.put("timeStartSealChat", 107);
		ID2OBJECT[107] = "timeStartSealChat";
		OBJECT2ID.put("descSealChat", 108);
		ID2OBJECT[108] = "descSealChat";
		OBJECT2ID.put("notifyToUserSealChat", 109);
		ID2OBJECT[109] = "notifyToUserSealChat";
		OBJECT2ID.put("demo_human_blacklist", 110);
		ID2OBJECT[110] = "demo_human_blacklist";
		OBJECT2ID.put("blockId", 111);
		ID2OBJECT[111] = "blockId";
		OBJECT2ID.put("blockTime", 112);
		ID2OBJECT[112] = "blockTime";
		OBJECT2ID.put("game_recent_chat", 113);
		ID2OBJECT[113] = "game_recent_chat";
		// GENERATE END
	}
	
}
