package org.jow.common.battle;

import org.jow.core.CallPoint;
import org.jow.core.config.JowDistr;
import org.jow.core.config.JowDistr.Room;
import org.jow.core.config.RoomConfig;

/**
 * 战斗相关常量
 * 
 * @author Yangxianmeng
 *
 */
public class BattleUtils {
	
	/** 战斗常量 */
	/** zhengc */
	public static final int MODE_NONE = 0;
	
	/** 单机 */
	public static final int MODE_CONSOLE = 1;
	/** 联网 */
	public static final int MODE_NET = 2;
	/** 录像 */
	public static final int MODE_REPLAY = 3;
	
	/** 一个房间的阵营数 */
	public static final int CAMP_NUM = 3;
	/** 每个阵营人数 */
	public static final int HUMAN_NUM_PER_CAMP = 3;

	/** 阵营 */
	/** 无阵营 */
	public static final int CAMP_NONE	= 0;
	/** 蓝 */
	public static final int CAMP_BLUE	= 1;
	/** 绿 */
	public static final int CAMP_GREEN	= 2;
	/** 红 */
	public static final int CAMP_RED	= 3;
	
	/** 玩家操作类型 */
	public static final int CMD_NONE	= 0;
	public static final int CMD_MOVE	= 1;
	/** 加入 */
	public static final int CMD_JOIN	= 2;
	public static final int CMD_LEAVE	= 3;
	public static final int CMD_SETAI	= 4;
	public static final int CMD_MOVEEX	= 5;
	public static final int CMD_STOPMOVE= 6;
	public static final int CMD_SKILL	= 7;
	public static final int CMD_SELECTSKILL	= 8;
	public static final int CMD_REVIVE	= 9;
	public static final int CMD_PHIZL	= 10;
	public static final int CMD_FOOT	= 11;
	
	/** 职业 */
	/** 男 */
	public static final int PRO1		= 1000;
	/** 女 */
	public static final int PRO2		= 1001;
	
	
	/***
	 * 负载均衡到一个RoomPort上
	 * 
	 * @param id
	 * @return
	 */
	public static CallPoint allocateRoomPoint(long id) {
		
		int portNum = 0;
		for(Room room : JowDistr.roomConfigs) {
			portNum += room.portStartupNumRoom;
		}
		
		int portIndex = (int) (id % portNum);
		int nodeIndex = 0;
		
		for(; nodeIndex < JowDistr.roomConfigs.size(); ++ nodeIndex) {
			Room room = JowDistr.roomConfigs.get(nodeIndex);
			if(portIndex >= room.portStartupNumRoom) {
				portIndex -= room.portStartupNumRoom;
			}else {
				break;
			}
		}
		return new CallPoint(RoomConfig.NODE_ROOM_PREFIX + nodeIndex, RoomConfig.PORT_ROOM_PERFIX + portIndex, RoomConfig.SERV_ROOM);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
