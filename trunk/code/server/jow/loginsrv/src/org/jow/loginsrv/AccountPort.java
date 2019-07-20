package org.jow.loginsrv;

import org.jow.common.idAllot.IdAllotPool;
import org.jow.core.Port;
import org.jow.core.support.SysException;
import org.jow.core.support.log.LogCore;

public class AccountPort extends Port{

	/** ID分配池 */
	protected IdAllotPool idPool = new IdAllotPool(this);
	
	public AccountPort(String portId) {
		super(portId);
	}

	@Override
	public long applySeqId() {
		if(idPool == null) {
			LogCore.error.error("本Port未初始化ID池：port={}", this);
			throw new SysException("本Port未初始化ID池：port={}", this);
		}
		return idPool.applyId();
	}
	
}

