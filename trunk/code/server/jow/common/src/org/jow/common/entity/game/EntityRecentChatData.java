package org.jow.common.entity.game;

import org.jow.core.gen.entity.Column;
import org.jow.core.gen.entity.Entity;
/**
*
* 最近游戏
*/
@Entity(entityName="RecentChatDataDB", tableName="game_recent_chat")
public enum EntityRecentChatData {
	@Column(type=long.class, comment="玩家id", index=true)
	humanId,
	@Column(type=long.class, comment="玩家id")
	playerId,
	@Column(type=long.class, comment="聊天时间")
	time,
}
