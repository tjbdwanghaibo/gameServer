package org.jow.gamesrv.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jow.common.battle.BattlePlayerVO;
import org.jow.common.db.DB;
import org.jow.common.entity.game.HumanDB;
import org.jow.common.game.HumanCentralInfo;
import org.jow.common.game.HumanInfo;
import org.jow.common.game.HumanOnlineInfo;
import org.jow.common.support.utils.DBUtils;
import org.jow.core.CallPoint;
import org.jow.core.Parms;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.RecordTransient;
import org.jow.core.Service;
import org.jow.core.config.GameConfig;
import org.jow.core.config.LoginConfig;
import org.jow.core.db.DBKey;
import org.jow.core.gen.proxy.DistrClass;
import org.jow.core.gen.proxy.DistrMethod;
import org.jow.core.support.TickTimer;
import org.jow.core.support.TimeUtils;
import org.jow.gamesrv.support.Log;
import org.jow.loginsrv.RPCProxy.AccountServiceProxy;

@DistrClass(importClass= {CallPoint.class, HumanInfo.class, Parms.class, List.class, HumanCentralInfo.class})
public class GameManagerService extends Service{

	
	/** 在线玩家信息 */
	private Map<Long, HumanOnlineInfo> onlineHumans = new HashMap<>();
	/** 本服全部玩家基本信息 */
	private Map<Long, HumanInfo> humans = new HashMap<>();
	
	/** 每2秒ping一下account service */
	private TickTimer pingTimer = new TickTimer(TimeUtils.SEC * 2);
	
	
	public GameManagerService(Port port) {
		super(port);
	}

	@Override
	public Object getId() {
		return GameConfig.SERV_GAME_MANAGER;
	}

	@Override
	public void startup() {
		super.startup();
		
		port.addTask(new PortTask() {
			
			@Override
			public void execute(Port port) {
				initHumanInfo();
			}
		});
	}

	/**
	 * 记载全服玩家数据
	 */
	private void initHumanInfo() {
		Log.game.info("Load human info...");
		
		List<String> columns = Arrays.asList(HumanDB.K.id,
				HumanDB.K.head,
				HumanDB.K.level,
				HumanDB.K.name,
				HumanDB.K.sex,
				HumanDB.K.signature,
				HumanDB.K.timeLogin,
				HumanDB.K.timeLogout
				);
		DBUtils.findByWithPaging(HumanDB.tableName, columns, (r) -> {
			HumanInfo humanInfo = new HumanInfo();
			humanInfo.setHead(r.get(HumanDB.K.head));
			humanInfo.setId(r.get(HumanDB.K.id));
			humanInfo.setName(r.get(HumanDB.K.name));
			humanInfo.setLevel(r.get(HumanDB.K.level));
			humanInfo.setSex(r.get(HumanDB.K.sex));
			
			humans.put(humanInfo.getId(), humanInfo);
		});
		Log.game.info("Load human info finished!");
	}
	
	
	@Override
	public void pulseOverride() {
		// TODO Auto-generated method stub
		super.pulseOverride();
		// ping account service
		if (pingTimer.isPeriod(port.getTimeCurrent())) {
			AccountServiceProxy prx = AccountServiceProxy.newInstance(LoginConfig.NODE_LOGIN, 
//			AccountServiceProxy prx = AccountServiceProxy.newInstance("MainStartup", 
					LoginConfig.PORT_ACCOUNT, LoginConfig.SERV_ACCOUNT);
			prx.gamePing(GameConfig.GAME_INDEX, onlineHumans.size());
		}
	}
	
	@DistrMethod
	public void humanLogin(long humanId, CallPoint connPoint, CallPoint gamePoint) {
		HumanOnlineInfo info = new HumanOnlineInfo();
		info.setId(humanId);
		info.setConnPoint(connPoint);
		info.setGamePoint(gamePoint);
		onlineHumans.put(humanId, info);
	}
	
	@DistrMethod
	public void humanLogout(long humanId) {
		onlineHumans.remove(humanId);
	}
	
	@DistrMethod
	public void isLogin(long humanId) {
		HumanOnlineInfo info = onlineHumans.get(humanId);
		if (info == null) {
			port.returns("isLogin", false);
		} else {
			port.returns("isLogin", true, "connPoint", info.getConnPoint());
		}
	}
	
	@DistrMethod
	public void humanCreate(HumanInfo humanInfo) {
//		HistoryData historyData = new HistoryData();
//		historyData.setDeadCount(0);
//		historyData.setGameCount(0);
//		historyData.setGameTimeTotal(0);
//		historyData.setKillCount(0);
//		historyData.setMaxScore(0);
//		historyData.setId(humanInfo.getId());
//		humanInfo.setHistoryData(historyData);
//		historyData.persist();
		humans.put(humanInfo.getId(), humanInfo);
	}
	
	/**
	 * 加载机器人数据
	 */
	@DistrMethod
	public void getInitRobotData() {
		long pid = port.createReturnAsync();
		DB db = DB.newInstance(HumanDB.tableName);
		db.findBy(false,  DBKey.COLUMN, Arrays.asList(HumanDB.K.id, HumanDB.K.name), HumanDB.K.robot, true);
		db.listenResult(this::_result_getInitRobotData, "pid", pid);
		
	}
	
	private void _result_getInitRobotData(Parms results, Parms context) {
		List<RecordTransient> records = results.get();
		long pid = context.get("pid");
		
		List<BattlePlayerVO> robots = new ArrayList<>();
		for (RecordTransient record : records) {
			long humanId = record.get(HumanDB.K.id);
			String name = record.get(HumanDB.K.name);
			
			BattlePlayerVO player = new BattlePlayerVO();
			player.setId(humanId);
			player.setName(name);
			robots.add(player);
		}
		port.returnsAsync(pid, robots);
	}
}
