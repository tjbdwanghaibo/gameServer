package org.jow.gamesrv;
                    
import org.jow.core.Service;
import org.jow.core.gen.proxy.RPCImpIBase;
import org.jow.gamesrv.RPCProxy.GameServiceProxy.EnumCall;
import org.jow.core.support.function.*;
import org.jow.core.CallPoint;
import org.jow.core.gen.JowGenFile;
import org.jow.common.battle.*;
@SuppressWarnings("unchecked")
@JowGenFile
public final class GameServiceImpl extends RPCImpIBase {
	
	/**
	 * 获取函数指针
	 */
	@Override	
	public Object getMethodFunction(Service service, int methodKey) {
		GameService serv = (GameService)service;
		switch (methodKey) {
			case EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNCHECK_LONG_LONG: {
				return (JowFunction2<Long, Long>)serv::connCheck;
			}
			case EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNCLOSED_LONG_LONG: {
				return (JowFunction2<Long, Long>)serv::connClosed;
			}
			case EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNDROPPED_LONG_LONG: {
				return (JowFunction2<Long, Long>)serv::connDropped;
			}
			case EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_LOGIN_CALLPOINT_LONG_STRING: {
				return (JowFunction3<CallPoint, Long, String>)serv::login;
			}
			case EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_MODONBATTLEOVER_LONG_BATTLEENDINFO_STRING: {
				return (JowFunction3<Long, BattleEndInfo, String>)serv::modOnBattleOver;
			}
			case EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_MODONBATTLESTART_LONG_CALLPOINT_LONG: {
				return (JowFunction3<Long, CallPoint, Long>)serv::modOnBattleStart;
			}
			case EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_MODTEAMONINVITE_LONG_INT_LONG_STRING_STRING: {
				return (JowFunction5<Long, Integer, Long, String, String>)serv::modTeamOnInvite;
			}
			case EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_MSGHANDLER_LONG_LONG_BYTES: {
				return (JowFunction3<Long, Long, byte[]>)serv::msgHandler;
			}
			default: break;
		}
		
		return null;
	}

}
