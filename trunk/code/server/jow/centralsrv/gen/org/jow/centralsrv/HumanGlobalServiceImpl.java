package org.jow.centralsrv;
                    
import org.jow.core.Service;
import org.jow.core.gen.proxy.RPCImplBase;
import org.jow.centralsrv.RPCProxy.HumanGlobalServiceProxy.EnumCall;
import org.jow.core.support.function.*;
import java.util.List;
import org.jow.common.game.HumanCentralInfo;
import org.jow.core.CallPoint;
import org.jow.core.Chunk;
import org.jow.core.support.Param;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings({ "unchecked", "rawtypes" })
@JowGenFile
public final class HumanGlobalServiceImpl extends RPCImplBase {
	
	/**
	 * 获取函数指针
	 */
	@Override	
	public Object getMethodFunction(Service service, int methodKey) {
		HumanGlobalService serv = (HumanGlobalService)service;
		switch (methodKey) {
			case EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_CHANGERNAME_LONG_STRING: {
				return (JowFunction2<Long, String>)serv::changerName;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_GETONLINE_LIST: {
				return (JowFunction1<List>)serv::getOnline;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_GETONLINE_LONG: {
				return (JowFunction1<Long>)serv::getOnline;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANCREATE_HUMANCENTRALINFO: {
				return (JowFunction1<HumanCentralInfo>)serv::humanCreate;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT: {
				return (JowFunction3<Long, CallPoint, CallPoint>)serv::humanLogin;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANLOGOUT_LONG: {
				return (JowFunction1<Long>)serv::humanLogout;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_SENDMSG_LIST_INT_CHUNK: {
				return (JowFunction3<List, Integer, Chunk>)serv::sendMsg;
			}
			default: break;
		}
		
		return null;
	}

}
