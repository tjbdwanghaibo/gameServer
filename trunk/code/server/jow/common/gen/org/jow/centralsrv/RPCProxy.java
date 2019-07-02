package org.jow.centralsrv;

import org.jow.core.Call;                    
import org.jow.core.CallPoint;
import org.jow.core.Port;
import org.jow.core.support.Param;
import org.jow.core.support.Utils;
import org.jow.core.support.function.*;
import org.jow.core.gen.proxy.RPCProxyBase;
import java.util.List;
import org.jow.common.game.HumanCentralInfo;
import org.jow.core.Chunk;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings("rawtypes")
@JowGenFile
public class RPCProxy {
	public static final class HumanGlobalServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_CHANGERNAME_LONG_STRING = 1;
			public static final int ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_GETONLINE_LIST = 2;
			public static final int ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_GETONLINE_LONG = 3;
			public static final int ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANCREATE_HUMANCENTRALINFO = 4;
			public static final int ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT = 5;
			public static final int ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANLOGOUT_LONG = 6;
			public static final int ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_SENDMSG_LIST_INT_CHUNK = 7;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private HumanGlobalServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static HumanGlobalServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.servId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static HumanGlobalServiceProxy newInstance(String node, String port, Object id) {
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
		private static HumanGlobalServiceProxy createInstance(String node, String port, Object id) {
			HumanGlobalServiceProxy inst = new HumanGlobalServiceProxy();
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
		public void listenResult(JowFunction2<Param, Param> method, Object...context) {
			listenResult(method, new Param(context));
		}
		
		/**
		 * 监听返回值
		 * @param obj
		 * @param methodName
		 * @param context
		 */
		public void listenResult(JowFunction2<Param, Param> method, Param context) {
			context.put("_callerInfo", remote.callerInfo);
			localPort.listenResult(method, context);
		}
		
		
		public void listenResult(JowFunction3<Boolean, Param, Param> method, Object...context) {
			listenResult(method, new Param(context));
		}
		
		public void listenResult(JowFunction3<Boolean, Param, Param> method, Param context) {
			context.put("_callerInfo", remote.callerInfo);
			localPort.listenResult(method, context);
		}
		
		
		/**
		 * 等待返回值
		 */
		public Param waitForResult() {
			return localPort.waitForResult();
		}
		
		public void changerName(long arg0, String arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_CHANGERNAME_LONG_STRING, new Object[]{ arg0, arg1 });
		}
		
		public void getOnline(List arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_GETONLINE_LIST, new Object[]{ arg0 });
		}
		
		public void getOnline(long arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_GETONLINE_LONG, new Object[]{ arg0 });
		}
		
		public void humanCreate(HumanCentralInfo arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANCREATE_HUMANCENTRALINFO, new Object[]{ arg0 });
		}
		
		public void humanLogin(long arg0, CallPoint arg1, CallPoint arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT, new Object[]{ arg0, arg1, arg2 });
		}
		
		public void humanLogout(long arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANLOGOUT_LONG, new Object[]{ arg0 });
		}
		
		public void sendMsg(List arg0, int arg1, Chunk arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_SENDMSG_LIST_INT_CHUNK, new Object[]{ arg0, arg1, arg2 });
		}
		
		public Call makeCall_changerName(long arg0, String arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_CHANGERNAME_LONG_STRING, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_getOnline(List arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_GETONLINE_LIST, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_getOnline(long arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_GETONLINE_LONG, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_humanCreate(HumanCentralInfo arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANCREATE_HUMANCENTRALINFO, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_humanLogin(long arg0, CallPoint arg1, CallPoint arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANLOGIN_LONG_CALLPOINT_CALLPOINT, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_humanLogout(long arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_HUMANLOGOUT_LONG, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_sendMsg(List arg0, int arg1, Chunk arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_CENTRALSRV_HUMANGLOBALSERVICE_SENDMSG_LIST_INT_CHUNK, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
}