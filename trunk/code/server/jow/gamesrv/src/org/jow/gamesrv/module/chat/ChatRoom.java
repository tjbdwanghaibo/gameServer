package org.jow.gamesrv.module.chat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jow.centralsrv.RPCProxy.HumanGlobalServiceProxy;
import org.jow.common.entity.game.ChatDB;
import org.jow.common.game.HumanOnlineInfo;
import org.jow.common.msg.MsgIds;
import org.jow.common.msg.MsgChat.DMSG.Builder;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.Chunk;
import org.jow.core.Parms;
import org.jow.core.config.CentralConfig;

public class ChatRoom {
	/** 群主 */
	public static final int owner = 1;
	
	/** 管理员 */
	public static final int administrator = 2;
	
	/** 成员 */
	public static final int member = 3;
	
	private long roomId;
	
	public Map<Long, ChatDB> humanGroup = new ConcurrentHashMap<>();
	
	public ChatRoom(long roomId) {
		this.setRoomId(roomId);
	}
	
	/**
	 * 加入房间
	 */
	public void addHuman(ChatDB chatHumanObj) {
		if(humanGroup.containsKey(chatHumanObj.getHumanId())) {
			return;
		}	
		humanGroup.put(chatHumanObj.getHumanId(), chatHumanObj);
	}

	/**
	 * 移除此人
	 * @param chatHumanObj
	 */
	public void removeHuman(long administratorId, long humanId) {	
		if(!humanGroup.containsKey(administratorId) || !humanGroup.containsKey(humanId)) {
			return;
		}
		
		if(humanGroup.get(administratorId).getIdentity() == ChatRoom.administrator 
				|| humanGroup.get(administratorId).getIdentity() == ChatRoom.owner) {
			humanGroup.remove(humanId);
		}
	}
	
	
	/**
	 * 退出群聊
	 */
	public void quitRoom(long humanId) {
		if(!humanGroup.containsKey(humanId)) {
			return;
		}
		humanGroup.remove(humanId);
	}

	/**
	 * 广播给所有人
	 * @param msgBuilder
	 */
	public void sendMsgToAll(Builder msgBuilder) {
		if(humanGroup.isEmpty()) {
			return;
		}
		for(ChatDB human : humanGroup.values()) {
			sendMsg(human.getHumanId(), msgBuilder);
		}
	}
	
	/**
	 * 广播给某一人
	 * @param humanId
	 * @param msgBuilder
	 */
	public void sendMsg(long humanId, Builder msgBuilder) {
//		HumanGlobalServiceProxy humanGlobalPrx = HumanGlobalServiceProxy.newInstance("MainStartup", 
		HumanGlobalServiceProxy humanGlobalPrx = HumanGlobalServiceProxy.newInstance(CentralConfig.NODE_CENTRAL, 
				CentralConfig.PORT_HUMAN_GLOBAL, CentralConfig.SERV_HUMAN_GLOBAL);
		humanGlobalPrx.getOnline(humanId);
		humanGlobalPrx.listenResult(this::_result_onlineHuman, "msg", msgBuilder);
	}
	
	private void _result_onlineHuman(Parms results, Parms context) {
		HumanOnlineInfo humanOnlineInfo = results.get();
		if(humanOnlineInfo == null) {
			return;
		}
		ConnectionProxy prx = ConnectionProxy.newInstance(humanOnlineInfo.getConnPoint());
		Builder msg = context.get("msg");
		prx.sendMsg(MsgIds.SCChat, new Chunk(msg));
	}
	
	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	
}
