package cn.javaplus.smonitor.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.javaplus.log.Log;
import cn.javaplus.smonitor.PrintToConsoleAndFile;

public class InitListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
		Log.setOut(new PrintToConsoleAndFile());
		Log.d("ThreadStockDataDownloader Start");
		ThreadStockDataDownloader.getInstance().start();
		ThreadFustBuy.getInstance().start();
	}
}