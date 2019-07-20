package org.jow.dbsrv;

import org.jow.core.Call;                    
import org.jow.core.CallPoint;
import org.jow.core.Port;
import org.jow.core.Parms;
import org.jow.core.support.Utils;
import org.jow.core.support.function.*;
import org.jow.core.gen.proxy.RPCProxyBase;
import java.util.List;
import org.jow.core.Chunk;
import org.jow.core.Record;
import org.jow.core.gen.JowGenFile;

@SuppressWarnings("rawtypes")
@JowGenFile
public class RPCProxy {
	public static final class DBLineServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_DBSRV_DBLINESERVICE_COUNT_STRING_LONG_STRING_OBJECTS = 1;
			public static final int ORG_JOW_DBSRV_DBLINESERVICE_EXECUTE_STRING_LONG_BOOLEAN_STRING_OBJECTS = 2;
			public static final int ORG_JOW_DBSRV_DBLINESERVICE_QUERY_STRING_LONG_STRING_OBJECTS = 3;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private DBLineServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static DBLineServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static DBLineServiceProxy newInstance(String node, String port, Object id) {
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
		private static DBLineServiceProxy createInstance(String node, String port, Object id) {
			DBLineServiceProxy inst = new DBLineServiceProxy();
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
		
		public void count(String arg0, long arg1, String arg2, Object... arg3) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_COUNT_STRING_LONG_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
		}
		
		public void execute(String arg0, long arg1, boolean arg2, String arg3, Object... arg4) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_EXECUTE_STRING_LONG_BOOLEAN_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3, arg4 });
		}
		
		public void query(String arg0, long arg1, String arg2, Object... arg3) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_QUERY_STRING_LONG_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
		}
		
		public Call makeCall_count(String arg0, long arg1, String arg2, Object... arg3) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_COUNT_STRING_LONG_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_execute(String arg0, long arg1, boolean arg2, String arg3, Object... arg4) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_EXECUTE_STRING_LONG_BOOLEAN_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3, arg4 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_query(String arg0, long arg1, String arg2, Object... arg3) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_QUERY_STRING_LONG_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
	public static final class DBPartServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_COUNTALL_BOOLEAN_STRING = 1;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_COUNTBY_BOOLEAN_STRING_OBJECTS = 2;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_COUNTBYQUERY_BOOLEAN_STRING_STRING_OBJECTS = 3;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_DELETE_STRING_LONG = 4;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_DELETEALL_STRING = 5;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_FIND_STRING_LIST = 6;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_FINDBY_BOOLEAN_INT_INT_STRING_OBJECTS = 7;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_FINDBY_BOOLEAN_STRING_OBJECTS = 8;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_FINDBYQUERY_BOOLEAN_INT_INT_STRING_STRING_OBJECTS = 9;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_FINDBYQUERY_BOOLEAN_STRING_STRING_OBJECTS = 10;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_FINDFIELDTABLE = 11;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_FLUSH_STRING = 12;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_FLUSHALL = 13;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_GET_STRING_LONG = 14;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_GETBY_BOOLEAN_STRING_OBJECTS = 15;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_GETBYQUERY_BOOLEAN_STRING_STRING_OBJECTS = 16;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_INSERT_RECORD = 17;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_SQL_BOOLEAN_BOOLEAN_STRING_STRING_OBJECTS = 18;
			public static final int ORG_JOW_DBSRV_DBPARTSERVICE_UPDATE_STRING_LONG_CHUNK_BOOLEAN = 19;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private DBPartServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static DBPartServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static DBPartServiceProxy newInstance(String node, String port, Object id) {
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
		private static DBPartServiceProxy createInstance(String node, String port, Object id) {
			DBPartServiceProxy inst = new DBPartServiceProxy();
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
		
		public void countAll(boolean arg0, String arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTALL_BOOLEAN_STRING, new Object[]{ arg0, arg1 });
		}
		
		public void countBy(boolean arg0, String arg1, Object... arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTBY_BOOLEAN_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2 });
		}
		
		public void countByQuery(boolean arg0, String arg1, String arg2, Object... arg3) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
		}
		
		public void delete(String arg0, long arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_DELETE_STRING_LONG, new Object[]{ arg0, arg1 });
		}
		
		public void deleteAll(String arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_DELETEALL_STRING, new Object[]{ arg0 });
		}
		
		public void find(String arg0, List arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FIND_STRING_LIST, new Object[]{ arg0, arg1 });
		}
		
		public void findBy(boolean arg0, int arg1, int arg2, String arg3, Object... arg4) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBY_BOOLEAN_INT_INT_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3, arg4 });
		}
		
		public void findBy(boolean arg0, String arg1, Object... arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBY_BOOLEAN_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2 });
		}
		
		public void findByQuery(boolean arg0, int arg1, int arg2, String arg3, String arg4, Object... arg5) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBYQUERY_BOOLEAN_INT_INT_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3, arg4, arg5 });
		}
		
		public void findByQuery(boolean arg0, String arg1, String arg2, Object... arg3) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
		}
		
		public void findFieldTable() {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDFIELDTABLE, new Object[]{  });
		}
		
		public void flush(String arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FLUSH_STRING, new Object[]{ arg0 });
		}
		
		public void flushAll() {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FLUSHALL, new Object[]{  });
		}
		
		public void get(String arg0, long arg1) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GET_STRING_LONG, new Object[]{ arg0, arg1 });
		}
		
		public void getBy(boolean arg0, String arg1, Object... arg2) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GETBY_BOOLEAN_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2 });
		}
		
		public void getByQuery(boolean arg0, String arg1, String arg2, Object... arg3) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GETBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
		}
		
		public void insert(Record arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_INSERT_RECORD, new Object[]{ arg0 });
		}
		
		public void sql(boolean arg0, boolean arg1, String arg2, String arg3, Object... arg4) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_SQL_BOOLEAN_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3, arg4 });
		}
		
		public void update(String arg0, long arg1, Chunk arg2, boolean arg3) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_UPDATE_STRING_LONG_CHUNK_BOOLEAN, new Object[]{ arg0, arg1, arg2, arg3 });
		}
		
		public Call makeCall_countAll(boolean arg0, String arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTALL_BOOLEAN_STRING, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_countBy(boolean arg0, String arg1, Object... arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTBY_BOOLEAN_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_countByQuery(boolean arg0, String arg1, String arg2, Object... arg3) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_delete(String arg0, long arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_DELETE_STRING_LONG, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_deleteAll(String arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_DELETEALL_STRING, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_find(String arg0, List arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FIND_STRING_LIST, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_findBy(boolean arg0, int arg1, int arg2, String arg3, Object... arg4) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBY_BOOLEAN_INT_INT_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3, arg4 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_findBy(boolean arg0, String arg1, Object... arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBY_BOOLEAN_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_findByQuery(boolean arg0, int arg1, int arg2, String arg3, String arg4, Object... arg5) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBYQUERY_BOOLEAN_INT_INT_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3, arg4, arg5 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_findByQuery(boolean arg0, String arg1, String arg2, Object... arg3) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_findFieldTable() {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDFIELDTABLE, new Object[]{  });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_flush(String arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FLUSH_STRING, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_flushAll() {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FLUSHALL, new Object[]{  });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_get(String arg0, long arg1) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GET_STRING_LONG, new Object[]{ arg0, arg1 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_getBy(boolean arg0, String arg1, Object... arg2) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GETBY_BOOLEAN_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_getByQuery(boolean arg0, String arg1, String arg2, Object... arg3) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GETBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_insert(Record arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_INSERT_RECORD, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_sql(boolean arg0, boolean arg1, String arg2, String arg3, Object... arg4) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_SQL_BOOLEAN_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ arg0, arg1, arg2, arg3, arg4 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
		public Call makeCall_update(String arg0, long arg1, Chunk arg2, boolean arg3) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_UPDATE_STRING_LONG_CHUNK_BOOLEAN, new Object[]{ arg0, arg1, arg2, arg3 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
	public static final class IdAllotServiceProxy extends RPCProxyBase {
		public final class EnumCall{
			public static final int ORG_JOW_DBSRV_IDALLOT_IDALLOTSERVICE_APPLY_INT = 1;
		}
		
		private CallPoint remote;
		private Port localPort;
		
		/**
		 * 私有构造函数
		 * 防止实例被私自创建 必须通过newInstance函数
		 */
		private IdAllotServiceProxy() {}
	
		
		/**
		 * 获取实例
		 * @return
		 */
		public static IdAllotServiceProxy newInstance(CallPoint targetPoint) {
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.serviceId);
		}
		
		/**
		 * 获取实例
		 * @return
		 */
		public static IdAllotServiceProxy newInstance(String node, String port, Object id) {
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
		private static IdAllotServiceProxy createInstance(String node, String port, Object id) {
			IdAllotServiceProxy inst = new IdAllotServiceProxy();
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
		
		public void apply(int arg0) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_IDALLOT_IDALLOTSERVICE_APPLY_INT, new Object[]{ arg0 });
		}
		
		public Call makeCall_apply(int arg0) {
			Call call = localPort.makeCall(false, remote, EnumCall.ORG_JOW_DBSRV_IDALLOT_IDALLOTSERVICE_APPLY_INT, new Object[]{ arg0 });
			call.to.callerInfo = Utils.getCallerInfo();
			
			return call;
		}	
	}
	
}