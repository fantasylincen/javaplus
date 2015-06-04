package cn.mxz.testbase;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.factory.IFactory;
import cn.mxz.util.ServiceFactory;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.sencitive.SencitiveConfig;

import com.lemon.commons.database.DataBasePool;


public class ServiceTestBase {

	protected IFactory factory;
	protected TestSocket	socket;

	@Before
	public void init() {

		DataBasePool.SIZE = 20;

		PropertyConfigurator.configure("res/log4j.properties");	//初始化日志配置
		ServerConfig.init("-serverId:559103");									//根据main输入参数,决定服务器ID,运行类型等
		Debuger.init();											//调试器初始化
//		InitDB.getInstance();									//初始化SP库

		SencitiveConfig.init();

		Debuger.debug("ServiceTestBase.init() 服务器区ID:" + ServerConfig.getServerId() + ":" + ServerConfig.getConfig().getName());

		socket = new TestSocket();
		factory = new ServiceFactory(socket);
	}
}
