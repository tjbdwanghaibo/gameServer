package org.jow.loginsrv.main;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jow.common.DefaultPort;
import org.jow.common.db.InitFieldTable;
import org.jow.common.entity.DBReferenceTable;
import org.jow.common.msg.MsgSerializer;
import org.jow.core.Node;
import org.jow.core.Port;
import org.jow.core.PortTask;
import org.jow.core.ReferenceTable;
import org.jow.core.config.ConnConfig;
import org.jow.core.config.DBConfig;
import org.jow.core.config.JowConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.config.JowDistr.Conn;
import org.jow.core.config.LoginConfig;
import org.jow.core.support.observer.MsgSender;
import org.jow.dbsrv.main.DBStartup;
import org.jow.loginsrv.AccountPort;
import org.jow.loginsrv.AccountService;
import org.jow.loginsrv.CommonSerializer;
import org.jow.loginsrv.MsgReceiverInit;
import org.jow.loginsrv.support.Log;

public class LoginStartup {

	public static void main(String[] args) {
		
		if(args.length > 0 ) {
			JowConfig.RES_PATH = args[0];
		}else {
			//设置文件路径
			JowConfig.RES_PATH = "../data/";
		}
		
		String loginNodeId = LoginConfig.NODE_LOGIN;
		
		//设置日志文件名
		System.setProperty("logFileName", loginNodeId);
		
		//-------加载，策划配置，常量配置
		
		
		Log.login.info("=========初始化基本环境=========");
		MsgReceiverInit.init(MsgSender.instance);
		MsgSerializer.init();
		CommonSerializer.init();
		ReferenceTable.INSTANCE = new DBReferenceTable();
		
		
		
		//创建node
		Log.login.info("=========创建Node=========");
		JowDistr.Login login = JowDistr.loginConfig;
		String loginNodeAddr = login.nodeAddr;
		Node loginNode = new Node(loginNodeId, loginNodeAddr);
		
		//连接本区DB服务器
		Log.login.info("======连接到本区DB服务器=======");
		DBConfig.init(loginNodeId, login.dbConfig);
		DBStartup.startup(loginNode);
		
		//延迟一秒，再继续初始化
		//  ???
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Log.login.error(ExceptionUtils.getStackTrace(e));
		}
		
		//连接本地的服务器
		Log.login.info("=========连接本区连接服务器=======");
		for (int i = 0; i < JowDistr.connConfigs.size(); ++i) {
			Conn conn = JowDistr.connConfigs.get(i);
			String connNodeId = ConnConfig.getConnNodeId(i);
			String connNodeAddr = conn.nodeAddr;
			loginNode.addRemoteNode(connNodeId, connNodeAddr);
		}
		//只有一个Node,省略
		
		//创建临时的Port
		DefaultPort portDef = new DefaultPort(JowDistr.PORT_DEFAULT);
		portDef.startup(loginNode);
		
		Log.login.info("=========加载系统数据=========");		
		final InitFieldTable serInitData = new InitFieldTable();
		portDef.addTask(new PortTask() {

			@Override
			public void execute(Port port) {
				serInitData.init();
			}
			
		});
		
		//等待加载完成
		while(!serInitData.isCompleted()) {
			try {
				Thread.sleep(10);
				loginNode.pulse();
			} catch (InterruptedException e) {
				Log.login.error(ExceptionUtils.getStackTrace(e));
			}
		}

		//启动账号登陆服务
		AccountPort accountPort = new AccountPort(LoginConfig.PORT_ACCOUNT);
		accountPort.startup(loginNode);
		AccountService accountService = new AccountService(accountPort);
		accountService.startup();
		accountPort.addService(accountService);
		
		// Node正式启动
		Log.login.info("======Node正式启动======");
		loginNode.startup();
		
		// 启动日志信息
		Log.login.info("====================");
		Log.login.info(loginNodeId + " started.");
		Log.login.info("Listen:" + loginNodeAddr);
		Log.login.info("====================");
		
		//系统关闭时进行清理
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			public void run() {
				Log.login.info("======服务器关闭处理中========");
				
				try {
					// 通知立即刷新缓存服务器
					DBStartup.flushAll(loginNode);
					// 再等待2秒 持久化数据
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
