package org.jow.common.entity.core;

import org.jow.core.gen.entity.Column;
import org.jow.core.gen.entity.Entity;

@Entity(entityName = "IdAllot",tableName = "core_id_allot")
public enum EntityIdAllot {
	@Column(type=long.class,comment = "当前分配的ID最大值", index=true)
	value,
	;
}
