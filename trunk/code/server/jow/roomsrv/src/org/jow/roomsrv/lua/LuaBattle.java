package org.jow.roomsrv.lua;

import java.util.List;

import org.jow.common.msg.MsgBattle.DBattleCMD;

/***
 * 服务器跑lua验证战斗
 * 
 * @author Yangxianmeng
 *
 */
public class LuaBattle {
	
	static {
		System.loadLibrary("luabattle");
		jniload();
	}
	
	/**
	 * 创建luaState
	 * @param mainPath
	 * @return
	 */
	public static native long jniLuaNewState(String mainPath);

	/***
	 * jni初始化
	 * @return
	 */
	public static native void jniload();
	
	/**
	 * jni结束时清理
	 * @return
	 */
	public static native int jniUnload();
	
	/**
	 * 创建房间
	 * @param luaState
	 * @param sn
	 * @param mode
	 * @param seed
	 * @return
	 */
	public static native long jniCreateBattle(long luaState, int sn, int mode, int seed);
	
	/**
	 * 销毁房间
	 * @param luaState
	 * @param id
	 * @return
	 */
	public static native boolean jniDestoryBattle(long luaState, long id);
	
	/**
	 * 跑一帧
	 * @param luaState
	 * @param id
	 * @param cmds
	 * @return -1=出错,0=成功,1=战斗结束
	 */
	public static native int jniUpdateBattle(long luaState, long id, List<DBattleCMD> cmds);
	
	/**
	 * 同步指令
	 * @param luaState
	 * @param id
	 * @param cmds
	 * @return -1=出错,0=成功,1=战斗结束
	 */
	public static native int jniBattleDo(long luaState, long id, List<DBattleCMD> cmds);
	
	/**
	 * 结束后获取返回值
	 * @param luaState
	 * @param id
	 * @return
	 */
	public static native String jniGetResult(long luaState, long id);
	
}
