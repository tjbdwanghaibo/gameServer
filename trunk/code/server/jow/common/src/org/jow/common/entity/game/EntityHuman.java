package org.jow.common.entity.game;

import org.jow.core.gen.entity.Column;
import org.jow.core.gen.entity.Entity;

/**
 * 角色数据
 * @author Yangxianmeng
 *
 */
@Entity(entityName = "HumanDB", tableName = "game_human")
public enum EntityHuman {
	@Column(type = String.class, comment = "玩家姓名",index = true)
	name,
	@Column(type = int.class, comment = "玩家等级")
	level,
	@Column(type = String.class, comment = "玩家头像")
	head,
	@Column(type = int.class, comment = "玩家性别")
	sex,
	@Column(type=String.class, comment="签名", defaults="")
	signature,
	@Column(type=long.class, comment="最后一次登入时间")
	timeLogin,
	@Column(type=long.class, comment="最后一次登出时间")
	timeLogout,
}
