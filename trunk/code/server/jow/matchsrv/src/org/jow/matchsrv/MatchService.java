package org.jow.matchsrv;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jow.common.battle.BattlePlayerVO;
import org.jow.common.battle.BattleUtils;
import org.jow.common.config.ConfBattle;
import org.jow.core.CallPoint;
import org.jow.core.Parms;
import org.jow.core.Port;
import org.jow.core.Service;
import org.jow.core.config.GameConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.config.MatchConfig;
import org.jow.core.gen.proxy.DistrClass;
import org.jow.core.gen.proxy.DistrMethod;
import org.jow.core.support.TickTimer;
import org.jow.core.support.TimeUtils;
import org.jow.gamesrv.RPCProxy.GameManagerServiceProxy;
import org.jow.roomsrv.RPCProxy.RoomServiceProxy;

@DistrClass(importClass = { List.class })
public class MatchService extends Service {

	/** 房间Id分配 */
	private int idAuto;

	/** 实际对战匹配队列<玩法，匹配队列> */
	private Map<Integer, List<MatchingRoomInfo>> matchingRooms = new HashMap<>();

	/** 机器人集合 */
	private RobotDataSet robotSet = new RobotDataSet();

	/** 每秒刷新一下匹配队列 */
	private TickTimer updateTimer = new TickTimer(TimeUtils.SEC);

	public MatchService(Port port) {
		super(port);
	}

	@Override
	public Object getId() {

		return MatchConfig.SERV_MATCH;
	}

	@Override
	protected void pulseOverride() {
		long nowTime = Port.getTime();
		if (updateTimer.isPeriod(nowTime)) {

			for (int battleSn : matchingRooms.keySet()) {
				ConfBattle confBattle = ConfBattle.get(battleSn);
				List<MatchingRoomInfo> roomList = matchingRooms.get(battleSn);
				Iterator<MatchingRoomInfo> iter = roomList.iterator();
				while(iter.hasNext()) {
					MatchingRoomInfo roomInfo = iter.next();
					if(roomInfo.isFull()) {
						iter.remove();
						continue;
					}
					//十秒后不允许加入
					if(nowTime > roomInfo.getTimestamp() + 20 * TimeUtils.SEC) {
						//人数加满到上限
						int robotNum = roomInfo.getSpace();
						if(confBattle != null) {
							robotNum = Math.min(robotNum, confBattle.robotCamps.length);
						}
						if(robotNum > 0){
							List<BattlePlayerVO> robots = robotSet.randomSelect(robotNum, roomInfo.getMembers());
							for (BattlePlayerVO robot : robots) {
								int camp = roomInfo.addBalance(robot.getId());
								
								// 把玩家加入战斗房间
								RoomServiceProxy prx = RoomServiceProxy.newInstance(roomInfo.getRoomPoint());
								prx.enterRoom(roomInfo.getId(), BattleUtils.MODE_NET, camp, Arrays.asList(robot));
							}
						}
						iter.remove();
						continue;
					}
				}
			}
		}
	}
	
	/**
	 * 开始匹配
	 * @param players
	 * @param battleSn
	 */
	@DistrMethod
	public void startMatch(List<BattlePlayerVO> players, int battleSn) {
		//加入机器人
		if(robotSet.isEmpty()) {
			GameManagerServiceProxy gameManagerPrx = GameManagerServiceProxy.newInstance(GameConfig.getGameNodeId(0), 
					JowDistr.PORT_DEFAULT, GameConfig.SERV_GAME_MANAGER);
			gameManagerPrx.getInitRobotData();
			gameManagerPrx.listenResult(this::_result_getInitRobotData);
		}
		
		//更新机器人
		robotSet.add(players);
		
		MatchingRoomInfo matchedRoom = null;
		int camp = 0;
		List<MatchingRoomInfo> roomList = matchingRooms.get(battleSn);
		if(roomList == null) {
			roomList = new LinkedList<MatchingRoomInfo>();
			matchingRooms.put(battleSn, roomList);
		}
		//先尝试匹配
		for (MatchingRoomInfo roomInfo : roomList) {
			int tempCamp = roomInfo.getMaxSpace(players.size());
			if(tempCamp >= 0){
				matchedRoom = roomInfo;
				camp = tempCamp;
				break;
			}
		}
		
		//匹配不成功 创建一个房间
		if(matchedRoom == null) {
			long roomId = allocateRoomId();
			CallPoint roomPoint = BattleUtils.allocateRoomPoint(roomId);
			matchedRoom = new MatchingRoomInfo(roomId, roomPoint);
			camp = 0;
			roomList.add(matchedRoom);
		}
		
		//把玩家加到房间
		for (BattlePlayerVO player : players) {
			matchedRoom.add(camp, player.getId());
		}
		//把玩家加入战斗房间
		RoomServiceProxy prx = RoomServiceProxy.newInstance(matchedRoom.getRoomPoint());
		prx.enterRoom(matchedRoom.getId(), BattleUtils.MODE_NET, camp, players);
	}
	
	private long allocateRoomId() {
		long roomId = ++ this.idAuto;
		return (1L << 32) + roomId;
	}

	private void _result_getInitRobotData(Parms results, Parms context) {
		List<BattlePlayerVO> robots = results.get();
		robotSet.add(robots);
	}

}
