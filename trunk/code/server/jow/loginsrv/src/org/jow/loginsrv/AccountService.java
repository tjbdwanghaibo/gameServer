package org.jow.loginsrv;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jow.common.constant.ErrorCode;
import org.jow.common.db.DB;
import org.jow.common.entity.login.AccountDB;
import org.jow.common.msg.MsgAccount.SCAccountLoginQueue;
import org.jow.common.msg.MsgIds;
import org.jow.connsrv.RPCProxy.ConnectionProxy;
import org.jow.core.CallPoint;
import org.jow.core.Chunk;
import org.jow.core.Parms;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.Service;
import org.jow.core.config.JowDistr;
import org.jow.core.config.LoginConfig;
import org.jow.core.gen.proxy.DistrClass;
import org.jow.core.gen.proxy.DistrMethod;
import org.jow.core.support.TickTimer;
import org.jow.core.support.TimeUtils;
import org.jow.loginsrv.support.AccountExtendMsgHandler;
import org.jow.loginsrv.support.Log;

/**
 * 登陆服务
 * @author Yangxianmeng
 *
 */
@DistrClass
public class AccountService extends Service{

	/** 消息处理类 */
	private static AccountExtendMsgHandler msgHandler = new AccountExtendMsgHandler(); 
	/** 储存选人阶段的信息 */
	private Map<Long, AccountObject> accountObjs = new HashMap<>();
	/** 各个逻辑服注册的人数 */
	private Map<Integer, GameInfo> gameInfos = new HashMap<>();
	
	/** 申请进入列表 */
	private List<Long> loginApply = new LinkedList<>();
	/** 本秒允许玩家登陆数量 */
	private int loginedNumPerSec = 0;
	/** 本次提示玩家间隔期允许登陆玩家数量（仅作显示记录，不参与任何逻辑）*/
	private int loginedNumPerTips = 0;
	
	/** 一帧需要登陆的最大人数 */
	private int LOGIN_MAX_QUEUE = 50;
	
	/** 每秒检查一次 是否有需要开始登陆加载的角色*/
	private TickTimer loginTimer = new TickTimer(1000);
	/** 每N秒给登陆队列中角色进行提示*/
	private TickTimer loginTipsTimer = new TickTimer(10 * TimeUtils.SEC);
	
	public AccountService(Port port) {
		super(port);
		for (int gameIndex = 0; gameIndex < JowDistr.gameConfigs.size(); ++gameIndex) {
			GameInfo gameInfo = new GameInfo();
			gameInfo.setIndex(gameIndex);
			gameInfos.put(gameIndex, gameInfo);
		}
	}

	@Override
	public void startup() {
		super.startup();
		
		port.addTask(new PortTask() {
			
			@Override
			public void execute(Port port) {
				Log.login.info("load balance info...");
				// 载入各逻辑服的注册人数
				for (int gameIndex = 0; gameIndex < JowDistr.gameConfigs.size(); ++gameIndex) {
					DB db = DB.newInstance(AccountDB.tableName);
					db.countBy(false, AccountDB.K.gameIndex, gameIndex);
					Parms result = db.waitForResult();
					int count = result.get();
					gameInfos.get(gameIndex).setRegisterCount(count);
				}
				Log.login.info("load balance finished!");
			}
		});
		
		
	}
	
	@Override
	public Object getId() {
		return LoginConfig.SERV_ACCOUNT;
	}

	@Override
	protected void pulseOverride() {
		super.pulseOverride();
		
		//当前时间
		long now = port.getTimeCurrent();
		
		//检查需要开始登录加载的角色
		if(loginTimer.isPeriod(now)) {
			loginedNumPerSec = 0;
			loginQueue();
		}
		
		//对登陆队列中的玩家进行提示
		if(loginTipsTimer.isPeriod(now)) {
			pulsCharLoginQueueTips();
		}
	}
	
	/**
	 * 对登陆中的玩家进行提示
	 */
	private void pulsCharLoginQueueTips() {
		int num = 0;
		Iterator<Long> iter = loginApply.iterator();
		while (iter.hasNext()) {
			long connId = iter.next();
			AccountObject accountObj = accountObjs.get(connId);
			if (accountObj == null) {
				iter.remove();
			} else {
				num++;
				plusCharLoginQueueTipsOne(accountObj, num);
			}
		}
		// 如果有排队 那么输出排队信息
		if (!loginApply.isEmpty()) {
			Log.login.info("当前排队中：申请登陆人数={}，正在登陆人数={}", loginApply.size(), loginedNumPerTips);
			loginedNumPerTips = 0;
		}
	}

	/**
	 * 对队列中的玩家进行提示
	 * @param accountObj
	 * @param num
	 */
	private void plusCharLoginQueueTipsOne(AccountObject accountObj, int num) {
		
		//剩余时间
		int sec = num/ LOGIN_MAX_QUEUE  + 1;
		
		//提示客户端
		SCAccountLoginQueue.Builder msgResult = SCAccountLoginQueue.newBuilder();
		msgResult.setNum(num);
		msgResult.setSec(sec);
		
		ConnectionProxy proxy = ConnectionProxy.newInstance(accountObj.getConnPoint());
		proxy.sendMsg(MsgIds.SCAccountLoginQueue, new Chunk(msgResult));
	}

	/**
	 * 检查需要开始登陆的加载角色
	 */
	private void loginQueue() {
		
		while(!loginApply.isEmpty() && loginedNumPerSec < LOGIN_MAX_QUEUE) {
			//维护登陆状态
			long connId = loginApply.remove(0);
			AccountObject accountObj = accountObjs.get(connId);
			if(accountObj == null) {
				continue;
			}
			
			//记录本次允许登陆的人数
			loginedNumPerSec ++;
			loginedNumPerTips ++ ;
			accountObj.setState(AccountObject.STATE_LOGING);
			AccountManager.login(accountObj, this);
		}
		
	}

	@DistrMethod
	public void msgHandler(long connId, byte[] msgbuf) {
		CallPoint connPoint = new CallPoint();
		connPoint.nodeId = port.getCallFromNodeId();
		connPoint.portId = port.getCallFromPortId();
		connPoint.serviceId = connId;
		
		msgHandler.handle(msgbuf, "connPoint", connPoint, "serv", this);
	}
	
	
	@DistrMethod
	public void gamePing(int gameIndex, int onlineCount) {
		GameInfo gameInfo = gameInfos.get(gameIndex);
		if(gameInfo != null) {
			gameInfo.setOnlineCount(onlineCount);
			gameInfo.setLastPingTime(port.getTimeCurrent());
		}
	}
	
	@DistrMethod
	public void connClosed(long connId) {
		accountObjs.remove(connId);
		
	}
	
	@DistrMethod
	public void connCheck(long connId) {
		port.returns(accountObjs.containsKey(connId));
	}
	
	
	
	/**
	 * 增加登陆申请
	 * @param accountObj2
	 */
	public void loginApplyAdd(AccountObject accountObj) {
		//重复登陆
		if(accountObjs.containsKey(accountObj.getConnId())) {
			AccountManager.sendLoginResult(accountObj.getConnPoint(),ErrorCode.LOGIN_REPEAT_LOGIN);// "正在登陆,请勿重复登陆!"
			return;
		}
		
		//服务器在线人数爆满
		if(isOnlineFull()) {
			AccountManager.sendLoginResult(accountObj.getConnPoint(), ErrorCode.LOGIN_SERVER_FULL);//"服务器已达到最大的在线人数"
			return;
		}
		//进入队列
		accountObjs.put(accountObj.getConnId(), accountObj);
		loginApply.add(accountObj.getConnId());
		
		//立刻触发一次队列检查
		loginQueue();
		
		//提示玩家等待
		if(accountObj.getState() == AccountObject.STATE_WAITINT) {
			plusCharLoginQueueTipsOne(accountObj, loginApply.size());
		}
	}

	private boolean isOnlineFull() {
		int humanCount = 0;
		for(GameInfo gameInfo : gameInfos.values()) {
			humanCount += gameInfo.getOnlineCount();
		}
		
		//同时在线最大人数
		
		return humanCount >= 1000;
	}
	
	/**
	 * 删除登陆中的账号
	 * @param connId
	 */
	public void removeAccountObj(long connId) {
		accountObjs.remove(connId);
	}
	
	/**
	 * 通过Id查找正在登陆中的账号对象
	 * @param connId
	 * @return
	 */
	public AccountObject getAccountObj(long connId) {
		return accountObjs.get(connId);
	}
	
	
	/**
	 * 查找注册人数最少的逻辑服
	 * @return
	 */
	public int applyGameIndex() {
		long now = port.getTimeCurrent();

		GameInfo game = gameInfos.values().stream().filter(gameInfo -> {
			return gameInfo.isActive(now);
		}).min((o1, o2) -> {
			return Integer.compare(o1.getRegisterCount(), o2.getRegisterCount());
		}).orElse(null);

		return game == null ? -1 : game.getIndex();
	}
	
	
}
