package org.jow.centralsrv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * 给指定玩家发消息
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
	
}
