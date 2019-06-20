package org.jow.common.entity.login;

import org.jow.core.gen.entity.Column;
import org.jow.core.gen.entity.Entity;

/**
 * 玩家账号表
 * @author Yangxianmeng
 *
 */
@Entity(entityName = "AccountDB", tableName = "login_account")
public enum EntityAccount {
	
	@Column(type=String.class, comment="账号名", index=true)
	account,
	@Column(type=String.class, comment="密码")
	password,
	@Column(type=String.class, comment="设备id", index=true)
	deviceId,
	@Column(type=String.class, comment="渠道id")
	channelId,
	@Column(type=int.class, comment="所在逻辑服编号", defaults="-1")
	gameIndex,
	
}
