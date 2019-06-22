package org.jow.common;

import org.jow.common.idAllot.IdAllotPool;
import org.jow.core.Port;
import org.jow.core.support.SysException;
import org.jow.core.support.log.LogCore;

public class DefaultPort extends Port {

	public DefaultPort(String portId) {
		super(portId);
	}
	
	protected IdAllotPool idPool = new IdAllotPool(this);
	
	@Override
	public long applySeqId() {
		//没有初始化Id池
		if(idPool == null) {
			LogCore.error.error("本Port未初始化ID池：port={}", this);
			throw new SysException("本Port未初始化ID池：port={}", this);
		}
		return idPool.applyId();
	}
}
