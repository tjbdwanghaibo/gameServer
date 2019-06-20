package org.jow.common.entity.central;

import org.jow.core.gen.entity.Column;
import org.jow.core.gen.entity.Entity;

/**
 * 玩家简要信息，中心服缓存
 * @author Administrator
 *
 */
@Entity(entityName="HumanSimpleDB", tableName="centralsrv_human")
public enum EntityHumanSimple {

	@Column(type=String.class,comment="玩家姓名")
	name,
	@Column(type=String.class,comment="玩家头像")
	head,
	@Column(type=int.class,comment="性别")
	sex,
	
}
