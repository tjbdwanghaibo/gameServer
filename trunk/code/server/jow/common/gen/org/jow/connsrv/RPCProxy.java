package org.jow.connsrv;

import org.jow.core.Call;                    
import org.jow.core.CallPoint;
import org.jow.core.Port;
import org.jow.core.Parms;
import org.jow.core.support.Utils;
import org.jow.core.support.function.*;
import org.jow.core.gen.proxy.RPCProxyBase;
import java.util.List;
import org.jow.core.Chunk;
import org.jow.core.support.ConnectionStatus;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings("rawtypes")
@JowGenFile
public class RPCProxy {
	public static final class ConnServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_CONNSRV_CONNSERVICE_CHECK_LONG = 1;
			public static final int ORG_JOW_CONNSRV_CONNSERVICE_SENDMSG_LIST_INT_CHUNK = 2;
			public static final int ORG_JOW_CONNSRV_CONNSERVICE_SENDMSG_LIST_LIST_LIST = 3;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private ConnServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static ConnServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static ConnServiceProxy newInstance(String node, String port, Object id) {
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
		private static ConnServiceProxy createInstance(String node, String port, Object id) {
			ConnServiceProxy inst = new ConnServiceProxy();
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
		
		public void check(long arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNSERVICE_CHECK_LONG, new Object[]{ arg0 });
		}
		
		public void sendMsg(List arg0, int arg1, Chunk arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(true, remote, EnumCall.ORG_JOW_CONNSRV_CONNSERVICE_SENDMSG_LIST_INT_CHUNK, new Object[]{ arg0, arg1, arg2 });
		}
		
		public void sendMsg(List arg0, List arg1, List arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNSERVICE_SENDMSG_LIST_LIST_LIST, new Object[]{ arg0, arg1, arg2 });
		}
		
		public Call makeCall_check(long arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNSERVICE_CHECK_LONG, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_sendMsg(List arg0, int arg1, Chunk arg2) {
			Call call = localPort.makeCall(true, remote, EnumCall.ORG_JOW_CONNSRV_CONNSERVICE_SENDMSG_LIST_INT_CHUNK, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_sendMsg(List arg0, List arg1, List arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNSERVICE_SENDMSG_LIST_LIST_LIST, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
	public static final class ConnectionProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_CONNSRV_CONNECTION_CLOSEIMMEDIATE = 1;
			public static final int ORG_JOW_CONNSRV_CONNECTION_GETIPADDRESS = 2;
			public static final int ORG_JOW_CONNSRV_CONNECTION_SENDMSG_INT_CHUNK = 3;
			public static final int ORG_JOW_CONNSRV_CONNECTION_SENDMSG_LIST_LIST = 4;
			public static final int ORG_JOW_CONNSRV_CONNECTION_SETSWITCHSTAGE_BOOLEAN = 5;
			public static final int ORG_JOW_CONNSRV_CONNECTION_STATUSBATTLE_CALLPOINT_LONG = 6;
			public static final int ORG_JOW_CONNSRV_CONNECTION_STATUSUPDATE_CONNECTIONSTATUS = 7;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private ConnectionProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static ConnectionProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static ConnectionProxy newInstance(String node, String port, Object id) {
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
		private static ConnectionProxy createInstance(String node, String port, Object id) {
			ConnectionProxy inst = new ConnectionProxy();
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
		
		public void closeImmediate() {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_CLOSEIMMEDIATE, new Object[]{  });
		}
		
		public void getIpAddress() {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_GETIPADDRESS, new Object[]{  });
		}
		
		public void sendMsg(int arg0, Chunk arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(true, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_SENDMSG_INT_CHUNK, new Object[]{ arg0, arg1 });
		}
		
		public void sendMsg(List arg0, List arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(true, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_SENDMSG_LIST_LIST, new Object[]{ arg0, arg1 });
		}
		
		public void setSwitchStage(boolean arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_SETSWITCHSTAGE_BOOLEAN, new Object[]{ arg0 });
		}
		
		public void statusBattle(CallPoint arg0, long arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_STATUSBATTLE_CALLPOINT_LONG, new Object[]{ arg0, arg1 });
		}
		
		public void statusUpdate(ConnectionStatus arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_STATUSUPDATE_CONNECTIONSTATUS, new Object[]{ arg0 });
		}
		
		public Call makeCall_closeImmediate() {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_CLOSEIMMEDIATE, new Object[]{  });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_getIpAddress() {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_GETIPADDRESS, new Object[]{  });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_sendMsg(int arg0, Chunk arg1) {
			Call call = localPort.makeCall(true, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_SENDMSG_INT_CHUNK, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_sendMsg(List arg0, List arg1) {
			Call call = localPort.makeCall(true, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_SENDMSG_LIST_LIST, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_setSwitchStage(boolean arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_SETSWITCHSTAGE_BOOLEAN, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_statusBattle(CallPoint arg0, long arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_STATUSBATTLE_CALLPOINT_LONG, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_statusUpdate(ConnectionStatus arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CONNSRV_CONNECTION_STATUSUPDATE_CONNECTIONSTATUS, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
}