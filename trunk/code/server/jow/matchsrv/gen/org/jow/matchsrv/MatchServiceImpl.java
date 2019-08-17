package org.jow.matchsrv;
                    
import org.jow.core.Service;
import org.jow.core.gen.proxy.RPCImpIBase;
import org.jow.matchsrv.RPCProxy.MatchServiceProxy.EnumCall;
import org.jow.core.support.function.*;
import java.util.List;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings({ "unchecked", "rawtypes" })
@JowGenFile
public final class MatchServiceImpl extends RPCImpIBase {
	
	/**
	 * 获取函数指针
	 */
	@Override	
	public Object getMethodFunction(Service service, int methodKey) {
		MatchService serv = (MatchService)service;
		switch (methodKey) {
			case EnumCall.ORG_JOW_MATCHSRV_MATCHSERVICE_STARTMATCH_LIST_INT: {
				return (JowFunction2<List, Integer>)serv::startMatch;
			}
			default: break;
		}
		
		return null;
	}

}
