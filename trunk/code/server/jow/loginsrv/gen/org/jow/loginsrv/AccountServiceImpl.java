package org.jow.loginsrv;
                    
import org.jow.core.Service;
import org.jow.core.gen.proxy.RPCImplBase;
import org.jow.loginsrv.RPCProxy.AccountServiceProxy.EnumCall;
import org.jow.core.support.function.*;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings("unchecked")
@JowGenFile
public final class AccountServiceImpl extends RPCImplBase {
	
	/**
	 * 获取函数指针
	 */
	@Override	
	public Object getMethodFunction(Service service, int methodKey) {
		AccountService serv = (AccountService)service;
		switch (methodKey) {
			case EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_CONNCHECK_LONG: {
				return (JowFunction1<Long>)serv::connCheck;
			}
			case EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_CONNCLOSED_LONG: {
				return (JowFunction1<Long>)serv::connClosed;
			}
			case EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_GAMEPING_INT_INT: {
				return (JowFunction2<Integer, Integer>)serv::gamePing;
			}
			case EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_MSGHANDLER_LONG_BYTES: {
				return (JowFunction2<Long, byte[]>)serv::msgHandler;
			}
			default: break;
		}
		
		return null;
	}

}
