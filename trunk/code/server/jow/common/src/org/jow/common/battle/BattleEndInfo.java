package org.jow.common.battle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jow.core.InputStream;
import org.jow.core.OutputStream;
import org.jow.core.interfaces.ISerializable;

/**
 * 对战结束 结算
 * 
 * @author Yangxianmeng
 *
 */
public class BattleEndInfo  implements ISerializable {
//	/** 名次 */
//	public int rank;
//	/** 模式 */
//	public int mode;
//	/** 击杀数 */
//	public int killCount;
//	/** 死亡数 */
//	public int deadCount;
//	/** 助攻数 */
//	public int assistCount;
//	/** 比赛时间 */
//	public long timestamp;
	/** 友方 */
	public List<Long> teamMate = new ArrayList<>();
	/** 敌方 */
	public List<Long> enemy = new ArrayList<>();
	@Override
	public void writeTo(OutputStream out) throws IOException {
//		out.write(rank);
//		out.write(mode);
//		out.write(killCount);
//		out.write(deadCount);
//		out.write(assistCount);
//		out.write(timestamp);
		out.write(teamMate);
		out.write(enemy);
	}
	@Override
	public void readFrom(InputStream in) throws IOException {
//		rank = in.read();
//		mode = in.read();
//		killCount = in.read();
//		deadCount = in.read();
//		assistCount = in.read();
//		timestamp = in.read();
		teamMate = in.read();
		enemy = in.read();
	}
	public List<Long> getTeamMate() {
		return teamMate;
	}
	public void setTeamMate(List<Long> teamMate) {
		this.teamMate = teamMate;
	}
	public List<Long> getEnemy() {
		return enemy;
	}
	public void setEnemy(List<Long> enemy) {
		this.enemy = enemy;
	}
	
}
