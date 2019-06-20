package org.jow.loginsrv;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.jow.common.db.DB;
import org.jow.common.entity.login.AccountDB;
import org.jow.common.idAllot.IdUtils;
import org.jow.common.msg.MsgCommon.SCHumanKick;
import org.jow.common.msg.MsgIds;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.CallPoint;
import org.jow.core.Chunk;
import org.jow.core.Port;
import org.jow.core.Record;
import org.jow.core.config.GameConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.config.JowDistr.Game;
import org.jow.core.support.Param;
import org.jow.gamesrv.RPCProxy.GameManagerServiceProxy;
import org.jow.gamesrv.RPCProxy.GameServiceProxy;

/**
 * @author Yangxianmeng
 *
 * 账号登陆相关逻辑
 */
public class AccountManager {
	/**  game port分配值 */
	public static final AtomicInteger portIdAuto = new AtomicInteger();
	
	/**
	 * 分配一个gamePort，负载均衡
	 * @return
	 */
	private static final String allotGamePort(int gameIndex) {
		Game game = JowDistr.gameConfigs.get(gameIndex);
		return GameConfig.PORT_GAME_PERFIX + portIdAuto.incrementAndGet() % game.portStartupNumGame;
	}

	/**
	 * 发送登陆结果
	 * @param connPoint
	 * @param resultCode
	 */
	public static void sendLoginResult(CallPoint connPoint, int resultCode) {
//		SCLoginResult.Builder msgReply = SCLoginResult.newBuilder();
//		msgReply.setResultCode(resultCode);
//		
//		ConnectionProxy prx = ConnectionProxy.newInstance(connPoint);
//		prx.sendMsg(MsgIds.SCLoginResult, new Chunk(msgReply));
	}
	
	/**
	 * 开始登陆
	 * @param accountObj
	 */
	public static void login(AccountObject accountObj, AccountService serv) {
		// 通过账号密码登陆
		if (!StringUtils.isEmpty(accountObj.getAccount()) && !StringUtils.isEmpty(accountObj.getPassword())) {
			DB db = DB.newInstance(AccountDB.tableName);
			db.getBy(false, AccountDB.K.account, accountObj.getAccount());
			db.listenResult(AccountManager::_result_loadAccount, "accountObj", accountObj, "serv", serv);
		// 通过设备号登陆
		} else if (!StringUtils.isEmpty(accountObj.getDeviceId())) {
			DB db = DB.newInstance(AccountDB.tableName);
			db.getBy(false, AccountDB.K.deviceId, accountObj.getDeviceId());
			db.listenResult(AccountManager::_result_loadAccount, "accountObj", accountObj, "serv", serv);
		// 账号或密码为空，登陆失败
		} else {
			serv.removeAccountObj(accountObj.getConnId());
			sendLoginResult(accountObj.getConnPoint(), 1/*ErrorCode.LOGIN_ACCOUNT_PASSWORD_NULL*/);
		}
	}
	
	private static void _result_loadAccount(Param results, Param context) {
		AccountObject accountObj = context.get("accountObj");
		AccountService serv = context.get("serv");
		
		Record record = results.get();
		AccountDB accountDB;
		// 账号不存在，创建一个
		if (record == null) {
			int gameIndex = serv.applyGameIndex();
			// 未开服，或者所有逻辑服都挂了
			if (gameIndex == -1) {
				serv.removeAccountObj(accountObj.getConnId());
				sendLoginResult(accountObj.getConnPoint(), 2/*ErrorCode.LOGIN_SERVER_UNOPEN*/);
				return;
			}
			
			accountDB = new AccountDB();
			long id = IdUtils.toGameId(Port.applyId(), gameIndex);
			accountDB.setId(id);
			accountDB.setAccount(accountObj.getAccount());
			accountDB.setPassword(accountObj.getPassword());
			accountDB.setDeviceId(accountObj.getDeviceId());
			accountDB.setChannelId(accountObj.getChannelId());
			accountDB.setGameIndex(gameIndex);
			accountDB.persist();
		} else {
			accountDB = new AccountDB(record);
		}
		
		// 通过账号和密码登陆的，验证密码
		if (!StringUtils.isEmpty(accountObj.getAccount()) && !StringUtils.isEmpty(accountObj.getPassword())) {
			// 密码错误
			if (!accountObj.getPassword().equals(accountDB.getPassword())) {
				serv.removeAccountObj(accountObj.getConnId());
				sendLoginResult(accountObj.getConnPoint(), 3/*ErrorCode.LOGIN_PASSWORD_ERROR*/);
				return;
			}
		}
		
		String gameNodeId = GameConfig.getGameNodeId(accountDB.getGameIndex());
		GameManagerServiceProxy prx = GameManagerServiceProxy.newInstance(gameNodeId, JowDistr.PORT_DEFAULT, GameConfig.SERV_GAME_MANAGER);
		prx.isLogin(accountDB.getId());
		prx.listenResult(AccountManager::_result_isLogin, "accountObj", accountObj, "serv", serv, "accountDB", accountDB);
	}
	
	private static void _result_isLogin(Param results, Param context) {
		AccountObject accountObj = context.get("accountObj");
		AccountService serv = context.get("serv");
		AccountDB accountDB = context.get("accountDB");
		
		boolean isLogin = results.get("isLogin");
		if (isLogin) {
			CallPoint oldConnPoint = results.get("connPoint");
			ConnectionProxy prx = ConnectionProxy.newInstance(oldConnPoint);
			// 发送剔除协议
			SCHumanKick.Builder msgKick = SCHumanKick.newBuilder();
			msgKick.setResultCode(0/*ErrorCode.LOGIN_BE_KICKED*/);
			prx.sendMsg(MsgIds.SCHumanKick, new Chunk(msgKick));
			// 断开连接
			prx.closeImmediate();
			
			serv.removeAccountObj(accountObj.getConnId());
			sendLoginResult(accountObj.getConnPoint(), 0/*ErrorCode.LOGIN_ALREADY_IN_OTHER_PLACE*/);
			return;
		}
		
		// 开始角色登陆
		String gameNodeId = GameConfig.getGameNodeId(accountDB.getGameIndex());
		String gamePortId = allotGamePort(accountDB.getGameIndex());
		GameServiceProxy prx = GameServiceProxy.newInstance(gameNodeId, gamePortId, GameConfig.SERV_GAME);
		prx.login(accountObj.getConnPoint(), accountDB.getId(), accountDB.getAccount());
		prx.listenResult(AccountManager::_result_loginGame, "accountObj", accountObj, "serv", serv);
	}
	
	private static void _result_loginGame(Param results, Param context) {
		AccountObject accountObj = context.get("accountObj");
		AccountService serv = context.get("serv");
		
		int result = results.get();
		serv.removeAccountObj(accountObj.getConnId());
		sendLoginResult(accountObj.getConnPoint(), result);
	}
	
}
