package org.jow.gamesrv;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jow.common.entity.game.HumanDB;
import org.jow.common.msg.MsgCommon.SCHumanInfoChange;
import org.jow.common.msg.MsgDef.DHuman;

/**
 * @author gaopan
 *
 * 监听玩家基本信息改变，并同步给客户端
 */
public class HumanInfoChange {
	/** 玩家对比数据 较旧的部分 */
	private Map<String, Object> humanInfoOld;

	public HumanInfoChange(HumanObject humanObjOld) {
		this.humanInfoOld = getHumanInfo(humanObjOld);
	}
	
	/**
	 * 增加监听信息
	 * @param humanObjOld
	 */
	public static void listen(HumanObject humanObjOld) {
		humanObjOld.humanInfoChangeListen();
	}

	/**
	 * humanInfoChange监听一次的结果
	 * @param humanObjNew
	 */
	public void resultForListen(HumanObject humanObjNew) {
		Map<String, Object> humanInfoNew = getHumanInfo(humanObjNew);
		Map<String, Object> humanInfoChange = getHumanInfoChange(humanInfoNew);
		
		// 如果有改变 就需要向客户端发送改变消息
		if (!humanInfoChange.isEmpty()) {
			SCHumanInfoChange msg = createMsg(humanInfoChange, humanObjNew.getId());
			humanObjNew.sendMsg(msg);
		}
		
		// 在本次将最新的humanInfo保存下来，下一次检测就可以直接用不用再重新获取
		humanInfoOld = humanInfoNew;
	}
	
	/**
	 * 获取人物身上所有需要的信息
	 * @param humanObj
	 * @return
	 */
	private Map<String, Object> getHumanInfo(HumanObject humanObj) {
		HumanDB human = humanObj.getHuman();
		
		Map<String, Object> result = new HashMap<>();
		result.put("level", human.getLevel());
//		result.put("gold", human.getGold());
//		result.put("diamond", human.getDiamond());
		return result;
	}
		
	/**
	 * 获取人物属性变化，返回变化字段的最终值
	 * @param humanInfoNew
	 * @return
	 */
	private Map<String, Object> getHumanInfoChange(Map<String, Object> humanInfoNew) {
		Map<String, Object> result = new HashMap<>();
		for (Entry<String, Object> entry : humanInfoOld.entrySet()) {
			String key = entry.getKey();
			
			Object valueOld = entry.getValue();
			Object valueNew = humanInfoNew.get(key);
			
			// 如果两个字段的字符串值相同，那么不管是String还是int还是double还是long，那值应该都相同，这个值就不用管
			if (valueOld.equals(valueNew)) {
				continue;
			}
			
			result.put(key, valueNew);
		}
		
		return result;
	}
	
	/**
	 * 写入数据
	 * @param dHuman
	 * @param key
	 * @param value
	 */
	private void fieldWrite(DHuman.Builder dHuman, String key, Object value) {
		switch (key) {
			case "level": {
				dHuman.setLevel((int)value);
				break;
			}
			case "gold": {
				dHuman.setGold((long)value);
				break;
			}
			case "diamond": {
				dHuman.setDiamond((long)value);
				break;
			}
		}
	}
	
	/**
	 * 构建Human Info 变化信息，如果没有变化，那就返回null
	 * @param humanInfoChange
	 * @return
	 */
	private SCHumanInfoChange createMsg(Map<String, Object> humanInfoChange, long humanId) {
		SCHumanInfoChange.Builder msgResult = SCHumanInfoChange.newBuilder();
		DHuman.Builder dHuman = DHuman.newBuilder();
		
		for (Entry<String, Object> entry : humanInfoChange.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			fieldWrite(dHuman, key, value);
		}
		dHuman.setId(humanId);
		msgResult.setHuman(dHuman);
		return msgResult.build();
	}

}
