package org.jow.matchsrv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jow.common.battle.BattlePlayerVO;
import org.jow.core.support.random.RandomUtils;

/**
 * 机器人
 * 
 * @author Yangxianmeng
 *
 */
public class RobotDataSet {

	/** 机器人上限 */
	private static final int ROBOT_NUM_MAX = 100;

	/** 机器人数据 */
	private List<BattlePlayerVO> robots = new ArrayList<>();

	public boolean isEmpty() {
		return robots.isEmpty();
	}

	/**
	 * 新增机器人，达到上限时会随机替换老的
	 * 
	 * @param robotNew
	 */
	public void add(BattlePlayerVO robotNew) {
		BattlePlayerVO robot = find(robotNew.getId());
		// 已经存在的机器人，更新机器人数据
		if (robot != null) {
			robot.copy(robotNew);
		} else {
			// 数量未达到上限 新增
			if (robots.size() < ROBOT_NUM_MAX) {
				robot = new BattlePlayerVO(robotNew);
				robots.add(robot);
			} else {
				// 替换概率是50%
				if (RandomUtils.nextFloat() < 0.5f) {
					// 随机一个现有的机器人替换掉
					int randomIndex = RandomUtils.nextInt(robots.size());
					robot = new BattlePlayerVO(robotNew);
					// [API] ArrayList : public E set(int index, E element), 用指定的元素替换此列表中指定位置的元素。
					robots.set(randomIndex, robot);
				}
			}
		}
		// 机器人不需要连接点和游戏点
		if (robot != null) {
			robot.setConnPoint(null);
			robot.setGamePoint(null);
		}

	}
	
	/**
	 * 新增机器人
	 * @param robots
	 */
	public void add(List<BattlePlayerVO> robots) {
		for (BattlePlayerVO battlePlayerVO : robots) {
			add(battlePlayerVO);
		}
	}
	
	
	private BattlePlayerVO find(long id) {
		for (BattlePlayerVO battlePlayerVO : robots) {
			if (battlePlayerVO.getId() == id) {
				return battlePlayerVO;
			}
		}
		return null;
	}
	
	/**
	 * 随机挑选num个不重复的机器人
	 * @param num
	 * @param exceptions
	 * @return
	 */
	public List<BattlePlayerVO> randomSelect(int num, List<Long> exceptions){
		if(robots.isEmpty()) {
			return Collections.emptyList();
		}
		
		if(RandomUtils.nextFloat() < 0.1f) {
			Collections.shuffle(robots,RandomUtils.JVM_RANDOM);
		}
		
		List<BattlePlayerVO> results = new ArrayList<>();
		num = Math.min(num, robots.size());
		int startIndex = RandomUtils.nextInt(robots.size());
		
		for (int i = 0; i < robots.size(); i++) {
			BattlePlayerVO robot = robots.get((i + startIndex) %robots.size());
			if(exceptions.contains(robot.getId())) {
				continue;
			}
			results.add(robot);
			if(results.size() >= num) {
				break;
			}
		}
		return results;
	}
}
