package org.jow.dbsrv;
                    
import org.jow.core.CallPoint;
import org.jow.core.Port;
import org.jow.core.support.Param;
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
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.servId);
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
		
		public void count(String verTable, long verNumber, String sql, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_COUNT_STRING_LONG_STRING_OBJECTS, new Object[]{ verTable, verNumber, sql, params });
		}
		
		public void execute(String verTable, long verNumber, boolean needResult, String sql, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_EXECUTE_STRING_LONG_BOOLEAN_STRING_OBJECTS, new Object[]{ verTable, verNumber, needResult, sql, params });
		}
		
		public void query(String verTable, long verNumber, String sql, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBLINESERVICE_QUERY_STRING_LONG_STRING_OBJECTS, new Object[]{ verTable, verNumber, sql, params });
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
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.servId);
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
		
		public void countAll(boolean flush, String tableName) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTALL_BOOLEAN_STRING, new Object[]{ flush, tableName });
		}
		
		public void countBy(boolean flush, String tableName, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTBY_BOOLEAN_STRING_OBJECTS, new Object[]{ flush, tableName, params });
		}
		
		public void countByQuery(boolean flush, String tableName, String whereAndOther, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_COUNTBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ flush, tableName, whereAndOther, params });
		}
		
		public void delete(String tableName, long id) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_DELETE_STRING_LONG, new Object[]{ tableName, id });
		}
		
		public void deleteAll(String tableName) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_DELETEALL_STRING, new Object[]{ tableName });
		}
		
		public void find(String tableName, List ids) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FIND_STRING_LIST, new Object[]{ tableName, ids });
		}
		
		public void findBy(boolean flush, int firstResult, int maxResults, String tableName, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBY_BOOLEAN_INT_INT_STRING_OBJECTS, new Object[]{ flush, firstResult, maxResults, tableName, params });
		}
		
		public void findBy(boolean flush, String tableName, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBY_BOOLEAN_STRING_OBJECTS, new Object[]{ flush, tableName, params });
		}
		
		public void findByQuery(boolean flush, int firstResult, int maxResults, String tableName, String whereAndOther, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBYQUERY_BOOLEAN_INT_INT_STRING_STRING_OBJECTS, new Object[]{ flush, firstResult, maxResults, tableName, whereAndOther, params });
		}
		
		public void findByQuery(boolean flush, String tableName, String whereAndOther, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ flush, tableName, whereAndOther, params });
		}
		
		public void findFieldTable() {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FINDFIELDTABLE, new Object[]{  });
		}
		
		public void flush(String tableName) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FLUSH_STRING, new Object[]{ tableName });
		}
		
		public void flushAll() {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_FLUSHALL, new Object[]{  });
		}
		
		public void get(String tableName, long id) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GET_STRING_LONG, new Object[]{ tableName, id });
		}
		
		public void getBy(boolean flush, String tableName, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GETBY_BOOLEAN_STRING_OBJECTS, new Object[]{ flush, tableName, params });
		}
		
		public void getByQuery(boolean flush, String tableName, String whereAndOther, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_GETBYQUERY_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ flush, tableName, whereAndOther, params });
		}
		
		public void insert(Record record) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_INSERT_RECORD, new Object[]{ record });
		}
		
		public void sql(boolean needResult, boolean flush, String tableName, String sql, Object... params) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_SQL_BOOLEAN_BOOLEAN_STRING_STRING_OBJECTS, new Object[]{ needResult, flush, tableName, sql, params });
		}
		
		public void update(String tableName, long id, Chunk patch, boolean sync) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_DBPARTSERVICE_UPDATE_STRING_LONG_CHUNK_BOOLEAN, new Object[]{ tableName, id, patch, sync });
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
			return createInstance(targetPoint.nodeId, targetPoint.portId, targetPoint.servId);
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
		
		public void apply(int num) {
			remote.callerInfo = Utils.getCallerInfo();
			localPort.call(false, remote, EnumCall.ORG_JOW_DBSRV_IDALLOT_IDALLOTSERVICE_APPLY_INT, new Object[]{ num });
		}
	}
	
}