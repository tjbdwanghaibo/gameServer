package org.jow.gamesrv;

import java.util.List;

import org.jow.common.db.DB;
import org.jow.common.entity.game.RecentChatDataDB;
import org.jow.core.Record;
import org.jow.core.support.Param;
import org.jow.gamesrv.module.friend.FriendManager;

/**
 * 玩家管理器
 * 
 * @author Yangxianmeng
 *
 */
public class HumanManager {

	/**
	 * 加载玩家各个模块
	 * @param humanObject
	 */
	public static void load(HumanObject humanObject) {
		
		loadHumanData(humanObject, RecentChatDataDB.tableName, RecentChatDataDB.K.humanId, humanObject.getId());
	}

	private static void loadHumanData(HumanObject humanObject, String tableName, Object...params) {
		DB db = DB.newInstance(tableName);
		db.findBy(false, params);
		db.listenResult(HumanManager::_result_loadHumanData, "humanObject", humanObject, "tableName", tableName);
		humanObject.loadingNum++;
	}
	
	private static void _result_loadHumanData(Param results, Param context) {
		String tableName = context.get("tableName");
		HumanObject humanObj = context.get("humanObject");
		
		List<Record> records = results.get();
		switch (tableName) {
		case RecentChatDataDB.tableName:
			FriendManager.loaderRecentChatDataDB(humanObj, records);
			break;

		default:
			break;
		}
		
		humanObj.loadingNum--;
		if(humanObj.loadingNum <= 0) {
			humanObj.onLoadDataFinished();
		}
	}
}
