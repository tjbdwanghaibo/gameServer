package org.jow.loginsrv;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.jow.common.constant.ErrorCode;
import org.jow.common.db.DB;
import org.jow.common.entity.login.AccountDB;
import org.jow.common.idAllot.IdUtils;
import org.jow.common.msg.MsgAccount.SCLoginResult;
import org.jow.common.msg.MsgCommon.SCHumanKick;
import org.jow.common.msg.MsgIds;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.CallPoint;
import org.jow.core.Chunk;
import org.jow.core.Parms;
import org.jow.core.Port;
import org.jow.core.Record;
import org.jow.core.config.GameConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.config.JowDistr.Game;
import org.jow.gamesrv.RPCProxy.GameManagerServiceProxy;
import org.jow.gamesrv.RPCProxy.GameServiceProxy;
import org.jow.loginsrv.support.Log;

public class AccountManager {

	/** game Port 分配值 */
	public static final AtomicInteger portIdAuto = new AtomicInteger();

	/**
	 * 分配一个GamePort，负载均衡
	 * 
	 * @param gameIndex
	 * @return
	 */
	private static final String allotGamePort(int gameIndex) {
		Game game = JowDistr.gameConfigs.get(gameIndex);
		return GameConfig.PORT_GAME_PERFIX + portIdAuto.incrementAndGet() % game.portStartupNumGame;
	}

	/**
	 * 发送登陆结果
	 * 
	 * @param callPoint
	 * @param resultCode
	 */
	public static void sendLoginResult(CallPoint connPoint, int resultCode) {

		SCLoginResult.Builder msgReply = SCLoginResult.newBuilder();
		msgReply.setResultCode(resultCode);

		ConnectionProxy prx = ConnectionProxy.newInstance(connPoint);
		prx.sendMsg(MsgIds.SCLoginResult, new Chunk(msgReply));
	}

	/**
	 * 开始登陆
	 * 
	 * @param accountObj
	 * @param serv
	 */
	public static void login(AccountObject accountObj, AccountService serv) {
		// 通过账号密码登陆
		if (!StringUtils.isEmpty(accountObj.getAccount()) && !StringUtils.isEmpty(accountObj.getPassword())) {
			DB db = DB.newInstance(AccountDB.tableName);
			db.getBy(false, AccountDB.K.account, accountObj.getAccount());
			db.listenResult(AccountManager::_result_loadAccount, "accountObj", accountObj, "serv", serv);
		
		//通过设备号登陆
		}else if(!StringUtils.isEmpty(accountObj.getDeviceId())) {
			DB db = DB.newInstance(AccountDB.tableName);
			db.getBy(false, AccountDB.K.deviceId, accountObj.getDeviceId());
			db.listenResult(AccountManager::_result_loadAccount, "accountObj", accountObj, "serv", serv);
		//账号或则密码为空，登陆失败
		}else {
			serv.removeAccountObj(accountObj.getConnId());
			sendLoginResult(accountObj.getConnPoint(), /*ErrorCode.LOGIN_ACCOUNT_PASSWORD_NULL*/5);//密码是空
		}

	}
	private static void _result_loadAccount(Parms results, Parms context) {
		AccountObject accountObj = context.get("accountObj");
		AccountService serv = context.get("serv");
		Record record = results.get();
		AccountDB accountDB;
		if (record == null) {// 账号不存在，创建一个
			int gameIndex = serv.applyGameIndex();
			if (gameIndex == -1) {// 未开服，或者所有的逻辑服都挂了
				serv.removeAccountObj(accountObj.getConnId());
				sendLoginResult(accountObj.getConnPoint(), 3);// 未开服
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

		// 验证密码
		if (!StringUtils.isEmpty(accountObj.getAccount()) && !StringUtils.isEmpty(accountObj.getPassword())) {
			// 密码错误
			if (!accountObj.getPassword().equals(accountDB.getPassword())) {
				serv.removeAccountObj(accountObj.getConnId());
				sendLoginResult(accountObj.getConnPoint(), ErrorCode.LOGIN_PASSWORD_ERROR);// 密码错误登陆失败
				return;
			}
		}
		
		String gameNodeId = GameConfig.getGameNodeId(accountDB.getGameIndex());
		GameManagerServiceProxy prx = GameManagerServiceProxy.newInstance(gameNodeId, JowDistr.PORT_DEFAULT, GameConfig.SERV_GAME_MANAGER);
		prx.isLogin(accountDB.getId());
		Log.login.info("登陆中，检查gameManager，玩家是否上线了！ account = " + accountDB.getAccount());
		prx.listenResult(AccountManager::_result_isLogin, "accountObj", accountObj, "serv", serv, "accountDB", accountDB);
	}
	
	
	private static void _result_isLogin(Parms results, Parms context) {
		
		AccountObject accObject = context.get("accountObj");
		AccountService serv = context.get("serv");
		AccountDB accountDB = context.get("accountDB");
		
		boolean isLogin = results.get("isLogin");
		Log.login.info("登陆检查返回   isLogin = {}" , isLogin);
		if(isLogin) {
			
			CallPoint oldConnPoint = results.get("connPoint");
			ConnectionProxy prx = ConnectionProxy.newInstance(oldConnPoint);
			
			//发送剔除协议
			SCHumanKick.Builder msgKick = SCHumanKick.newBuilder();
			msgKick.setResultCode(ErrorCode.LOGIN_BE_KICKED);
			prx.sendMsg(MsgIds.SCHumanKick, new Chunk(msgKick));
			
			//断开连接
			prx.closeImmediate();
			
			serv.removeAccountObj(accObject.getConnId());
			sendLoginResult(accObject.getConnPoint(), ErrorCode.LOGIN_ALREADY_IN_OTHER_PLACE);
			return;
		}
		
		//开始角色登陆
		String gameNodeId = GameConfig.getGameNodeId(accountDB.getGameIndex());
		String gamePortId = allotGamePort(accountDB.getGameIndex());
		GameServiceProxy prx = GameServiceProxy.newInstance(gameNodeId, gamePortId, GameConfig.SERV_GAME);
		Log.login.info("开始角色登陆， account = " + accountDB.getAccount());
		prx.login(accObject.getConnPoint(), accountDB.getId(), accountDB.getAccount());
		prx.listenResult(AccountManager::_result_loginGame, "accountObj", accObject, "serv", serv);
	}
	
	private static void _result_loginGame(Parms results, Parms context) {
		AccountObject accountObj = context.get("accountObj");
		AccountService serv = context.get("serv");

		
		
		int result = results.get();
		Log.login.info("角色登陆返回(0-成功，10005-重复登陆)， result = {}" , result);
		
		serv.removeAccountObj(accountObj.getConnId());
		sendLoginResult(accountObj.getConnPoint(), result);
	}
	
	
	
	
	
	
	
	
	

}
