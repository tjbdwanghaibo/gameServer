package org.jow.centralsrv.human;
                    
import org.jow.core.Service;
import org.jow.core.gen.proxy.RPCImpIBase;
import org.jow.centralsrv.RPCProxy.HumanGlobalServiceProxy.EnumCall;
import org.jow.core.support.function.*;
import java.util.List;
import org.jow.common.game.HumanCentralInfo;
import org.jow.core.CallPoint;
import org.jow.core.Chunk;
import org.jow.core.Parms;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings({ "unchecked", "rawtypes" })
@JowGenFile
public final class HumanGlobalServiceImpl extends RPCImpIBase {
	
	/**
	 * 获取函数指针
	 */
	@Override	
	public Object getMethodFunction(Service service, int methodKey) {
		HumanGlobalService serv = (HumanGlobalService)service;
		switch (methodKey) {
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_CHANGENAME_LONG_STRING: {
				return (JowFunction2<Long, String>)serv::changeName;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_FINDHUMAN_STRING: {
				return (JowFunction1<String>)serv::findHuman;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_GETHUMANINFO_LIST: {
				return (JowFunction1<List>)serv::getHumanInfo;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_GETNEARBYPLAYER_LONG: {
				return (JowFunction1<Long>)serv::getNearbyPlayer;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_GETONLINE_LONG: {
				return (JowFunction1<Long>)serv::getOnline;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_GETONLINES_LIST: {
				return (JowFunction1<List>)serv::getOnlines;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_HUMANCREATE_HUMANCENTRALINFO: {
				return (JowFunction1<HumanCentralInfo>)serv::humanCreate;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT: {
				return (JowFunction3<Long, CallPoint, CallPoint>)serv::humanLogin;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_HUMANLOGOUT_LONG: {
				return (JowFunction1<Long>)serv::humanLogout;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_HUMANUPDATE_PARMS: {
				return (JowFunction1<Parms>)serv::humanUpdate;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_SENDMSG_LIST_INT_CHUNK: {
				return (JowFunction3<List, Integer, Chunk>)serv::sendMsg;
			}
			case EnumCall.ORG_JOW_CENTRALSRV_HUMAN_HUMANGLOBALSERVICE_SENDMSG_LONG_INT_CHUNK: {
				return (JowFunction3<Long, Integer, Chunk>)serv::sendMsg;
			}
			default: break;
		}
		
		return null;
	}

}
