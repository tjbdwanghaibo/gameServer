package org.jow.roomsrv;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.jow.common.battle.BattlePlayerVO;
import org.jow.core.Port;
import org.jow.core.Service;
import org.jow.core.config.RoomConfig;
import org.jow.core.gen.proxy.DistrMethod;
import org.jow.core.support.TickTimer;
import org.jow.core.support.TimeUtils;
import org.jow.roomsrv.lua.LuaBattle;
import org.jow.roomsrv.support.Log;
import org.jow.roomsrv.support.RoomExtendMsgHandler;

/**
 * 房间管理服
 * 
 * @author Yangxianmeng
 *
 */
public class RoomService extends Service {
	
	/** 协议消息类 */
	private static RoomExtendMsgHandler msgHandler = new RoomExtendMsgHandler();
	/** lua战斗文件主入口 */
	private static String LUA_MAIN = "ServerMain";
	
	/** 房间列表 */
	private Map<Long, RoomObject> roomObjs = new HashMap<>();
	
	/** 战斗帧时间间隔 */
	private TickTimer frameTimer = new TickTimer(67);
	/** 每秒检查房间 */
	private TickTimer checkTimer = new TickTimer(TimeUtils.SEC);
	/** lua战斗接口 */
	private long luaState;
	
	public RoomService(Port port) {
		super(port);
		
		//在LUA_PATH中查找ServerMian.lua文件
		String[] paths = StringUtils.split(LUA_MAIN, ";");
		for (String string : paths) {
			String mainPath = StringUtils.replace(string, "?", LUA_MAIN);
			if(!new File(mainPath).exists()) {
				continue;
			}
			
			luaState = LuaBattle.jniLuaNewState(mainPath);
			break;
		}
	}

	@Override
	public Object getId() {
		
		return RoomConfig.SERV_ROOM;
	}
	
	@Override
	protected void pulseOverride() {
		long now = port.getTimeCurrent();
		//清除超时房间
		if(checkTimer.isPeriod(now)) {
			Iterator<Entry<Long, RoomObject>> iter = roomObjs.entrySet().iterator();
			while(iter.hasNext()) {
				RoomObject roomObj = iter.next().getValue();
				if(roomObj.isTimeout(now)) {
					iter.remove();
					roomObj.destory();
					Log.room.info("Room timeout, id={}", roomObj.getId());
				}
			}
		}
		
		if(frameTimer.isPeriod(now)) {
			for(RoomObject roomObj : roomObjs.values()) {
				roomObj.pulse(now);
			}
		}
	}
	
	@DistrMethod
	public void msgHandler(long roomId, long humanId, byte[] msgbuf) {
		RoomObject roomObj = roomObjs.get(roomId);
		if(roomObj == null) {
			Log.room.warn("RoomObject not exist, roomId = {}", roomId);
			return;
		}
		msgHandler.handle(msgbuf, "roomObj", roomObj, "humanId", humanId);
	}
	
	@DistrMethod
	public void connClosed(long roomId, long humanId) {
		RoomObject roomObj = roomObjs.get(roomId);
		if(roomObj == null) {
			Log.room.info("RoomObject already destory, roomId = {}", roomId);
			return;
		}
		RoomMemberObject member = roomObj.getMemberObj(humanId);
		if(member != null) {
			member.getPlayer().setConnPoint(null);
		}
	}
	
	@DistrMethod
	public void connDropped(long roomId, long humanId) {
		RoomObject roomObj = roomObjs.get(roomId);
		if (roomObj == null) {
			Log.room.info("RoomObject already destory, roomId={}", roomId);
			return;
		}
		
		RoomMemberObject memberObj = roomObj.getMemberObj(humanId);
		if (memberObj != null) {
			memberObj.getPlayer().setConnPoint(null);
		}
	}
	
	/**
	 * 进入房间
	 * @param roomId
	 * @param mode
	 * @param camp
	 * @param players
	 */
	@DistrMethod
	public void enterRoom(long roomId, int mode, int camp, List<BattlePlayerVO> players) {
		RoomObject roomObj = roomObjs.get(roomId);
		if(roomObj == null) {		//房间不存在就创建一个
			roomObj = new RoomObject(roomId, this, camp);
			Log.room.info("Create room , id = {}", roomId);
			roomObjs.put(roomId, roomObj);
		}
		//进入房间
		for(BattlePlayerVO player : players) {
			roomObj.addMember(camp, player);
			Log.room.info("Enter room, roomId={}, humanId={}", roomId, player.getId());
		}
	}
	
	@DistrMethod
	public void battleOver(long roomId) {
		RoomObject roomObj = roomObjs.get(roomId);
		if (roomObj == null) {
			Log.room.warn("RoomObject not exist, roomId = {}", roomId);
			return;
		}
		roomObj.battleOver();
		roomObj.destory();
		
		Log.room.info("Over room, id={}", roomId);

	}
	
	
	@DistrMethod
	public void battleLevel(long humanId, long roomId) {
		RoomObject roomObj = roomObjs.get(roomId);
		if (roomObj == null) {
			return;
		}
//		String result = roomObj.getresult();
//		roomObj.battleLevel(humanId, result);
		Log.room.info("{} Level room, id={}",humanId, roomId);
	}
	
	public long getLuaState() {
		return luaState;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
