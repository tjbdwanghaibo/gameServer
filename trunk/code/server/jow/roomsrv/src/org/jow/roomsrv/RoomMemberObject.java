package org.jow.roomsrv;

import org.jow.common.battle.BattlePlayerVO;

/**
 * 房间成员对象
 * 
 * @author Yangxianmeng
 *
 */
public class RoomMemberObject {
	
	/** 成员index */
	private int index;
	/** 玩家战斗信息 */
	private BattlePlayerVO player;
	/** 阵营 */
	private int camp;
	/** 是否战斗 */
	private boolean isFigthing;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public BattlePlayerVO getPlayer() {
		return player;
	}
	public void setPlayer(BattlePlayerVO player) {
		this.player = player;
	}
	public int getCamp() {
		return camp;
	}
	public void setCamp(int camp) {
		this.camp = camp;
	}
	public boolean isFigthing() {
		return isFigthing;
	}
	public void setFigthing(boolean isFigthing) {
		this.isFigthing = isFigthing;
	}
}
