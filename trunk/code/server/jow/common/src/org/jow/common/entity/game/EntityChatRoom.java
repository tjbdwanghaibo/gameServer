package org.jow.common.entity.game;

import org.jow.core.gen.entity.Column;
import org.jow.core.gen.entity.Entity;

/**
 * 多人聊天
 * 
 * @author Administrator
 *
 */
@Entity(entityName="ChatDB", tableName = "game_chat")
public enum EntityChatRoom {
	
	@Column(type=long.class, comment = "房间Id", index=true)
	roomId,
	@Column(type = long.class, comment = "玩家Id", index=true)
	humanId,
	@Column(type = int.class, comment = "身份")
	identity,
	@Column(type = long.class, comment = "发言时间")
	time,
	
}
