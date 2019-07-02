package org.jow.centralsrv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jow.common.entity.central.HumanSimpleDB;
import org.jow.common.game.HumanCentralInfo;
import org.jow.common.game.HumanOnlineInfo;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.CallPoint;
import org.jow.core.Chunk;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.Service;
import org.jow.core.config.CentralConfig;
import org.jow.core.gen.proxy.DistrClass;
import org.jow.core.gen.proxy.DistrMethod;
import org.jow.core.support.Param;

/**
 * 玩家全局服务
 * 
 * @author Yangxianmeng
 *
 */
@DistrClass(importClass={CallPoint.class, List.class, HumanCentralInfo.class, Chunk.class, Param.class})
public class HumanGlobalService extends Service {

	/** 全部在线玩家 */
	private Map<Long, HumanOnlineInfo> onlineHumans = new HashMap<>();
	/** 玩家的简要信息 */
	private Map<Long, HumanSimpleDB> humans = new HashMap<>();
	/** 名字-HumanId */
	private Map<String, Long> name_Id = new HashMap<>();
	
	
	public HumanGlobalService(Port port) {
		super(port);
	}

	@Override
	public Object getId() {
		return CentralConfig.SERV_HUMAN_GLOBAL;
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

	protected void initHumanInfo() {
		// TODO Auto-generated method stub
	}

	@DistrMethod
	public void humanCreate(HumanCentralInfo humanCentralInfo) {
		if(humans.containsKey(humanCentralInfo.getId())) {
			return;
		}else {
			HumanSimpleDB human = new HumanSimpleDB();
			human.setId(humanCentralInfo.getId());
			human.setHead(humanCentralInfo.getHead());
			human.setName(humanCentralInfo.getName());
			human.setSex(humanCentralInfo.getSex());
			humans.put(human.getId(), human);
		}
	}
	
	@DistrMethod
	public void humanLogin(long humanId, CallPoint connPoint, CallPoint gamePoint) {
		HumanOnlineInfo humanOnlineInfo = new HumanOnlineInfo();
		humanOnlineInfo.setConnPoint(connPoint);
		humanOnlineInfo.setGamePoint(gamePoint);
		humanOnlineInfo.setId(humanId);
		onlineHumans.put(humanId, humanOnlineInfo);
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
	public void getOnline(List<Long> humanIds) {
		List<HumanOnlineInfo> onlines = new ArrayList<>(humanIds.size());
		for (long humanId : humanIds) {
			onlines.add(onlineHumans.get(humanId));
		}
		port.returns(onlines);
	}
	
	/**
	 * 给指定玩家发消息(立即发送)
	 * @param humanId
	 */
	public void sendMsg(long humanId, int msgId, Chunk msgBuf) {
		HumanOnlineInfo humanOnlineInfo = onlineHumans.get(humanId);
		if(humanOnlineInfo == null) {
			return;
		}
		
		ConnectionProxy proxy = ConnectionProxy.newInstance(humanOnlineInfo.getConnPoint());
		proxy.sendMsg(msgId, msgBuf);
	}
	
	/**
	 * 给指定的玩家列表发消息（立即发送）
	 * @param humanIds
	 * @param msgId
	 * @param chunk
	 */
	@DistrMethod
	public void sendMsg(List<Long> humanIds, int msgId, Chunk chunk) {
		for (long humanId : humanIds) {
			sendMsg(humanId, msgId, chunk);
		}
	}
	
	/**
	 * 玩家修改名字
	 * @param humanId
	 * @param newName
	 */
	@DistrMethod
	public void changerName(long humanId, String newName) {
		if(name_Id.containsKey(newName)) {
			port.returns(false);
			return;
		}
		HumanSimpleDB human = humans.get(humanId);
		name_Id.remove(human.getName());
		name_Id.put(newName, humanId);
		human.setName(newName);
		port.returns(true);
	}
}
