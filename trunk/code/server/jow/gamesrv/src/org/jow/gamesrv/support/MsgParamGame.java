package org.jow.gamesrv.support;

import org.jow.core.support.observer.MsgParamBase;
import org.jow.gamesrv.HumanObject;

import com.google.protobuf.GeneratedMessage;

/**
 * @author gaopan
 *
 * 游戏阶段中的客户端消息都通过这个类封装
 */
public class MsgParamGame extends MsgParamBase {
	/** 玩家对象 */
	private HumanObject humanObj;

	public MsgParamGame(GeneratedMessage msg) {
		super(msg);
	}

	public HumanObject getHumanObj() {
		return humanObj;
	}

	public void setHumanObj(HumanObject humanObj) {
		this.humanObj = humanObj;
	}
	
}
