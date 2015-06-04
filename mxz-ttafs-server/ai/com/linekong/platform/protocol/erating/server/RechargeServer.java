package com.linekong.platform.protocol.erating.server;

import java.io.IOException;
import java.net.UnknownHostException;

import mongo.gen.MongoGen.Daos;

import org.apache.log4j.PropertyConfigurator;
import org.xsocket.connection.IServer;
import org.xsocket.connection.Server;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.server.MongoCollectionFetcher;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.debuger.SystemLog;
import cn.mxz.util.sencitive.SencitiveConfig;

import com.lemon.commons.database.DataBasePool;

public class RechargeServer {

	public void run() throws UnknownHostException, IOException {
		int rechargePort = ServerConfig.getConfig().getRechargePort();
		IServer server = new Server("0.0.0.0", rechargePort, new RechargeHandler());
		server.start();
		SystemLog.debug( "充值服务器启动成功，端口:" + rechargePort);
//		server.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		DataBasePool.SIZE = 20;

		PropertyConfigurator.configure("res/log4j.properties"); // 初始化日志配置
		ServerConfig.init("-serverId:559102"); // 根据main输入参数,决定服务器ID,运行类型等
		Debuger.init(); // 调试器初始化
		// InitDB.getInstance(); //初始化SP库

		SencitiveConfig.init();

		Debuger.debug("ServiceTestBase.init() 服务器区ID:" + ServerConfig.getServerId());
		Daos.setCollectionFetcher(new MongoCollectionFetcher());

		new RechargeServer().run();

	}
}
