package org.hhhhhh.house;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hhhhhh.house.spider.Spider;

import cn.javaplus.log.Log;

public class InitListener implements ServletContextListener {


	public void contextDestroyed(ServletContextEvent sce) {
		Log.d("contextDestroyed");
		System.exit(0);
	}

	public void contextInitialized(ServletContextEvent sce) {
		Log.d("contextInitialized");
		Spider s = Spider.getInstance();
		s.ensureStart();
	}


}