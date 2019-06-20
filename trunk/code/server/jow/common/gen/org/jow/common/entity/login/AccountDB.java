package org.jow.common.entity.login;

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
public final class AccountDB extends EntityBase {
	
	public static final String tableName = "login_account";

	/**
	 * 属性关键字
	 */
	public static final class K {
		/** id */
		public static final String id = "id";
		/** 账号名 */
		public static final String account = "account";
		/** 密码 */
		public static final String password = "password";
		/** 设备id */
		public static final String deviceId = "deviceId";
		/** 渠道id */
		public static final String channelId = "channelId";
		/** 所在逻辑服编号 */
		public static final String gameIndex = "gameIndex";
	}

	@Override
	public String getTableName() {
		return tableName;
	}
	
	public AccountDB() {
		super();
		setGameIndex(-1);
	}

	public AccountDB(Record record) {
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
			case K.account: {
				value = getAccount();
				break;
			}
			case K.password: {
				value = getPassword();
				break;
			}
			case K.deviceId: {
				value = getDeviceId();
				break;
			}
			case K.channelId: {
				value = getChannelId();
				break;
			}
			case K.gameIndex: {
				value = getGameIndex();
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
			case K.account: {
				setAccount((String) value);
				break;
			}
			case K.password: {
				setPassword((String) value);
				break;
			}
			case K.deviceId: {
				setDeviceId((String) value);
				break;
			}
			case K.channelId: {
				setChannelId((String) value);
				break;
			}
			case K.gameIndex: {
				setGameIndex((int) value);
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
	 * 账号名
	 */
	public String getAccount() {
		return record.get("account");
	}

	public void setAccount(final String account) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("account", account);

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
	 * 密码
	 */
	public String getPassword() {
		return record.get("password");
	}

	public void setPassword(final String password) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("password", password);

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
	 * 设备id
	 */
	public String getDeviceId() {
		return record.get("deviceId");
	}

	public void setDeviceId(final String deviceId) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("deviceId", deviceId);

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
	 * 渠道id
	 */
	public String getChannelId() {
		return record.get("channelId");
	}

	public void setChannelId(final String channelId) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("channelId", channelId);

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
	 * 所在逻辑服编号
	 */
	public int getGameIndex() {
		return record.get("gameIndex");
	}

	public void setGameIndex(final int gameIndex) {
		//更新前的数据状态
		int statusOld = record.getStatus();
		
		//更新属性
		record.set("gameIndex", gameIndex);

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