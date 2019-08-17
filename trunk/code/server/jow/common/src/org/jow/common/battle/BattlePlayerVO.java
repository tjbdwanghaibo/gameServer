package org.jow.common.battle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jow.core.CallPoint;
import org.jow.core.InputStream;
import org.jow.core.OutputStream;
import org.jow.core.interfaces.ISerializable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 玩家战斗信息
 * 
 * @author Yangxianmeng
 *
 */
public class BattlePlayerVO implements ISerializable {

	/** 玩家Id */
	private long id;
	/** 玩家名称 */
	private String name;
	/** 装备技能 */
	private int skill;
	/** 携带的宠物 */
	private int pet;
	/** 皮肤 */
	private int skin;
	/** 道具及等级 */
	private Map<Integer, Integer> items = new HashMap<>();
	
	/** 连接点 */
	private CallPoint connPoint;
	/** 游戏点 */
	private CallPoint gamePoint;
	
	/** 角色 */
	private int character;
	/** 武器 */
	private int arms;
	
	public BattlePlayerVO() {
		
	}
	
	public BattlePlayerVO(BattlePlayerVO other) {
		copy(other);
	}
	
	public void copy(BattlePlayerVO o) {
		this.id = o.id;
		this.name = o.name;
		this.skill = o.skill;
		this.pet = o.pet;
		this.skin = o.skin;
		this.items.clear();
		this.items.putAll(o.items);
		
		this.connPoint = o.connPoint;
		this.gamePoint = o.gamePoint;
		
		this.character = o.character;
		this.arms = o.arms;
	}

	public String buildDBattlePlayer() {
		
		JSONObject jo = new JSONObject();
		
		JSONArray ja = new JSONArray();
		for(Entry<Integer, Integer> entry : items.entrySet()) {
			JSONObject itemJo = new JSONObject();
			itemJo.put("k", entry.getKey());
			itemJo.put("v", entry.getValue());
			ja.add(itemJo);
		}
		jo.put("items", ja);
		
		if(skill > 0) {
			jo.put("skill", skill);
		}
		
		if(pet > 0) {
			jo.put("pet", pet);
		}
		if(skin > 0) {
			jo.put("skin", skin);
		}
		if(character > 0) {
			jo.put("character", character);
		}
		if(arms > 0) {
			jo.put("arms", arms);
		}
		
		return jo.toJSONString();
	}
	
	
	@Override
	public void readFrom(InputStream in) throws IOException {
		id = in.read();
		name = in.read();
		skill = in.read();
		pet = in.read();
		skin = in.read();
		items = in.read();
		
		connPoint = in.read();
		gamePoint = in.read();
		
		character = in.read();
		arms = in.read();
	}

	@Override
	public void writeTo(OutputStream out) throws IOException {
		out.write(id);
		out.write(name);
		out.write(skill);
		out.write(pet);
		out.write(skin);
		out.write(items);
		
		out.write(connPoint);
		out.write(gamePoint);
		
		out.write(character);
		out.write(arms);
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

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getPet() {
		return pet;
	}

	public void setPet(int pet) {
		this.pet = pet;
	}

	public int getSkin() {
		return skin;
	}

	public void setSkin(int skin) {
		this.skin = skin;
	}

	public Map<Integer, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Integer, Integer> items) {
		this.items = items;
	}

	public CallPoint getConnPoint() {
		return connPoint;
	}

	public void setConnPoint(CallPoint connPoint) {
		this.connPoint = connPoint;
	}

	public CallPoint getGamePoint() {
		return gamePoint;
	}

	public void setGamePoint(CallPoint gamePoint) {
		this.gamePoint = gamePoint;
	}

	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}

	public int getArms() {
		return arms;
	}

	public void setArms(int arms) {
		this.arms = arms;
	}
}
