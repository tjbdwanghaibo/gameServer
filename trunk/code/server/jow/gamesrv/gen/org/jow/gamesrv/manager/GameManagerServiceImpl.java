package org.jow.gamesrv.manager;
                    
import org.jow.core.Service;
import org.jow.core.gen.proxy.RPCImpIBase;
import org.jow.gamesrv.RPCProxy.GameManagerServiceProxy.EnumCall;
import org.jow.core.support.function.*;
import java.util.List;
import org.jow.common.game.HumanCentralInfo;
import org.jow.common.game.HumanInfo;
import org.jow.core.CallPoint;
import org.jow.core.Parms;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings("unchecked")
@JowGenFile
public final class GameManagerServiceImpl extends RPCImpIBase {
	
	/**
	 * 获取函数指针
	 */
	@Override	
	public Object getMethodFunction(Service service, int methodKey) {
		GameManagerService serv = (GameManagerService)service;
		switch (methodKey) {
			case EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_GETINITROBOTDATA: {
				return (JowFunction0)serv::getInitRobotData;
			}
			case EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANCREATE_HUMANINFO: {
				return (JowFunction1<HumanInfo>)serv::humanCreate;
			}
			case EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT: {
				return (JowFunction3<Long, CallPoint, CallPoint>)serv::humanLogin;
			}
			case EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANLOGOUT_LONG: {
				return (JowFunction1<Long>)serv::humanLogout;
			}
			case EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_ISLOGIN_LONG: {
				return (JowFunction1<Long>)serv::isLogin;
			}
			default: break;
		}
		
		return null;
	}

}
