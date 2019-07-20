package org.jow.common.support;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jow.common.msg.MsgIds;
import org.jow.core.Parms;
import org.jow.core.support.SysException;
import org.jow.core.support.Utils;
import org.jow.core.support.log.LogCore;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.GeneratedMessage;

/**
 * 消息发送函数
 * @author Yangxianmeng
 *
 */
public abstract class MsgHandler {
	/** 过滤的协议列表 */
	public static Map<Integer, String> filterProtos = new ConcurrentHashMap<>();
	
	public abstract void fire(GeneratedMessage msg, Parms param);
	
	/**
	 * 解析消息
	 * @param type
	 * @param s
	 * @return
	 * @throws IOException 
	 */
	public GeneratedMessage parseFrom(int type, CodedInputStream s) throws IOException {
		return MsgIds.parseFrom(type, s);
	}
	
	
	public boolean handle(byte[] buffer, Object...params) {
		//消息长度
		@SuppressWarnings("unused")
		int len = Utils.bytesToBigEndian32(buffer, 0);
		//消息Id
		int msgId = Utils.bytesToBigEndian32(buffer, 4);
		if(filterProtos.containsKey(msgId)) {
			return false;
		}
		// 记录日志
		if (msgId != 1211 && msgId != 1301) {
			LogCore.msg.debug("接收到客户端消息：id={}", msgId);
		}
		//取出消息体
		CodedInputStream in = CodedInputStream.newInstance(buffer, 8, buffer.length - 8);
		try {
			GeneratedMessage msg = parseFrom(msgId, in);
			if(msg == null) {
				LogCore.msg.info("proto not exist,id={}", msgId);
				return true;
			}
			
			//发送接受到的消息事件
			Parms param = new Parms(params);
			//把消息Id加入参数中 不用再次获取
			param.put("msgId", msgId);
			fire(msg, param);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException(e);
		}
	}
}
