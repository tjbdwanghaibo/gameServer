package org.jow.common.support.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.jow.common.db.DB;
import org.jow.core.Record;
import org.jow.core.RecordTransient;
import org.jow.core.db.DBKey;
import org.jow.core.interfaces.IRecord;
import org.jow.core.support.Param;
import org.jow.core.support.log.LogCore;

public class DBUtils {

	/** 分页大小（每次加载1000条） */
	private static final int PAGE_SIZE = 1000;
	
	/**
	 * 起服，阻塞分页加载指定表
	 * @param tableName
	 * @param loder
	 * @param params
	 */
	private static <T extends IRecord> void i_findByWithPaging(String tableName, 
			Consumer<? super T> loder, Object...params) {
		
		DB prx = DB.newInstance(tableName);
		//获得记录条数
		prx.countBy(false);
		Param param = prx.waitForResult();
		
		int count = param.get();
		LogCore.temp.info("Start to load {}: count {}", tableName, count);
		long time = System.currentTimeMillis();
	
		//分页查询
		int loopCount = count / PAGE_SIZE;
		
		for (int i = 0; i < loopCount; i++) {
			prx.findBy(false, i * PAGE_SIZE, PAGE_SIZE, params);
			prx.waitForResult();
			
			List<T> records = param.get();
			for (T t : records) {
				loder.accept(t);
			}
		}
		LogCore.temp.info("Finish to load {}: cost time {}", tableName, System.currentTimeMillis() - time);
	}
	
	
	/**
	 * 起服时，分页加载指定表，只加载部分列
	 * @param tableName 表名
	 * @param colums 只返回指定列
	 * @param loader 每条记录的回调函数
	 */
	public static void findByWithPaging(String tableName,List<String> columns, Consumer<? super RecordTransient> loader) {
		i_findByWithPaging(tableName, loader, DBKey.COLUMN, columns);
	}
	
	/**
	 * 起服时， 阻塞加载全部列
	 * @param tableName
	 * @param loader
	 */
	public static void findbyWithPaging(String tableName, Consumer<? super Record> loader) {
		i_findByWithPaging(tableName, loader);
	}
	
	/**
	 * 起服时，阻塞分页加载指定表，指定where子句
	 * @param tableName 表名
	 * @param loader 每条记录的回调函数
	 * @param whereAndOther where子句
	 * @param params 查询参数
	 */
	private static <T extends IRecord> void i_findByQueryWithPaging(String tableName, Consumer<? super T> loader,
			String whereAndOther, Object... params) {

		DB prx = DB.newInstance(tableName);

		// 获得记录总条数
		prx.countBy(false);
		Param param = prx.waitForResult();

		int count = param.get();
		LogCore.temp.info("Start to load {}: count {}", tableName, count);
		long time = System.currentTimeMillis();
		// 分页查询
		int loopCount = count / PAGE_SIZE;
		for (int i = 0; i <= loopCount; i++) {
			prx.findByQuery(false, i * PAGE_SIZE, PAGE_SIZE, whereAndOther, params);
			param = prx.waitForResult();

			List<T> records = param.get();
			for (T r : records) {
				loader.accept(r);
			}
		}
		LogCore.temp.info("Finish to load {}: cost time {}", tableName, System.currentTimeMillis() - time);
	}
	
	/**
	 * 起服时，阻塞分页加载指定表，指定where子句，只加载部分列
	 * @param tableName 表名
	 * @param columns 只返回指定列
	 * @param loader 每条记录的回调函数
	 * @param whereAndOther where子句
	 * @param params where子句的参数
	 */
	public static void findByQueryWithPaging(String tableName, List<String> columns, 
			Consumer<? super RecordTransient> loader, String whereAndOther, Object... params) {
		List<Object> ps = new ArrayList<>(Arrays.asList(params));
		ps.add(DBKey.COLUMN);
		ps.add(columns);
		i_findByQueryWithPaging(tableName, loader, whereAndOther, ps.toArray());
	}
	
	/**
	 * 起服时，阻塞分页加载指定表，指定where子句，加载全部列
	 * @param tableName
	 * @param loader
	 * @param whereAndOther where子句
	 * @param params where自己的参数
	 */
	public static void findByQueryWithPaging(String tableName, Consumer<? super Record> loader, 
			String whereAndOther, Object... params) {
		i_findByQueryWithPaging(tableName, loader, whereAndOther, params);
	}
	
	
	
}
