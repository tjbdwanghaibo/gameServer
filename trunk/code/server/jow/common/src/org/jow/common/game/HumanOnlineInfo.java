package org.jow.common.game;

import java.io.IOException;

import org.jow.core.CallPoint;
import org.jow.core.InputStream;
import org.jow.core.OutputStream;
import org.jow.core.interfaces.ISerializable;

/**
 * @author gaopan
 *
 * 在线玩家的信息
 */
public class HumanOnlineInfo implements ISerializable {
	/** 玩家状态 */
	/** 空闲 */
	public static final int STATE_IDLE	= 0;
	/** 组队中 */
	public static final int STATE_TEAM	= 1;
	/** 房间中 */
	public static final int STATE_ROOM	= 2;
	/** 战斗中 */
	public static final int STATE_FIGHT	= 3;
	
	/** 玩家id */
	private long id;
	/** 连接点 */
	private CallPoint connPoint;
	/** 逻辑点 */
	private CallPoint gamePoint;
	/** 战斗点 */
	private CallPoint roomPoint;
	/** 玩家状态 */
	private int status = STATE_IDLE;
	
	public HumanOnlineInfo() {}
	
	@Override
	public void writeTo(OutputStream out) throws IOException {
		out.write(id);
		out.write(connPoint);
		out.write(gamePoint);
		out.write(roomPoint);
		out.write(status);
	}

	@Override
	public void readFrom(InputStream in) throws IOException {
		id = in.read();
		connPoint = in.read();
		gamePoint = in.read();
		roomPoint = in.read();
		status = in.read();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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

	public CallPoint getRoomPoint() {
		return roomPoint;
	}

	public void setRoomPoint(CallPoint roomPoint) {
		this.roomPoint = roomPoint;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
