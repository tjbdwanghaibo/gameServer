package org.jow.matchsrv;

import java.util.ArrayList;
import java.util.List;

import org.jow.common.battle.BattleUtils;
import org.jow.core.CallPoint;
import org.jow.core.Port;

/**
 * 正在匹配的房间信息
 * 
 * @author Yangxianmeng
 *
 */
public class MatchingRoomInfo {

	/** 房间id */
	private long id;

	/** 房间成员 */
	private List<List<Long>> members = new ArrayList<>();

	/** 房间所在的RoomService的CallPoint */
	private CallPoint roomPoint;

	/** 创建的时间戳 */
	private long timestamp;

	public MatchingRoomInfo(long id, CallPoint roomPoint) {
		this.id = id;
		this.roomPoint = roomPoint;
		this.timestamp = Port.getTime();
		for (int camp = 0; camp < BattleUtils.CAMP_NUM; camp++) {
			members.add(new ArrayList<>());
		}
	}

	/**
	 * 获得指定阵营空位数
	 * 
	 * @param camp
	 * @return
	 */
	public int getSpace(int camp) {
		return BattleUtils.HUMAN_NUM_PER_CAMP - members.get(camp).size();
	}

	public int getMaxSpace(int playerNum) {
		int maxSpace = 0;
		int camp = -1;
		for (int i = 0; i < BattleUtils.CAMP_NUM; i++) {
			int space = getSpace(i);
			if (space >= playerNum && space > maxSpace) {
				maxSpace = space;
				camp = i;
			}
		}
		return camp;
	}

	/**
	 * 获取该房间总的空位数
	 * 
	 * @return
	 */
	public int getSpace() {
		int space = 0;
		for (int camp = 0; camp < BattleUtils.CAMP_NUM; ++camp) {
			space += getSpace(camp);
		}

		return space;
	}

	/**
	 * 加入指定阵营，外层需要先调用getSpace来判断空位数够不够
	 * 
	 * @param camp
	 * @param humanId
	 */
	public void add(int camp, long humanId) {
		members.get(camp).add(humanId);
	}

	/**
	 * 判断房间是否已经满了
	 * 
	 * @return
	 */
	public boolean isFull() {
		for (List<Long> campMembers : members) {
			if (campMembers.size() < BattleUtils.HUMAN_NUM_PER_CAMP) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 加入一个机器人，保持阵营平衡，外层先调用isFull来判断空位数够不够
	 * @param humanId
	 * @return
	 */
	public int addBalance(long humanId) {
		int campBingo = -1;
		int campHumanNum = Integer.MAX_VALUE;
		for (int camp = 0; camp < BattleUtils.CAMP_NUM; camp++) {
			int humanNum = members.get(camp).size();
			if(humanNum < campHumanNum) {
				campBingo = camp;
				campHumanNum = humanNum;
			}
		}
		add(campBingo, campHumanNum);
		return campBingo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Long> getMembers() {
		List<Long> result = new ArrayList<>();
		for (List<Long> campMembers : members) {
			result.addAll(campMembers);
		}
		return result;
	}


	public CallPoint getRoomPoint() {
		return roomPoint;
	}

	public void setRoomPoint(CallPoint roomPoint) {
		this.roomPoint = roomPoint;
	}

	public long getTimestamp() {
		return timestamp;
	}
}
