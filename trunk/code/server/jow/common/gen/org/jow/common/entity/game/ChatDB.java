package org.jow.common.entity.game;

import org.apache.commons.lang3.exception.ExceptionUtils;

import org.jow.core.db.DBConsts;
import org.jow.core.OutputStream;
import org.jow.core.Port;
import org.jow.core.Record;
import org.jow.core.support.SysException;
import org.jow.common.db.DB;
import org.jow.core.support.log.LogCore;
import org.jow.core.entity.EntityBase;
import org.jow.core.gen.JowGenFile;

@JowGenFile
public final class ChatDB extends EntityBase {
	
	public static final String tableName = "game_chat";

	/**
	 * 属性关键字
	 */
	public static final class K {
		/** id */
		public static final String id = "id";
		/** 房间Id */
		public static final String roomId = "roomId";
		/** 玩家Id */
		public static final String humanId = "humanId";
		/** 身份 */
		public static final String identity = "identity";
		/** 发言时间 */
		public static final String time = "time";
	}

	@Override
	public String getTableName() {
		return tableName;
	}
	
	public ChatDB() {
		super();
	}

	public ChatDB(Record record) {
		super(record);
	}

	/**
	 * 新增数据
	 */
	@Override
	public void persist() {
		// 状态错误
		if (record.getStatus() != DBConsts.RECORD_STATUS_NEW) {
			LogCore.db.error("只有新增包能调用persist函数，请确认状态：data={}, stackTrace={}", this, ExceptionUtils.getStackTrace(new Throwable()));
			return;
		}
		
		DB prx = DB.newInstance(getTableName());
		prx.insert(record);
		
		// 重置状态
		record.resetStatus();
	}

	/**
	 * 同步修改数据至DB服务器
	 * 默认不立即持久化到数据库
	 */
	@Override
	public void update() {
		update(false);
	}
	
	/**
	 * 同步修改数据至DB服务器
	 * @param sync 是否立即同持久化到数据库
	 */
	@Override
	public void update(boolean sync) {
		// 新增包不能直接调用update函数 请先调用persist
		if (record.getStatus() == DBConsts.RECORD_STATUS_NEW) {
			throw new SysException("新增包不能直接调用update函数，请先调用persist：data={}", this);
		}
		
		// 升级包
		try(OutputStream patch = record.patchUpdateGen()) {
			if (patch == null || patch.getLength() == 0) {
				return;
			}
	
			// 将升级包同步至DB服务器
			DB prx = DB.newInstance(getTableName());
			prx.update(getId(), patch.getChunk(), sync);
		}
		
		// 重置状态
		record.resetStatus();
	}

	/**
	 * 删除数据
	 */
	@Override
	public void remove() {
		// 新建的记录，尚未存盘，直接返回
		if (record.getStatus() == DBConsts.RECORD_STATUS_NEW) {
			return;
		}
		
		DB prx = DB.newInstance(getTableName());
		prx.delete(getId());
	}

	/**
	 * 获取指定字段值
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getField(String key) {
		Object value = null;
		if (key == null) {
			return (T) value;
		}
		switch (key) {
			case K.id: {
				value = getId();
				break;
			}
			case K.roomId: {
				value = getRoomId();
				break;
			}
			case K.humanId: {
				value = getHumanId();
				break;
			}
			case K.identity: {
				value = getIdentity();
				break;
			}
			case K.time: {
				value = getTime();
				break;
			}
			default: break;
		}
		
		return (T) value;
	}
	
	public void setField(String key, Object value) {
		if (key == null) {
			return;
		}

		switch (key) {
			case K.id: {
				setId((long) value);
				break;
			}
			case K.roomId: {
				setRoomId((long) value);
				break;
			}
			case K.humanId: {
				setHumanId((long) value);
				break;
			}
			case K.identity: {
				setIdentity((int) value);
				break;
			}
			case K.time: {
				setTime((long) value);
				break;
			}
			default: break;
		}
	}

	/**
	 * id
	 */
	public long getId() {
		return record.get("id");
	}

	public void setId(final long id) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("id", id);

		//更新后的数据状态
		int statusNew = record.getStatus();
		//1.如果更新前是普通状态 and 更新后是修改状态，那么就记录这条数据，用来稍后自动提交。
		//2.哪怕之前是修改状态，只要数据是刚创建或串行化过来的新对象，则也会记录修改，因为有些时候会串行化过来一个修改状态下的数据。
		if ((statusOld == DBConsts.RECORD_STATUS_NONE && statusNew == DBConsts.RECORD_STATUS_MODIFIED) ||
		   (statusOld == DBConsts.RECORD_STATUS_MODIFIED && record.isNewness())) {
			//记录修改的数据 用来稍后自动提交
			Port.getCurrent().addEntityModify(this);
			//如果是刚创建或串行化过来的新对象 取消这个标示
			if (record.isNewness()) {
				record.setNewness(false);
			}
		}
	}
	/**
	 * 房间Id
	 */
	public long getRoomId() {
		return record.get("roomId");
	}

	public void setRoomId(final long roomId) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("roomId", roomId);

		//更新后的数据状态
		int statusNew = record.getStatus();
		//1.如果更新前是普通状态 and 更新后是修改状态，那么就记录这条数据，用来稍后自动提交。
		//2.哪怕之前是修改状态，只要数据是刚创建或串行化过来的新对象，则也会记录修改，因为有些时候会串行化过来一个修改状态下的数据。
		if ((statusOld == DBConsts.RECORD_STATUS_NONE && statusNew == DBConsts.RECORD_STATUS_MODIFIED) ||
		   (statusOld == DBConsts.RECORD_STATUS_MODIFIED && record.isNewness())) {
			//记录修改的数据 用来稍后自动提交
			Port.getCurrent().addEntityModify(this);
			//如果是刚创建或串行化过来的新对象 取消这个标示
			if (record.isNewness()) {
				record.setNewness(false);
			}
		}
	}
	/**
	 * 玩家Id
	 */
	public long getHumanId() {
		return record.get("humanId");
	}

	public void setHumanId(final long humanId) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("humanId", humanId);

		//更新后的数据状态
		int statusNew = record.getStatus();
		//1.如果更新前是普通状态 and 更新后是修改状态，那么就记录这条数据，用来稍后自动提交。
		//2.哪怕之前是修改状态，只要数据是刚创建或串行化过来的新对象，则也会记录修改，因为有些时候会串行化过来一个修改状态下的数据。
		if ((statusOld == DBConsts.RECORD_STATUS_NONE && statusNew == DBConsts.RECORD_STATUS_MODIFIED) ||
		   (statusOld == DBConsts.RECORD_STATUS_MODIFIED && record.isNewness())) {
			//记录修改的数据 用来稍后自动提交
			Port.getCurrent().addEntityModify(this);
			//如果是刚创建或串行化过来的新对象 取消这个标示
			if (record.isNewness()) {
				record.setNewness(false);
			}
		}
	}
	/**
	 * 身份
	 */
	public int getIdentity() {
		return record.get("identity");
	}

	public void setIdentity(final int identity) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("identity", identity);

		//更新后的数据状态
		int statusNew = record.getStatus();
		//1.如果更新前是普通状态 and 更新后是修改状态，那么就记录这条数据，用来稍后自动提交。
		//2.哪怕之前是修改状态，只要数据是刚创建或串行化过来的新对象，则也会记录修改，因为有些时候会串行化过来一个修改状态下的数据。
		if ((statusOld == DBConsts.RECORD_STATUS_NONE && statusNew == DBConsts.RECORD_STATUS_MODIFIED) ||
		   (statusOld == DBConsts.RECORD_STATUS_MODIFIED && record.isNewness())) {
			//记录修改的数据 用来稍后自动提交
			Port.getCurrent().addEntityModify(this);
			//如果是刚创建或串行化过来的新对象 取消这个标示
			if (record.isNewness()) {
				record.setNewness(false);
			}
		}
	}
	/**
	 * 发言时间
	 */
	public long getTime() {
		return record.get("time");
	}

	public void setTime(final long time) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("time", time);

		//更新后的数据状态
		int statusNew = record.getStatus();
		//1.如果更新前是普通状态 and 更新后是修改状态，那么就记录这条数据，用来稍后自动提交。
		//2.哪怕之前是修改状态，只要数据是刚创建或串行化过来的新对象，则也会记录修改，因为有些时候会串行化过来一个修改状态下的数据。
		if ((statusOld == DBConsts.RECORD_STATUS_NONE && statusNew == DBConsts.RECORD_STATUS_MODIFIED) ||
		   (statusOld == DBConsts.RECORD_STATUS_MODIFIED && record.isNewness())) {
			//记录修改的数据 用来稍后自动提交
			Port.getCurrent().addEntityModify(this);
			//如果是刚创建或串行化过来的新对象 取消这个标示
			if (record.isNewness()) {
				record.setNewness(false);
			}
		}
	}

}