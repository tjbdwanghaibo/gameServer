package org.jow.roomsrv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jow.common.battle.BattleEndInfo;
import org.jow.common.battle.BattlePlayerVO;
import org.jow.common.battle.BattleUtils;
import org.jow.common.msg.MsgBattle.CSBattleDo;
import org.jow.common.msg.MsgBattle.DBattleCMD;
import org.jow.common.msg.MsgBattle.SCBattleDo;
import org.jow.common.msg.MsgBattle.SCBattleOver;
import org.jow.common.msg.MsgBattle.SCBattleStageSwitch;
import org.jow.common.msg.MsgBattle.SCBattleStart;
import org.jow.common.msg.MsgBattle.SCTick;
import org.jow.common.msg.MsgIds;
import org.jow.connsrv.RPCProxy.ConnServiceProxy;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.Chunk;
import org.jow.core.Port;
import org.jow.core.config.ConnConfig;
import org.jow.core.support.JSONUtils;
import org.jow.core.support.TimeUtils;
import org.jow.core.support.random.RandomUtils;
import org.jow.core.support.tuple.TwoTuple;
import org.jow.gamesrv.RPCProxy.GameServiceProxy;
import org.jow.roomsrv.support.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 一个房间
 * 
 * @author Yangxianmeng
 *
 */
public class RoomObject {

	/** 玩家Id分配 */
	private int idAuto = 0;

	/** 房间Id */
	private long id;
	/** 所在服务 */
	private RoomService service;
	/** 当前 帧号 */
	private int frame = 0;
	/** 副本Sn */
	private int scenSn = 1001;
	/** 房间模式 */
	private int mode;
	/** 随机种子 */
	private int seed = RandomUtils.nextInt(Integer.MAX_VALUE);
	/** 房间创建的时间戳 */
	private long timestamp = Port.getTime();

	/** 历史消息 */
	private List<DBattleCMD> history = new ArrayList<>();
	/** 当前帧消息 */
	private List<DBattleCMD> current = new ArrayList<>();
	/** 房间成员列表 */
	private Map<Long, RoomMemberObject> memberObjs = new HashMap<>();

	/** connPort -> connection */
	private Map<TwoTuple<String, String>, List<Object>> port2Connection = new HashMap<>();

	/** lua 中的战斗编号 */
	private String jiesuan = "[]";

	public RoomObject(long id, RoomService service, int mode) {
		this.id = id;
		this.service = service;
		this.mode = mode;
	}

	public void pulse(long now) {
		// 服务器脚本跑一帧
		current.clear();
		// 增加帧号
		frame++;

		// 广播帧消息
		SCTick.Builder msgTick = SCTick.newBuilder();
		msgTick.setFrame(frame);

		broadCast(MsgIds.SCTick, new Chunk(msgTick));
	}

	private void broadCast(int msgId, Chunk msgbuf) {
		// 根据connPort循环发送到connPort上唯一服务ConnService,再由ConnService统一发送客户端
		for (Entry<TwoTuple<String, String>, List<Object>> entry : port2Connection.entrySet()) {
			TwoTuple<String, String> protInfo = entry.getKey();
			List<Object> connections = entry.getValue();

			// 发送消息
			ConnServiceProxy proxy = ConnServiceProxy.newInstance(protInfo.first, protInfo.second,
					ConnConfig.SERV_CONN);
			proxy.sendMsg(connections, msgId, msgbuf);
		}
	}

	private void addCmd(DBattleCMD dCMD) {
		// 加到当前帧
		current.add(dCMD);
		// 加到历史
		history.add(dCMD);
		// 立刻广播到客户端
		SCBattleDo.Builder msgReply = SCBattleDo.newBuilder();
		msgReply.addCmds(dCMD);
		broadCast(MsgIds.SCBattleDo, new Chunk(msgReply));

	}

	public void addCurrent(long huamanId, CSBattleDo msg) {
		RoomMemberObject memberObj = memberObjs.get(huamanId);
		if (memberObj == null) {
			Log.room.error("Human not exit, id={}", huamanId);
			return;
		}

		DBattleCMD.Builder dCMD = DBattleCMD.newBuilder();
		dCMD.setFrame(frame);
		dCMD.setCmdType(msg.getCmdType());
		// 玩家的index
		dCMD.setArg1(memberObj.getIndex());
		if (msg.hasArg2()) {
			dCMD.setArg2(msg.getArg2());
		}
		if (msg.hasArg3()) {
			dCMD.setArg3(msg.getArg3());
		}
		if (msg.hasArg4()) {
			dCMD.setArg4(msg.getArg4());
		}
		if (msg.hasArg5()) {
			dCMD.setArg5(msg.getArg5());
		}
		addCmd(dCMD.build());
	}

	/**
	 * 加入一个玩家
	 * 
	 * @param camp
	 * @param player
	 */
	public void addMember(int camp, BattlePlayerVO player) {
		RoomMemberObject memberObj = memberObjs.get(player.getId());
		if (memberObj != null) {
			Log.room.error("Human already exist, id = {}", player.getId());
			return;
		}

		// 更新连接服务器的状态
		if (player.getConnPoint() != null) {
			ConnectionProxy connPrx = ConnectionProxy.newInstance(player.getConnPoint());
			connPrx.statusBattle(service.getCallPoint(), id);
		}

		// 更新逻辑服的状态
		if (player.getGamePoint() != null) {
			GameServiceProxy prx = GameServiceProxy.newInstance(player.getGamePoint());
			prx.modOnBattleStart(player.getId(), service.getCallPoint(), id);
		}

		// 创建玩家
		memberObj = new RoomMemberObject();
		memberObj.setIndex(++idAuto);
		memberObj.setPlayer(player);
		memberObj.setCamp(camp);
		memberObj.setFigthing(false);
		memberObjs.put(player.getId(), memberObj);

		// 发送切图消息
		if (player.getConnPoint() != null) {
			SCBattleStageSwitch.Builder msg = SCBattleStageSwitch.newBuilder();
			msg.setSn(scenSn);
			ConnectionProxy connProxy = ConnectionProxy.newInstance(player.getConnPoint());
			connProxy.sendMsg(MsgIds.SCBattleStageSwitch, new Chunk(msg));
		} else { // 机器人

			// 玩家加入操作
			DBattleCMD.Builder dCMD = DBattleCMD.newBuilder();
			dCMD.setFrame(frame);
			dCMD.setCmdType(BattleUtils.CMD_JOIN);
			dCMD.setArg1(memberObj.getIndex());
			if (player.getCharacter() > 0) {
				dCMD.setArg2(player.getCharacter());
			} else {
				dCMD.setArg2(BattleUtils.PRO1);
			}
			dCMD.setArg3(memberObj.getCamp() + 1);
			dCMD.setArg4(1);
			dCMD.setArg5(player.buildDBattlePlayer());
			addCmd(dCMD.build());
			memberObj.setFigthing(true);
		}
	}

	public void startBattle(long humanId) {
		RoomMemberObject memberObj = memberObjs.get(humanId);
		if (memberObj == null) {
			Log.room.error("Human not exist, id = {}", humanId);
			return;
		}

		if (memberObj.isFigthing()) {
			Log.room.error("Human isFinhting already ! id = {}", humanId);
			return;
		}

		BattlePlayerVO player = memberObj.getPlayer();
		// 发送战斗开始
		if (player.getConnPoint() != null) {
			SCBattleStart.Builder msgStart = SCBattleStart.newBuilder();
			msgStart.setSn(scenSn);
			msgStart.setMode(mode);
			msgStart.setSeed(seed);
			msgStart.setPlayerId(memberObj.getIndex());
			msgStart.setFrame(frame);
			msgStart.addAllCmds(history);

			ConnectionProxy pxy = ConnectionProxy.newInstance(player.getConnPoint());
			pxy.sendMsg(MsgIds.SCBattleStart, new Chunk(msgStart));
		}

		// 玩家加入操作
		DBattleCMD.Builder dCMD = DBattleCMD.newBuilder();
		dCMD.setFrame(frame);
		dCMD.setCmdType(BattleUtils.CMD_JOIN);
		dCMD.setArg1(memberObj.getIndex());
		if (player.getCharacter() > 0) {
			dCMD.setArg2(player.getCharacter());
		} else {
			dCMD.setArg2(BattleUtils.PRO1);
		}
		dCMD.setArg3(memberObj.getCamp() + 1);
		if (player.getConnPoint() == null) {
			dCMD.setArg4(1);
		}
		dCMD.setArg5(player.buildDBattlePlayer());
		addCmd(dCMD.build());
	}

	public long getId() {
		return id;
	}

	public boolean isTimeout(long now) {
		return now > timestamp + 6 * TimeUtils.MIN;
	}

	public void destory() {
		// LuaBattle.jniDestoryBattle(service.getLuaState(), luaBattleId);
	}

	public RoomMemberObject getMemberObj(long humanId) {
		return memberObjs.get(humanId);
	}

	// 战斗结束
	public void battleOver() {
		// 结算协议
		SCBattleOver.Builder msgOver = SCBattleOver.newBuilder();
		broadCast(MsgIds.SCBattleOver, new Chunk(msgOver));
		JSONArray jieSuanInfo = new JSONArray();
		if (jiesuan != null) {
			jieSuanInfo = JSONUtils.parseArray(jiesuan);
		}

		for (RoomMemberObject memberObj : memberObjs.values()) {
			// 更新链接状态
			if (memberObj.getPlayer().getConnPoint() != null) {
				ConnectionProxy prx = ConnectionProxy.newInstance(memberObj.getPlayer().getConnPoint());
				prx.statusBattle(null, -1);
			}
			// 通知逻辑服结算信息
			if (memberObj.getPlayer().getGamePoint() != null) {
				JSONObject rankInfo = new JSONObject();
				for (int i = 0; i < jieSuanInfo.size(); i++) {
					JSONObject obj = jieSuanInfo.getJSONObject(i);
					if (memberObj.getIndex() == obj.getIntValue("ctrlId")) {
						rankInfo = obj;
						break;
					}
				}
				BattleEndInfo info = new BattleEndInfo();
				for (RoomMemberObject object : memberObjs.values()) {
					if (object.getPlayer().getId() == memberObj.getPlayer().getId()) {
						continue;
					}
					if (object.getCamp() != memberObj.getCamp()) {
						info.getEnemy().add(object.getPlayer().getId());
					} else {
						info.getTeamMate().add(object.getPlayer().getId());
					}
				}
				rankInfo.put("mode", mode);
				GameServiceProxy prx = GameServiceProxy.newInstance(memberObj.getPlayer().getGamePoint());
				prx.modOnBattleOver(memberObj.getPlayer().getId(), info, rankInfo.toJSONString());
			}
		}

	}

	
	public void battleLevel() {
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
