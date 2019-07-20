package org.jow.gamesrv.module.chat;

import java.util.List;
import org.jow.core.Record;
import org.jow.gamesrv.HumanObject;

public class ChatManager {
	
	public static void loaderChatDB(HumanObject humanObj, List<Record> records) {
		humanObj.getModChat().loadChatFromDB(records);
	}
}
