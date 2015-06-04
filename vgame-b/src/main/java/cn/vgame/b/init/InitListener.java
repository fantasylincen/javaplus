package cn.vgame.b.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.javaplus.log.Log;
import cn.javaplus.log.Out;
import cn.vgame.b.log.MongoDbLogOutput;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		Log.d("contextDestroyed");
		System.exit(0);
	}

	public void contextInitialized(ServletContextEvent sce) {
		Log.d("contextInitialized");
		new InitThread().start();
	}
}