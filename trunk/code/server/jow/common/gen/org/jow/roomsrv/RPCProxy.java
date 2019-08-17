package org.jow.roomsrv;

import org.jow.core.Call;                    
import org.jow.core.CallPoint;
import org.jow.core.Parms;
import org.jow.core.Port;
import org.jow.core.support.Utils;
import org.jow.core.support.function.*;
import org.jow.core.gen.proxy.RPCProxyBase;
import java.util.List;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings("rawtypes")
@JowGenFile
public class RPCProxy {
	public static final class RoomServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_ROOMSRV_ROOMSERVICE_BATTLELEVEL_LONG_LONG = 1;
			public static final int ORG_JOW_ROOMSRV_ROOMSERVICE_BATTLEOVER_LONG = 2;
			public static final int ORG_JOW_ROOMSRV_ROOMSERVICE_CONNCLOSED_LONG_LONG = 3;
			public static final int ORG_JOW_ROOMSRV_ROOMSERVICE_CONNDROPPED_LONG_LONG = 4;
			public static final int ORG_JOW_ROOMSRV_ROOMSERVICE_ENTERROOM_LONG_INT_INT_LIST = 5;
			public static final int ORG_JOW_ROOMSRV_ROOMSERVICE_MSGHANDLER_LONG_LONG_BYTES = 6;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private RoomServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static RoomServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static RoomServiceProxy newInstance(String node, String port, Object id) {
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
		private static RoomServiceProxy createInstance(String node, String port, Object id) {
			RoomServiceProxy inst = new RoomServiceProxy();
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
		
		public void battleLevel(long humanId, long roomId) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_BATTLELEVEL_LONG_LONG, new Object[]{ humanId, roomId });
		}
		
		public void battleOver(long roomId) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_BATTLEOVER_LONG, new Object[]{ roomId });
		}
		
		public void connClosed(long roomId, long humanId) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_CONNCLOSED_LONG_LONG, new Object[]{ roomId, humanId });
		}
		
		public void connDropped(long roomId, long humanId) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_CONNDROPPED_LONG_LONG, new Object[]{ roomId, humanId });
		}
		
		public void enterRoom(long roomId, int mode, int camp, List players) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_ENTERROOM_LONG_INT_INT_LIST, new Object[]{ roomId, mode, camp, players });
		}
		
		public void msgHandler(long roomId, long humanId, byte... msgbuf) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_MSGHANDLER_LONG_LONG_BYTES, new Object[]{ roomId, humanId, msgbuf });
		}
		
		public Call makeCall_battleLevel(long humanId, long roomId) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_BATTLELEVEL_LONG_LONG, new Object[]{ humanId, roomId });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_battleOver(long roomId) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_BATTLEOVER_LONG, new Object[]{ roomId });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_connClosed(long roomId, long humanId) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_CONNCLOSED_LONG_LONG, new Object[]{ roomId, humanId });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_connDropped(long roomId, long humanId) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_CONNDROPPED_LONG_LONG, new Object[]{ roomId, humanId });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_enterRoom(long roomId, int mode, int camp, List players) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_ENTERROOM_LONG_INT_INT_LIST, new Object[]{ roomId, mode, camp, players });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_msgHandler(long roomId, long humanId, byte... msgbuf) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_ROOMSRV_ROOMSERVICE_MSGHANDLER_LONG_LONG_BYTES, new Object[]{ roomId, humanId, msgbuf });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
}