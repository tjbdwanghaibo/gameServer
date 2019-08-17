package org.jow.centralsrv.human;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jow.common.entity.central.HumanSimpleDB;
import org.jow.common.game.HumanCentralInfo;
import org.jow.common.game.HumanOnlineInfo;
import org.jow.common.support.utils.DBUtils;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.CallPoint;
import org.jow.core.Chunk;
import org.jow.core.Parms;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.Service;
import org.jow.core.config.CentralConfig;
import org.jow.core.gen.proxy.DistrClass;
import org.jow.core.gen.proxy.DistrMethod;

/**
 * 玩家全局服务
 * @author Yangxianmeng
 *
 */
@DistrClass(importClass = {CallPoint.class, List.class, HumanCentralInfo.class, Chunk.class, Parms.class})
public class HumanGlobalService extends Service{

	/** 全部在线玩家 */
	private Map<Long, HumanOnlineInfo> onlineHumans = new HashMap<>();
	/** 全部玩家简要信息 */
	private Map<Long, HumanSimpleDB> humans = new HashMap<>();
	/** 名字_humanId */
	private Map<String, Long> name_Id = new HashMap<>();
	
	
	public HumanGlobalService(Port port) {
		super(port);
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
	 * 加载全服的粉丝数量
	 */
	private void initHumanInfo() {
		DBUtils.findbyWithPaging(HumanSimpleDB.tableName, (r) -> {
			HumanSimpleDB db = new HumanSimpleDB(r);
			humans.put(db.getId(), db);
			name_Id.put(db.getName(), db.getId());
		});
	}
	
	@Override
	public Object getId() {
		return CentralConfig.SERV_HUMAN_GLOBAL;
	}

	@DistrMethod
	public void humanCreate(HumanCentralInfo humanInfo) {
		if(humans.containsKey(humanInfo.getId())) {
			return;
		}else {
			HumanSimpleDB human = new HumanSimpleDB();
			human.setId(humanInfo.getId());
			human.setHead(humanInfo.getHead());
			human.setName(humanInfo.getName());
			human.setSex(humanInfo.getSex());
			human.persist();
			humans.put(human.getId(), human);
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
	public void getOnline(long humanId) {
		port.returns(onlineHumans.get(humanId));
	}
	
	@DistrMethod
	public void getOnlines(List<Long> humanIds) {
		List<HumanOnlineInfo> onlines = new ArrayList<>(humanIds.size());
		for (long humanId : humanIds) {
			onlines.add(onlineHumans.get(humanId));
		}
		
		port.returns(onlines);
	}
	
	
	/**
	 * 给指定玩家发送消息（立即发送）
	 * 
	 * @param humanId
	 * @param msgId
	 * @param msgbuf
	 */
	@DistrMethod
	public void sendMsg(long humanId, int msgId, Chunk msgbuf) {
		HumanOnlineInfo info = onlineHumans.get(humanId);
		if (info == null) {
			return;
		}

		ConnectionProxy prx = ConnectionProxy.newInstance(info.getConnPoint());
		prx.sendMsg(msgId, msgbuf);
	}
	
	/**
	 * 给指定角色列表发送消息（立即发送）
	 * 
	 * @param humanIds
	 * @param msgId
	 * @param msgbuf
	 */
	@DistrMethod
	public void sendMsg(List<Long> humanIds, int msgId, Chunk msgbuf) {
		for (long humanId : humanIds) {
			sendMsg(humanId, msgId, msgbuf);
		}
	}
	
	
	/**
	 * 查找好友
	 * @param name
	 */
	@DistrMethod
	public void findHuman(String name) {
		if(name_Id.containsKey(name)) {
			HumanSimpleDB human = humans.get(name_Id.get(name));
			HumanCentralInfo humanInfo = new HumanCentralInfo(human);
			if(onlineHumans.containsKey(humanInfo.getId())) {
				humanInfo.setOnline(true);
			}
			port.returns("humanInfo", humanInfo);
			return;
		}
		
		if(StringUtils.isNotEmpty(name) && NumberUtils.isNumber(name) && name.length() < 18) {
			try {
				long humanId = Long.parseLong(name);
				HumanSimpleDB human = humans.get(humanId);
				if(human != null) {
					HumanCentralInfo humanInfo = new HumanCentralInfo(human);
					if(onlineHumans.containsKey(humanId)){
						humanInfo.setOnline(true);
					}
					port.returns("humanInfo", humanInfo);
					return;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			port.returns("humanInfo", null);
		}
		
	}
	
	
	/**
	 * 获取玩家简要信息
	 * @param humanIds
	 */
	@DistrMethod
	public void getHumanInfo(List<Long> humanIds) {
		List<HumanCentralInfo> humanlist = new ArrayList<>();
		for(long id : humanIds){
			HumanSimpleDB human = humans.get(id);
			if(human != null){
				HumanCentralInfo humanInfo = new HumanCentralInfo(human);
				if(onlineHumans.containsKey(id)){
					humanInfo.setOnline(true);
				}
				humanlist.add(humanInfo);
			}
		}
		port.returns("humanlist", humanlist);
	}
	/**
	 * 玩家修改名字
	 * @param humanId
	 * @param newName
	 */
	@DistrMethod
	public void changeName(long humanId, String newName) {
		if(name_Id.containsKey(newName)){
			port.returns(false);
			return;
		}
		HumanSimpleDB human = humans.get(humanId);
		name_Id.remove(human.getName());
		name_Id.put(newName, humanId);
		human.setName(newName);
		port.returns(true);
	}
	
	/**
	 * 玩家信息更新
	 * @param param
	 */
	@DistrMethod
	public void humanUpdate(Parms param) {
		Long id = param.get(HumanSimpleDB.K.id);
		if (id == null) {
			return;
		}
		
		HumanSimpleDB humanInfo = humans.get(id);
		if (humanInfo == null) {
			return;
		}
		for(String key : param.keySet()){
			humanInfo.setField(key, param.get(key));
		}
	}
	/**
	 * 获取附近玩家信息（暂时给几个在线玩家）
	 * @param humanIds
	 */
	@DistrMethod
	public void getNearbyPlayer(long humanId) {
		List<HumanCentralInfo> humanlist = new ArrayList<>();
		int num = 0;
		for(HumanOnlineInfo info : onlineHumans.values()){
			if(info.getId() == humanId){
				continue;
			}
			HumanSimpleDB human = humans.get(info.getId());
			if(human != null){
				HumanCentralInfo humanInfo = new HumanCentralInfo(human);
				humanInfo.setOnline(true);
				humanlist.add(humanInfo);
				num ++;
			}
			if(num >= 10){
				break;
			}
		}
		port.returns("humanlist", humanlist);
	}
	
}
