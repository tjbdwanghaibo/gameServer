package org.jow.gamesrv;

import org.jow.common.idAllot.IdAllotPool;
import org.jow.core.Port;
import org.jow.core.support.SysException;
import org.jow.core.support.log.LogCore;

public class GamePort extends Port{
	
	protected IdAllotPool idPool = new IdAllotPool(this);
	

	public GamePort(String portId) {
		super(portId);
	}

	@Override
	public long applySeqId() {
		//未初始化Id池
		if(idPool == null) {
			LogCore.error.error("本Port未初始化ID池：port={}", this);
			throw new SysException("本Port未初始化ID池：port={}", this);
		}
		return idPool.applyId();
	}
	
}
