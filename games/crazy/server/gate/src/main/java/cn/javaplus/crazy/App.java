package cn.javaplus.crazy;

import cn.javaplus.crazy.base.GateMongoCollectionFetcher;
import cn.javaplus.crazy.handlers.Gate;
import cn.javaplus.crazy.ipmonitor.GateIpMonitor;
import cn.javaplus.crazy.mongo.Daos;
import cn.javaplus.util.Killer;
import cn.javaplus.util.MainArgs;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String[] args) {
		MainArgs.set(args);

		if (MainArgs.contains("stop")) {
			new Killer().kill("server");
			return;
		}

		GateMongoCollectionFetcher fetcher = new GateMongoCollectionFetcher();
		Daos.setCollectionFetcher(fetcher);
		new Gate().start();
		new GateIpMonitor().start();
	}

}
