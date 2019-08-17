package org.jow.matchsrv.main;

import org.jow.common.entity.DBReferenceTable;
import org.jow.common.msg.MsgSerializer;
import org.jow.core.Node;
import org.jow.core.ReferenceTable;
import org.jow.core.config.CentralConfig;
import org.jow.core.config.JowConfig;
import org.jow.core.config.JowDistr;
import org.jow.core.config.MatchConfig;
import org.jow.matchsrv.CommonSerializer;
import org.jow.matchsrv.MatchPort;
import org.jow.matchsrv.MatchService;
import org.jow.matchsrv.support.Log;

/**
 * 全局房间服务
 * 
 * @author Yangxianmeng
 *
 */
public class MatchStarup {

	public static void main(String[] args) {
		// 设置日志文件名
		System.setProperty("logFileName", MatchConfig.NODE_MATCH);
		// 配置文件路径
		JowConfig.RES_PATH = "../data/";
		if (args.length > 0) {
			JowConfig.RES_PATH = args[0];
		}
		Log.match.info("======初始化基本环境======");
		// TODO
		MsgSerializer.init();
		CommonSerializer.init();
		ReferenceTable.INSTANCE = new DBReferenceTable();

		// 创建node
		Log.match.info("======创建Node======");
		JowDistr.Match match = JowDistr.mathConfig;
		String matchNodeAddr = match.nodeAddr;
		Node matchNode = new Node(MatchConfig.NODE_MATCH, matchNodeAddr);

		// 连接到全局中央服务器
		Log.match.info("======连接到全局中央服务器======");
		matchNode.addRemoteNode(CentralConfig.NODE_CENTRAL, JowDistr.centralConfig.nodeAddr);

		// 启动匹配服务
		MatchPort matchPort = new MatchPort(MatchConfig.PORT_MATCH);
		matchPort.startup(matchNode);
		MatchService matchService = new MatchService(matchPort);
		matchService.startup();
		matchPort.addService(matchService);

		// Node正式启动
		Log.match.info("======Node正式启动======");
		matchNode.startup();

		// 启动日志信息
		Log.match.info("====================");
		Log.match.info(MatchConfig.NODE_MATCH + " started.");
		Log.match.info("Listen:" + matchNodeAddr);
		Log.match.info("====================");

		// 系统关闭时进行清理
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {

			}
		});
	}

}
