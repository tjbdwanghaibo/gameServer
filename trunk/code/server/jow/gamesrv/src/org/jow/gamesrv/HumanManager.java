package org.jow.gamesrv;

import java.util.List;

import org.jow.common.db.DB;
import org.jow.common.entity.game.ChatDB;
import org.jow.common.event.EventKey;
import org.jow.core.Parms;
import org.jow.core.Record;
import org.jow.core.interfaces.IEvent;
import org.jow.core.support.observer.Listener;
import org.jow.gamesrv.event.OnHumanLogout;
import org.jow.gamesrv.module.chat.ChatManager;
import org.jow.gamesrv.support.Log;


public class HumanManager {

	/**
	 * 加载各模块数据
	 * @param humanObject
	 */
	public static void load(HumanObject humanObj) {
		loadHumanData(humanObj, ChatDB.tableName, ChatDB.K.humanId, humanObj.getId());
		
		Log.game.info("玩家数据加载完成！");
	}
	
	private static void loadHumanData(HumanObject humanObj, String tableName, Object...params) {
		DB db = DB.newInstance(tableName);
		db.findBy(false, params);
		db.listenResult(HumanManager::_result_loadHumanData,"humanObj", humanObj, "tableName", tableName);
		humanObj.loadingNum ++ ;
	}

	
	private static void _result_loadHumanData(Parms results, Parms context) {
		
		String tableName = context.get("tableName");
		HumanObject humanObj = context.get("humanObj");
		
		List<Record> records = results.get();
		switch(tableName) {
		
		case ChatDB.tableName:
			ChatManager.loaderChatDB(humanObj, records);
			break;
		
		}

		humanObj.loadingNum -- ;
		if(humanObj.loadingNum <= 0) {
			humanObj.onLoadDataFinished();
		}
	}
	
	
	@Listener(EventKey.HUMAN_LOGOUT)
	public static void onHumanLogout(IEvent evt) {
		OnHumanLogout event = (OnHumanLogout)evt;
		HumanObject humanObj = event.getHumanObj();
		Log.game.info("玩家登出。 name = {}", humanObj.getHuman().getName());
	}
	
}
