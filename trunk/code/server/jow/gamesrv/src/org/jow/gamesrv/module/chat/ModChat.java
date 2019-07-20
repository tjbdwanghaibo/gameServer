package org.jow.gamesrv.module.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jow.common.entity.game.ChatDB;
import org.jow.common.msg.MsgChat.DMSG;
import org.jow.common.msg.MsgChat.DRoom;
import org.jow.common.msg.MsgChat.SCCreatChatRoom;
import org.jow.core.Port;
import org.jow.core.Record;
import org.jow.gamesrv.HumanObject;
import org.jow.gamesrv.module.ModuleHumanBase;

public class ModChat extends ModuleHumanBase {
	
	/** 房间集合 */
	public Map<Long, ChatRoom> rooms = new HashMap<>();
	
	public ModChat(HumanObject humanObj) {
		super(humanObj);
	}
	
	@Override
	public void initData() {
		super.initData();
	}
	
	/**
	 * 玩家登录时，从db载入
	 * @param records
	 */
	public void loadChatFromDB(List<Record> records) {
		for (Record record : records) {
			ChatDB chatHumanObject = new ChatDB(record);
			if(rooms.containsKey(chatHumanObject.getRoomId())) {
				rooms.get(chatHumanObject.getRoomId()).addHuman(chatHumanObject);
			}else {
				ChatRoom room = new ChatRoom(chatHumanObject.getRoomId()); 
				room.addHuman(chatHumanObject);
				rooms.put(chatHumanObject.getRoomId(),room);
			}
		}
	}
	
	/**
	 * 创建新房间
	 * @param humanIds
	 */
	public void creatChatRoom(List<DRoom> humanIds) {
		long roomId = Port.applyId();
		ChatRoom room = new ChatRoom(roomId); 
		for (DRoom dRoom : humanIds) {
			ChatDB chatdb = new ChatDB();
			chatdb.setId(Port.applyId());
			chatdb.setHumanId(dRoom.getHumanId());
			chatdb.setIdentity(ChatRoom.member);
			chatdb.setTime(0);
			chatdb.persist();
			room.addHuman(chatdb);
		}
		//添加自己
		ChatDB chatdb = new ChatDB();
		chatdb.setId(Port.applyId());
		chatdb.setHumanId(humanObj.getId());
		chatdb.setIdentity(ChatRoom.owner);
		chatdb.setTime(0);
		chatdb.persist();
		room.addHuman(chatdb);
		rooms.put(room.getRoomId(), room);
		
		SCCreatChatRoom.Builder resultMsg = SCCreatChatRoom.newBuilder();
		resultMsg.setRoomId(roomId);
		humanObj.sendMsg(resultMsg);
	}
	
	/**
	 * 聊天广播
	 * 
	 * @param humanId 艾塔哪个玩家
	 * @param channel 房间Id
	 * @param content 内容
	 * @param VoiceURL 语音的URL
	 */
	public void doChat(long humanId, int roomId, String content,String VoiceURL) {
		
		DMSG.Builder msgBuilder = DMSG.newBuilder();
		msgBuilder.setChannel(roomId);
		msgBuilder.setContent(content);
		msgBuilder.setTimestamp(Port.getTime());
		msgBuilder.setSendHumanHeadSn(humanObj.getHuman().getHead());
		msgBuilder.setSendHumanSex(humanObj.getHuman().getSex());
		msgBuilder.setSendHumanName(humanObj.getHuman().getName());
		msgBuilder.setVoiceURL(VoiceURL);
		msgBuilder.setReceiveHumanId(humanId);
		
		ChatRoom room =  humanObj.getModChat().getChatRoom(roomId);
		if(roomId > 0) {
			room.sendMsgToAll(msgBuilder);
		}else {
			room.sendMsg(humanId,msgBuilder);
		}
	}
	
	/**
	 * 获得房间
	 * @param roomId
	 * @return
	 */
	public ChatRoom getChatRoom(long roomId) {
		if(!rooms.containsKey(roomId)) {
			return new ChatRoom(roomId);
		}
		return rooms.get(roomId);
	}
}
