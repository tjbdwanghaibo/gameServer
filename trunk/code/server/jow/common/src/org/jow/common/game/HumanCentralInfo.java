package org.jow.common.game;

import java.io.IOException;

import org.jow.common.entity.central.HumanSimpleDB;
import org.jow.common.msg.MsgDef.DHumanSimpleInfo;
import org.jow.core.InputStream;
import org.jow.core.OutputStream;
import org.jow.core.interfaces.ISerilizable;

/**
 * 中央服上的玩家信息
 * @author Yangxianmeng
 *
 */
public class HumanCentralInfo implements ISerilizable {

	/** 玩家id */
	private long id;
	/** 玩家姓名 */
	private String name;
	/** 头像 */
	private String head;
	/** 性别 */
	private int sex;
	/** 是否在线*/
	private boolean online;
	
	/** 等级 */
	private int level;
	
	public HumanCentralInfo() {}
	
	public HumanCentralInfo(HumanSimpleDB human){
		this.id = human.getId();
		this.name = human.getName();
		this.head = human.getHead();
		this.sex = human.getSex();
	}
	@Override
	public void writeTo(OutputStream out) throws IOException {
		out.write(id);
		out.write(name);
		out.write(head);
		out.write(sex);
		out.write(online);
		out.write(level);
	}

	@Override
	public void readFrom(InputStream in) throws IOException {
		id = in.read();
		name = in.read();
		head = in.read();
		sex = in.read();
		online = in.read();
		level = in.read();
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
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * 生成网络协议
	 * 
	 * @return
	 */
	public DHumanSimpleInfo.Builder parseBuilder() {
		DHumanSimpleInfo.Builder dhumanInfo = DHumanSimpleInfo.newBuilder();
		dhumanInfo.setId(this.getId());
		dhumanInfo.setSex(this.getSex());
		dhumanInfo.setName(this.getName());
		dhumanInfo.setHead(this.getHead());
		dhumanInfo.setOnline(this.isOnline());
		dhumanInfo.setLevel(1);
		return dhumanInfo;
	}

}
