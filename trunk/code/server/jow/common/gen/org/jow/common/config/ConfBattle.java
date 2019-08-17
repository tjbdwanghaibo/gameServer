package org.jow.common.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jow.core.interfaces.IReloadSupport;
import org.jow.core.support.configData.ConfigBase;
import org.jow.core.support.configData.ConfigJSON;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@ConfigJSON
public class ConfBattle extends ConfigBase {
	/** id */
	public final int sn;
	/** 名称 */
	public final String name;
	/** 是否为排名模式 */
	public final Boolean rank;
	/** 是否允许复活 */
	public final Boolean revive;
	/** 出生坐标 */
	public final int[] bornPos;
	/** 出生朝向 */
	public final int[] bornDir;
	/** 地图宽度（10000相当于Unity世界坐标1米） */
	public final int width;
	/** 地图高度 */
	public final int height;
	/** 机器人 */
	public final int[] robots;
	/** 机器人阵营 */
	public final int[] robotCamps;
	/** 随机障碍数量 */
	public final int obsRandomCount;
	/** 掉落间隔 */
	public final int dropInterval;
	/** 固定位置掉落间隔 */
	public final int dropStaticInterval;
	/** 固定位置延迟 */
	public final int dropStaticDelay;
	/** 最大掉落数量 */
	public final int maxDropCount;
	/** 玩家死亡掉落 */
	public final int playerDrop;
	/** 固定点怪物刷新间隔 */
	public final int monsterStaticInterval;
	/** 固定怪物刷新延迟 */
	public final int monsterStaticDelay;
	/** 怪物刷新间隔 */
	public final int monsterInterval;
	/** 怪物刷新延迟 */
	public final int monsterDelay;
	/** 时间 */
	public final int timeOut;
	/** 怪物刷新上限 */
	public final int maxMonsterCount;
	/** 塔1 */
	public final int basement1;
	/** 塔2 */
	public final int basement2;
	/** 阵营1坐标 */
	public final int[] camp1Pos;
	/** 阵营2坐标 */
	public final int[] camp2Pos;
	/** 阵营1朝向 */
	public final int[] camp1Dir;
	/** 阵营2朝向 */
	public final int[] camp2Dir;

	private ConfBattle(int sn, String name, Boolean rank, Boolean revive, int[] bornPos, int[] bornDir, int width, int height, int[] robots, int[] robotCamps, int obsRandomCount, int dropInterval, int dropStaticInterval, int dropStaticDelay, int maxDropCount, int playerDrop, int monsterStaticInterval, int monsterStaticDelay, int monsterInterval, int monsterDelay, int timeOut, int maxMonsterCount, int basement1, int basement2, int[] camp1Pos, int[] camp2Pos, int[] camp1Dir, int[] camp2Dir) {
			this.sn = sn;		
			this.name = name;		
			this.rank = rank;		
			this.revive = revive;		
			this.bornPos = bornPos;		
			this.bornDir = bornDir;		
			this.width = width;		
			this.height = height;		
			this.robots = robots;		
			this.robotCamps = robotCamps;		
			this.obsRandomCount = obsRandomCount;		
			this.dropInterval = dropInterval;		
			this.dropStaticInterval = dropStaticInterval;		
			this.dropStaticDelay = dropStaticDelay;		
			this.maxDropCount = maxDropCount;		
			this.playerDrop = playerDrop;		
			this.monsterStaticInterval = monsterStaticInterval;		
			this.monsterStaticDelay = monsterStaticDelay;		
			this.monsterInterval = monsterInterval;		
			this.monsterDelay = monsterDelay;		
			this.timeOut = timeOut;		
			this.maxMonsterCount = maxMonsterCount;		
			this.basement1 = basement1;		
			this.basement2 = basement2;		
			this.camp1Pos = camp1Pos;		
			this.camp2Pos = camp2Pos;		
			this.camp1Dir = camp1Dir;		
			this.camp2Dir = camp2Dir;		
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
	public static Collection<ConfBattle> findAll() {
		return DATA.getList();
	}

	/**
	 * 通过SN获取数据
	 * @param sn
	 * @return
	 */
	public static ConfBattle get(Integer sn) {
		return DATA.getMap().get(sn);
	}

	/**
	 * 通过SN判断是否拥有该物品（针对装备移除item表的暂时策略）
	 * @param sn
	 * @return
	 */
	public static boolean contains(Integer sn) {
		return DATA.getMap().containsKey(sn);
	}

	/**
	 * 获取全部key
	 * @return
	 */
	public static Set<Integer> findKeys() {
		return DATA.getMap().keySet();
	}

	/**
	 * 通过属性获取单条数据
	 * @param params
	 * @return
	 */
	public static ConfBattle getBy(Object...params) {
		List<ConfBattle> list = utilsBase(DATA.getList(), params);
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
	public static List<ConfBattle> findBy(Object...params) {
		return utilsBase(DATA.getList(), params);
	}

	/**
	 * 属性关键字
	 */
	public static final class K {
		/** id */
		public static final String sn = "sn";
		/** 名称 */
		public static final String name = "name";
		/** 是否为排名模式 */
		public static final String rank = "rank";
		/** 是否允许复活 */
		public static final String revive = "revive";
		/** 出生坐标 */
		public static final String bornPos = "bornPos";
		/** 出生朝向 */
		public static final String bornDir = "bornDir";
		/** 地图宽度（10000相当于Unity世界坐标1米） */
		public static final String width = "width";
		/** 地图高度 */
		public static final String height = "height";
		/** 机器人 */
		public static final String robots = "robots";
		/** 机器人阵营 */
		public static final String robotCamps = "robotCamps";
		/** 随机障碍数量 */
		public static final String obsRandomCount = "obsRandomCount";
		/** 掉落间隔 */
		public static final String dropInterval = "dropInterval";
		/** 固定位置掉落间隔 */
		public static final String dropStaticInterval = "dropStaticInterval";
		/** 固定位置延迟 */
		public static final String dropStaticDelay = "dropStaticDelay";
		/** 最大掉落数量 */
		public static final String maxDropCount = "maxDropCount";
		/** 玩家死亡掉落 */
		public static final String playerDrop = "playerDrop";
		/** 固定点怪物刷新间隔 */
		public static final String monsterStaticInterval = "monsterStaticInterval";
		/** 固定怪物刷新延迟 */
		public static final String monsterStaticDelay = "monsterStaticDelay";
		/** 怪物刷新间隔 */
		public static final String monsterInterval = "monsterInterval";
		/** 怪物刷新延迟 */
		public static final String monsterDelay = "monsterDelay";
		/** 时间 */
		public static final String timeOut = "timeOut";
		/** 怪物刷新上限 */
		public static final String maxMonsterCount = "maxMonsterCount";
		/** 塔1 */
		public static final String basement1 = "basement1";
		/** 塔2 */
		public static final String basement2 = "basement2";
		/** 阵营1坐标 */
		public static final String camp1Pos = "camp1Pos";
		/** 阵营2坐标 */
		public static final String camp2Pos = "camp2Pos";
		/** 阵营1朝向 */
		public static final String camp1Dir = "camp1Dir";
		/** 阵营2朝向 */
		public static final String camp2Dir = "camp2Dir";
	}

	/**
	 * 数据集
	 * 单独提出来也是为了做数据延迟初始化
	 * 避免启动遍历类时，触发了static静态块
	 */
	private static final class DATA {
		/** 全部数据 */
		private static volatile Map<Integer, ConfBattle> _map;

		private static String name = "ConfBattle";

		/**
		 * 获取数据的值集合
		 * @return
		 */
		public static Collection<ConfBattle> getList() {
			return getMap().values();
		}
		
		/**
		 * 获取Map类型数据集合
		 * @return
		 */
		public static Map<Integer, ConfBattle> getMap() {
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
			Map<Integer, ConfBattle> dataMap = new HashMap<>();
			
			// JSON数据
			String confJSON = readConfigFile(name);
			if (StringUtils.isBlank(confJSON)) {
				return;
			}

			// 填充实体数据
			JSONArray confs = (JSONArray)JSONArray.parse(confJSON);
			for (int i = 0 ; i < confs.size() ; i++) {
				JSONObject conf = confs.getJSONObject(i);
				ConfBattle object = new ConfBattle(conf.getIntValue("sn"), conf.getString("name"), conf.getBooleanValue("rank"), conf.getBooleanValue("revive"), 
				parseIntArray(conf.getString("bornPos")), parseIntArray(conf.getString("bornDir")), conf.getIntValue("width"), conf.getIntValue("height"), 
				parseIntArray(conf.getString("robots")), parseIntArray(conf.getString("robotCamps")), conf.getIntValue("obsRandomCount"), conf.getIntValue("dropInterval"), 
				conf.getIntValue("dropStaticInterval"), conf.getIntValue("dropStaticDelay"), conf.getIntValue("maxDropCount"), conf.getIntValue("playerDrop"), 
				conf.getIntValue("monsterStaticInterval"), conf.getIntValue("monsterStaticDelay"), conf.getIntValue("monsterInterval"), conf.getIntValue("monsterDelay"), 
				conf.getIntValue("timeOut"), conf.getIntValue("maxMonsterCount"), conf.getIntValue("basement1"), conf.getIntValue("basement2"), 
				parseIntArray(conf.getString("camp1Pos")), parseIntArray(conf.getString("camp2Pos")), parseIntArray(conf.getString("camp1Dir")), parseIntArray(conf.getString("camp2Dir")));
				dataMap.put(conf.getInteger("sn"), object);
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
	public <T> T getFileValue(String key) {
		Object value = null;
		switch (key) {
			case "sn": {
				value = this.sn;
				break;
			}
			case "name": {
				value = this.name;
				break;
			}
			case "rank": {
				value = this.rank;
				break;
			}
			case "revive": {
				value = this.revive;
				break;
			}
			case "bornPos": {
				value = this.bornPos;
				break;
			}
			case "bornDir": {
				value = this.bornDir;
				break;
			}
			case "width": {
				value = this.width;
				break;
			}
			case "height": {
				value = this.height;
				break;
			}
			case "robots": {
				value = this.robots;
				break;
			}
			case "robotCamps": {
				value = this.robotCamps;
				break;
			}
			case "obsRandomCount": {
				value = this.obsRandomCount;
				break;
			}
			case "dropInterval": {
				value = this.dropInterval;
				break;
			}
			case "dropStaticInterval": {
				value = this.dropStaticInterval;
				break;
			}
			case "dropStaticDelay": {
				value = this.dropStaticDelay;
				break;
			}
			case "maxDropCount": {
				value = this.maxDropCount;
				break;
			}
			case "playerDrop": {
				value = this.playerDrop;
				break;
			}
			case "monsterStaticInterval": {
				value = this.monsterStaticInterval;
				break;
			}
			case "monsterStaticDelay": {
				value = this.monsterStaticDelay;
				break;
			}
			case "monsterInterval": {
				value = this.monsterInterval;
				break;
			}
			case "monsterDelay": {
				value = this.monsterDelay;
				break;
			}
			case "timeOut": {
				value = this.timeOut;
				break;
			}
			case "maxMonsterCount": {
				value = this.maxMonsterCount;
				break;
			}
			case "basement1": {
				value = this.basement1;
				break;
			}
			case "basement2": {
				value = this.basement2;
				break;
			}
			case "camp1Pos": {
				value = this.camp1Pos;
				break;
			}
			case "camp2Pos": {
				value = this.camp2Pos;
				break;
			}
			case "camp1Dir": {
				value = this.camp1Dir;
				break;
			}
			case "camp2Dir": {
				value = this.camp2Dir;
				break;
			}
			default: break;
		}
		
		return (T) value;
	}

}