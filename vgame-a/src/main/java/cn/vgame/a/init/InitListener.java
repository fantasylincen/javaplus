package cn.vgame.a.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.javaplus.log.Log;

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