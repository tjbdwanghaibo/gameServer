package org.jow.loginsrv;

import org.jow.core.Call;                    
import org.jow.core.CallPoint;
import org.jow.core.Port;
import org.jow.core.Parms;
import org.jow.core.support.Utils;
import org.jow.core.support.function.*;
import org.jow.core.gen.proxy.RPCProxyBase;
import org.jow.core.gen.JowGenFile;


@JowGenFile
public class RPCProxy {
	public static final class AccountServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_LOGINSRV_ACCOUNTSERVICE_CONNCHECK_LONG = 1;
			public static final int ORG_JOW_LOGINSRV_ACCOUNTSERVICE_CONNCLOSED_LONG = 2;
			public static final int ORG_JOW_LOGINSRV_ACCOUNTSERVICE_GAMEPING_INT_INT = 3;
			public static final int ORG_JOW_LOGINSRV_ACCOUNTSERVICE_MSGHANDLER_LONG_BYTES = 4;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private AccountServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static AccountServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static AccountServiceProxy newInstance(String node, String port, Object id) {
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
		private static AccountServiceProxy createInstance(String node, String port, Object id) {
			AccountServiceProxy inst = new AccountServiceProxy();
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
		
		public void connCheck(long arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_CONNCHECK_LONG, new Object[]{ arg0 });
		}
		
		public void connClosed(long arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_CONNCLOSED_LONG, new Object[]{ arg0 });
		}
		
		public void gamePing(int arg0, int arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_GAMEPING_INT_INT, new Object[]{ arg0, arg1 });
		}
		
		public void msgHandler(long arg0, byte... arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_MSGHANDLER_LONG_BYTES, new Object[]{ arg0, arg1 });
		}
		
		public Call makeCall_connCheck(long arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_CONNCHECK_LONG, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_connClosed(long arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_CONNCLOSED_LONG, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_gamePing(int arg0, int arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_GAMEPING_INT_INT, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_msgHandler(long arg0, byte... arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_LOGINSRV_ACCOUNTSERVICE_MSGHANDLER_LONG_BYTES, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
}