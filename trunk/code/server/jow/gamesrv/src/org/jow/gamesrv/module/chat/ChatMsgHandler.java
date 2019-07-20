package org.jow.gamesrv.module.chat;

import java.util.List;

import org.jow.common.msg.MsgChat.CSChat;
import org.jow.common.msg.MsgChat.CSCreatChatRoom;
import org.jow.common.msg.MsgChat.DRoom;
import org.jow.common.msg.MsgIds;
import org.jow.core.support.observer.MsgReceiver;
import org.jow.gamesrv.HumanObject;
import org.jow.gamesrv.support.MsgParamGame;

public class ChatMsgHandler {

	@MsgReceiver(MsgIds.CSChat)
	public static void onCSChat(MsgParamGame param) {
		
		CSChat msg = param.getMsg();
		HumanObject humanObj = param.getHumanObj();
	
		String content = msg.getContent();
		
		humanObj.getModChat()
		.doChat(msg.getTargetKey(), msg.getChannel(), content, msg.getVoiceURL());
	}
	
	@MsgReceiver(MsgIds.CSCreatChatRoom)
	public static void onCSCreatRoom(MsgParamGame param) {
		
		CSCreatChatRoom msg = param.getMsg();
		HumanObject humanObj = param.getHumanObj();
		
		List<DRoom> room = msg.getMsgList();
		
		humanObj.getModChat().creatChatRoom(room);
	}
	
	
	
	
	
}
