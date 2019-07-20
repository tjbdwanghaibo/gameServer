package org.jow.common.idAllot;

/**
 * 
 * id相关
 * 
 * @author Yangxianmeng
 * 
 */
public class IdUtils {
	
	/**
	 * 玩家id转成指定服玩家id
	 * @param id
	 * @param gameIndex
	 * @return
	 */
	public static long toGameId(long id, int gameIndex) {
		long actualId = id % (long)Math.pow(10, IdAllotPool.ID_DECIMAL_NUM);
		return gameIndex * (long)Math.pow(10, IdAllotPool.ID_DECIMAL_NUM) + actualId;
	}

	/**
	 * 根据id获取gameIndex
	 * @param id
	 * @return
	 */
	public static int getGameIndex(long id) {
		if (id < 0) {
			return -1;
		}
		
		long index = id / (long)Math.pow(10, IdAllotPool.ID_DECIMAL_NUM);
		return (int)index;
	}
	
}
