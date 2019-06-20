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
public final class HumanDB extends EntityBase {
	
	public static final String tableName = "game_human";

	/**
	 * 属性关键字
	 */
	public static final class K {
		/** id */
		public static final String id = "id";
		/** 玩家姓名 */
		public static final String name = "name";
		/** 玩家等级 */
		public static final String level = "level";
		/** 玩家头像 */
		public static final String head = "head";
		/** 玩家性别 */
		public static final String sex = "sex";
		/** 签名 */
		public static final String signature = "signature";
		/** 最后一次登入时间 */
		public static final String timeLogin = "timeLogin";
		/** 最后一次登出时间 */
		public static final String timeLogout = "timeLogout";
	}

	@Override
	public String getTableName() {
		return tableName;
	}
	
	public HumanDB() {
		super();
	}

	public HumanDB(Record record) {
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
			case K.name: {
				value = getName();
				break;
			}
			case K.level: {
				value = getLevel();
				break;
			}
			case K.head: {
				value = getHead();
				break;
			}
			case K.sex: {
				value = getSex();
				break;
			}
			case K.signature: {
				value = getSignature();
				break;
			}
			case K.timeLogin: {
				value = getTimeLogin();
				break;
			}
			case K.timeLogout: {
				value = getTimeLogout();
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
			case K.name: {
				setName((String) value);
				break;
			}
			case K.level: {
				setLevel((int) value);
				break;
			}
			case K.head: {
				setHead((String) value);
				break;
			}
			case K.sex: {
				setSex((int) value);
				break;
			}
			case K.signature: {
				setSignature((String) value);
				break;
			}
			case K.timeLogin: {
				setTimeLogin((long) value);
				break;
			}
			case K.timeLogout: {
				setTimeLogout((long) value);
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
	 * 玩家姓名
	 */
	public String getName() {
		return record.get("name");
	}

	public void setName(final String name) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("name", name);

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
	 * 玩家等级
	 */
	public int getLevel() {
		return record.get("level");
	}

	public void setLevel(final int level) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("level", level);

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
	 * 玩家头像
	 */
	public String getHead() {
		return record.get("head");
	}

	public void setHead(final String head) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("head", head);

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
	 * 玩家性别
	 */
	public int getSex() {
		return record.get("sex");
	}

	public void setSex(final int sex) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("sex", sex);

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
	 * 签名
	 */
	public String getSignature() {
		return record.get("signature");
	}

	public void setSignature(final String signature) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("signature", signature);

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
	 * 最后一次登入时间
	 */
	public long getTimeLogin() {
		return record.get("timeLogin");
	}

	public void setTimeLogin(final long timeLogin) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("timeLogin", timeLogin);

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
	 * 最后一次登出时间
	 */
	public long getTimeLogout() {
		return record.get("timeLogout");
	}

	public void setTimeLogout(final long timeLogout) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("timeLogout", timeLogout);

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