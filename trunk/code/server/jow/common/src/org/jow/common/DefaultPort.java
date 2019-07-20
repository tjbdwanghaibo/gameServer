package org.jow.common;

import org.jow.common.idAllot.IdAllotPool;
import org.jow.core.Port;
import org.jow.core.support.SysException;
import org.jow.core.support.log.LogCore;

public class DefaultPort extends Port {
	/** Id分配池 */
	protected IdAllotPool idPoll = new IdAllotPool(this);
	
	public DefaultPort(String portId) {
		super(portId);
	}

	@Override
	public long applySeqId() {
		//未初始化Id池
		if(idPoll == null) {
			LogCore.error.error("本Port未初始化ID池：port={}", this);
			throw new SysException("本Port未初始化ID池：port={}", this);
		}
		return idPoll.applyId();
	}
}
