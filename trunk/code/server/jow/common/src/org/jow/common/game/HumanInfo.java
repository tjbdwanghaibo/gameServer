package org.jow.common.game;

import java.io.IOException;

import org.jow.common.entity.game.HumanDB;
import org.jow.core.InputStream;
import org.jow.core.OutputStream;
import org.jow.core.interfaces.ISerilizable;
import org.jow.core.support.Param;

public class HumanInfo implements ISerilizable {

	/** 玩家Id */
	private long id;
	/** 玩家姓名 */
	private String name;
	/** 玩家等级 */
	private int level;
	/** 头像 */
	private String head;
	/** 性别 */
	private int sex;
	/** 是否在线 */
	private boolean online;
	/** 签名 */
	private String signature;
	
	
	public HumanInfo() {

	}
	
	public HumanInfo(HumanDB humanDB) {
		this.id = humanDB.getId();
		this.name = humanDB.getName();
		this.level = humanDB.getLevel();
		this.head = humanDB.getHead();
		this.sex = humanDB.getSex();
		this.online = true;
		this.signature = humanDB.getSignature();
	}
	
	
	@Override
	public void writeTo(OutputStream out) throws IOException {
		out.write(id);
		out.write(name);
		out.write(level);
		out.write(head);
		out.write(sex);
		out.write(online);
		out.write(signature);
	}
	
	
	@Override
	public void readFrom(InputStream in) throws IOException {
		id = in.read();
		name = in.read();
		level = in.read();
		head = in.read();
		sex = in.read();
		online = in.read();
		signature = in.read();
	}

	public void update(Param param) {
		String name = param.get(HumanDB.K.name);
		if(name != null){
			this.setName(name);
		}
		Integer level = param.get(HumanDB.K.level);
		if (level != null) {
			setLevel(level);
		}
		
		String head = param.get(HumanDB.K.head);
		if (head != null) {
			setHead(head);
		}
		
		Integer sex = param.get(HumanDB.K.sex);
		if (sex != null) {
			setSex(sex);
		}
		
		String signature = param.get(HumanDB.K.signature);
		if (signature != null) {
			setSignature(signature);
		}
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	
}
