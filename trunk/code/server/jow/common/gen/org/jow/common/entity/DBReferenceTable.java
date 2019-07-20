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
	private static String[] ID2OBJECT = new String[24];

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
		OBJECT2ID.put("game_human", 0);
		ID2OBJECT[0] = "game_human";
		OBJECT2ID.put("id", 1);
		ID2OBJECT[1] = "id";
		OBJECT2ID.put("name", 2);
		ID2OBJECT[2] = "name";
		OBJECT2ID.put("level", 3);
		ID2OBJECT[3] = "level";
		OBJECT2ID.put("head", 4);
		ID2OBJECT[4] = "head";
		OBJECT2ID.put("sex", 5);
		ID2OBJECT[5] = "sex";
		OBJECT2ID.put("core_id_allot", 6);
		ID2OBJECT[6] = "core_id_allot";
		OBJECT2ID.put("value", 7);
		ID2OBJECT[7] = "value";
		OBJECT2ID.put("login_account", 8);
		ID2OBJECT[8] = "login_account";
		OBJECT2ID.put("account", 9);
		ID2OBJECT[9] = "account";
		OBJECT2ID.put("password", 10);
		ID2OBJECT[10] = "password";
		OBJECT2ID.put("deviceId", 11);
		ID2OBJECT[11] = "deviceId";
		OBJECT2ID.put("channelId", 12);
		ID2OBJECT[12] = "channelId";
		OBJECT2ID.put("gameIndex", 13);
		ID2OBJECT[13] = "gameIndex";
		OBJECT2ID.put("centralsrv_human", 14);
		ID2OBJECT[14] = "centralsrv_human";
		OBJECT2ID.put("signature", 15);
		ID2OBJECT[15] = "signature";
		OBJECT2ID.put("timeLogin", 16);
		ID2OBJECT[16] = "timeLogin";
		OBJECT2ID.put("timeLogout", 17);
		ID2OBJECT[17] = "timeLogout";
		OBJECT2ID.put("chat_room", 18);
		ID2OBJECT[18] = "chat_room";
		OBJECT2ID.put("roomId", 19);
		ID2OBJECT[19] = "roomId";
		OBJECT2ID.put("humanId", 20);
		ID2OBJECT[20] = "humanId";
		OBJECT2ID.put("identity", 21);
		ID2OBJECT[21] = "identity";
		OBJECT2ID.put("game_chat", 22);
		ID2OBJECT[22] = "game_chat";
		OBJECT2ID.put("time", 23);
		ID2OBJECT[23] = "time";
		// GENERATE END
	}
	
}
