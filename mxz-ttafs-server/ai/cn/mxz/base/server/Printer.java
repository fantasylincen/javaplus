package cn.mxz.base.server;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.util.debuger.Debuger;

class Printer {

	@SuppressWarnings("deprecation")
	static void printServerStart(String serverName) {

		StartLog log = new StartLog();
		log.load("res/startlog.txt");
		log.replace(0, serverName);
		log.replace(1, ServerConfig.getConfig().getPort());
		log.replace(2, ServerConfig.getConfig().getGameManagerPort());
		log.replace(3, Debuger.isDevelop());
		log.replace(4, Debuger.isDebug());
		log.replace(5, getMemory());
		log.replace(6, ServerConfig.getServerId());
		log.replace(7, ServerConfig.getConfig().getName());
		log.print();
	}

	private static String getMemory() {
		Runtime r = Runtime.getRuntime();
		long fr = r.freeMemory();
		long tt = r.totalMemory();
		float fr1 = fr / 1000000;
		float tt1 = tt / 1000000;

		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

		return df.format(fr1) + "M/" + df.format(tt1) + "M";
	}
}
