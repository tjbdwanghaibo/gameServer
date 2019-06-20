package org.jow.common.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jow.core.configData.ConfBase;
import org.jow.core.configData.ConfigJSON;
import org.jow.core.interfaces.IReloadSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 参数|Param 
 * Param.xlsx
 * @author System
 * 此类是系统自动生成类 不要直接修改，修改后也会被覆盖
 */
@ConfigJSON
public class ConfParam extends ConfBase {
	/** SN */
	public final String sn;
	/** 值 */
	public final String value;

	private ConfParam(String sn, String value) {
			this.sn = sn;		
			this.value = value;		
	}

	private static IReloadSupport support = null;
	
	public static void initReloadSupport(IReloadSupport s) {
		support = s;
	}
	
	public static void reload() {
		if (support != null)
			support.beforeReload();
		DATA._init();
		
		if (support != null)
			support.afterReload();
	}

	/**
	 * 获取全部数据
	 * @return
	 */
	public static Collection<ConfParam> findAll() {
		return DATA.getList();
	}

	/**
	 * 通过SN获取数据
	 * @param sn
	 * @return
	 */
	public static ConfParam get(String sn) {
		return DATA.getMap().get(sn);
	}

	/**
	 * 通过SN判断是否拥有该物品（针对装备移除item表的暂时策略）
	 * @param sn
	 * @return
	 */
	public static boolean contains(String sn) {
		return DATA.getMap().containsKey(sn);
	}

	/**
	 * 获取全部key
	 * @return
	 */
	public static Set<String> findKeys() {
		return DATA.getMap().keySet();
	}

	/**
	 * 通过属性获取单条数据
	 * @param params
	 * @return
	 */
	public static ConfParam getBy(Object...params) {
		List<ConfParam> list = utilBase(DATA.getList(), params);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	/**
	 * 通过属性获取数据集合
	 * @param params
	 * @return
	 */
	public static List<ConfParam> findBy(Object...params) {
		return utilBase(DATA.getList(), params);
	}

	/**
	 * 属性关键字
	 */
	public static final class K {
		/** SN */
		public static final String sn = "sn";
		/** 值 */
		public static final String value = "value";
	}

	/**
	 * 数据集
	 * 单独提出来也是为了做数据延迟初始化
	 * 避免启动遍历类时，触发了static静态块
	 */
	private static final class DATA {
		/** 全部数据 */
		private static volatile Map<String, ConfParam> _map;

		private static String name = "ConfParam";

		/**
		 * 获取数据的值集合
		 * @return
		 */
		public static Collection<ConfParam> getList() {
			return getMap().values();
		}
		
		/**
		 * 获取Map类型数据集合
		 * @return
		 */
		public static Map<String, ConfParam> getMap() {
			// 延迟初始化
			if (_map == null) {
				synchronized (DATA.class) {
					if (_map == null) {
						_init();
					}
				}
			}
			
			return _map;
		}

		/**
		 * 初始化数据
		 */
		private static void _init() {
			Map<String, ConfParam> dataMap = new HashMap<>();
			
			// JSON数据
			String confJSON = readConfFile(name);
			if (StringUtils.isBlank(confJSON)) {
				return;
			}

			// 填充实体数据
			JSONArray confs = (JSONArray)JSONArray.parse(confJSON);
			for (int i = 0 ; i < confs.size() ; i++) {
				JSONObject conf = confs.getJSONObject(i);
				ConfParam object = new ConfParam(conf.getString("sn"), conf.getString("value"));
				dataMap.put(conf.getString("sn"), object);
			}

			// 保存数据
			_map = Collections.unmodifiableMap(dataMap);
		}

	}
    
	/**
	 *
	 * 取得属性值
	 * @param classInstance 实例
	 * @key 属性名称
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getFieldValue(String key) {
		Object value = null;
		switch (key) {
			case "sn": {
				value = this.sn;
				break;
			}
			case "value": {
				value = this.value;
				break;
			}
			default: break;
		}
		
		return (T) value;
	}

}