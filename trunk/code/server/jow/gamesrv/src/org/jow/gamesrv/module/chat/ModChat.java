package org.jow.gamesrv.module.chat;

import java.util.List;

import org.jow.core.Record;
import org.jow.gamesrv.HumanObject;
import org.jow.gamesrv.module.ModuleHumanBase;

/**
 * 聊天逻辑
 * 
 * @author Yangxianmeng
 *
 */
public class ModChat extends ModuleHumanBase {

	public ModChat(HumanObject humanObject) {
		super(humanObject);
	}

	
	
	
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		super.initData();
	}
	
	
	@Override
	public void pulse(long now) {
		// TODO Auto-generated method stub
		super.pulse(now);
	}





	public void loadRecentChatFromDB(List<Record> records) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
