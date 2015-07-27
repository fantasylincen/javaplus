package cn.javaplus.crazy;

import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.NodeArray;
import cn.javaplus.crazy.config.ServerJson;
import cn.javaplus.crazy.events.Events;
import cn.javaplus.crazy.server.NettyServer;
import cn.javaplus.crazy.server.ServerStartEvent;
import cn.javaplus.util.Killer;
import cn.javaplus.util.MainArgs;
import cn.mxz.base.server.Server;

/**
 * Hello world!
 * 
 */
public class App {

	public static void main(String [] args) throws InterruptedException {
		
		MainArgs.set(args);
		
		if (MainArgs.contains("stop")) {
			new Killer().kill("gate");
			return;
		}
		
		startServer();
	}

	private static void startServer() {
		Node node = ServerJson.getRoot();
		NodeArray array = node.getArray("servers");
		Integer find = MainArgs.findInteger("id");
		node = array.find("id", find);
		int port = node.getInt("port");
		Server server = new NettyServer(port);
		server.start();
		Events.dispatch(new ServerStartEvent(server));
	}

}
