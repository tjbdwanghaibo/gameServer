package org.jow.gamesrv;

import org.jow.core.Call;                    
import org.jow.core.CallPoint;
import org.jow.core.Port;
import org.jow.core.Parms;
import org.jow.core.support.Utils;
import org.jow.core.support.function.*;
import org.jow.core.gen.proxy.RPCProxyBase;
import java.util.List;
import org.jow.common.game.HumanCentralInfo;
import org.jow.common.game.HumanInfo;
import org.jow.core.gen.JowGenFile;


@JowGenFile
public class RPCProxy {
	public static final class GameServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_GAMESRV_GAMESERVICE_CONNCHECK_LONG_LONG = 1;
			public static final int ORG_JOW_GAMESRV_GAMESERVICE_CONNCLOSED_LONG_LONG = 2;
			public static final int ORG_JOW_GAMESRV_GAMESERVICE_CONNDROPPED_LONG_LONG = 3;
			public static final int ORG_JOW_GAMESRV_GAMESERVICE_LOGIN_CALLPOINT_LONG_STRING = 4;
			public static final int ORG_JOW_GAMESRV_GAMESERVICE_MSGHANDLER_LONG_LONG_BYTES = 5;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private GameServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static GameServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static GameServiceProxy newInstance(String node, String port, Object id) {
			return createInstance(node, port, id);
		}
		
		/**
		 * 创建实例
		 * @param localPort
		 * @param node
		 * @param port
		 * @param id
		 * @return
		 */
		private static GameServiceProxy createInstance(String node, String port, Object id) {
			GameServiceProxy inst = new GameServiceProxy();
			inst.localPort = Port.getCurrent();
			inst.remote = new CallPoint(node, port, id);
			
			return inst;
		}
		
		/**
		 * 监听返回值
		 * @param obj
		 * @param methodName
		 * @param context
		 */
		public void listenResult(JowFunction2<Parms, Parms> method, Object...context) {
			listenResult(method, new Parms(context));
		}
		
		/**
		 * 监听返回值
		 * @param obj
		 * @param methodName
		 * @param context
		 */
		public void listenResult(JowFunction2<Parms, Parms> method, Parms context) {
			context.put("_callerInfo", remote.callerInfo);
			localPort.listenResult(method, context);
		}
		
		
		public void listenResult(JowFunction3<Boolean, Parms, Parms> method, Object...context) {
			listenResult(method, new Parms(context));
		}
		
		public void listenResult(JowFunction3<Boolean, Parms, Parms> method, Parms context) {
			context.put("_callerInfo", remote.callerInfo);
			localPort.listenResult(method, context);
		}
		
		
		/**
		 * 等待返回值
		 */
		public Parms waitForResult() {
			return localPort.waitForResult();
		}
		
		public void connCheck(long arg0, long arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNCHECK_LONG_LONG, new Object[]{ arg0, arg1 });
		}
		
		public void connClosed(long arg0, long arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNCLOSED_LONG_LONG, new Object[]{ arg0, arg1 });
		}
		
		public void connDropped(long arg0, long arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNDROPPED_LONG_LONG, new Object[]{ arg0, arg1 });
		}
		
		public void login(CallPoint arg0, long arg1, String arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_LOGIN_CALLPOINT_LONG_STRING, new Object[]{ arg0, arg1, arg2 });
		}
		
		public void msgHandler(long arg0, long arg1, byte... arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_MSGHANDLER_LONG_LONG_BYTES, new Object[]{ arg0, arg1, arg2 });
		}
		
		public Call makeCall_connCheck(long arg0, long arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNCHECK_LONG_LONG, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_connClosed(long arg0, long arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNCLOSED_LONG_LONG, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_connDropped(long arg0, long arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_CONNDROPPED_LONG_LONG, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_login(CallPoint arg0, long arg1, String arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_LOGIN_CALLPOINT_LONG_STRING, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_msgHandler(long arg0, long arg1, byte... arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_GAMESERVICE_MSGHANDLER_LONG_LONG_BYTES, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
	public static final class GameManagerServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANCREATE_HUMANINFO = 1;
			public static final int ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT = 2;
			public static final int ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANLOGOUT_LONG = 3;
			public static final int ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_ISLOGIN_LONG = 4;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private GameManagerServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static GameManagerServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static GameManagerServiceProxy newInstance(String node, String port, Object id) {
			return createInstance(node, port, id);
		}
		
		/**
		 * 创建实例
		 * @param localPort
		 * @param node
		 * @param port
		 * @param id
		 * @return
		 */
		private static GameManagerServiceProxy createInstance(String node, String port, Object id) {
			GameManagerServiceProxy inst = new GameManagerServiceProxy();
			inst.localPort = Port.getCurrent();
			inst.remote = new CallPoint(node, port, id);
			
			return inst;
		}
		
		/**
		 * 监听返回值
		 * @param obj
		 * @param methodName
		 * @param context
		 */
		public void listenResult(JowFunction2<Parms, Parms> method, Object...context) {
			listenResult(method, new Parms(context));
		}
		
		/**
		 * 监听返回值
		 * @param obj
		 * @param methodName
		 * @param context
		 */
		public void listenResult(JowFunction2<Parms, Parms> method, Parms context) {
			context.put("_callerInfo", remote.callerInfo);
			localPort.listenResult(method, context);
		}
		
		
		public void listenResult(JowFunction3<Boolean, Parms, Parms> method, Object...context) {
			listenResult(method, new Parms(context));
		}
		
		public void listenResult(JowFunction3<Boolean, Parms, Parms> method, Parms context) {
			context.put("_callerInfo", remote.callerInfo);
			localPort.listenResult(method, context);
		}
		
		
		/**
		 * 等待返回值
		 */
		public Parms waitForResult() {
			return localPort.waitForResult();
		}
		
		public void humanCreate(HumanInfo arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANCREATE_HUMANINFO, new Object[]{ arg0 });
		}
		
		public void humanLogin(long arg0, CallPoint arg1, CallPoint arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT, new Object[]{ arg0, arg1, arg2 });
		}
		
		public void humanLogout(long arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANLOGOUT_LONG, new Object[]{ arg0 });
		}
		
		public void isLogin(long arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_ISLOGIN_LONG, new Object[]{ arg0 });
		}
		
		public Call makeCall_humanCreate(HumanInfo arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANCREATE_HUMANINFO, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_humanLogin(long arg0, CallPoint arg1, CallPoint arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_humanLogout(long arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_HUMANLOGOUT_LONG, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_isLogin(long arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_GAMESRV_MANAGER_GAMEMANAGERSERVICE_ISLOGIN_LONG, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
}