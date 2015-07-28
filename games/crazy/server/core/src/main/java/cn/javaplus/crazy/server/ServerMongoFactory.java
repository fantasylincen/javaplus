package cn.javaplus.crazy.server;

import java.net.UnknownHostException;

import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.NodeArray;
import cn.javaplus.crazy.config.ServerJson;
import cn.javaplus.util.Util;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class ServerMongoFactory {

	private static Mongo mongo;

	public static Mongo getMongo() {
		if (mongo == null) {
			try {

				
				Node node = ServerJson.getRoot();
				NodeArray array = node.getArray("servers");
				node = array.find("id", 10001);
				
				node = node.get("mongoDb");
				
				String host = node.getString("name");
				int port = node.getInt("port");

				mongo = new MongoClient(host, port);
			} catch (UnknownHostException e) {
				throw Util.Exception.toRuntimeException(e);
			}
		}
		return mongo;
	}

}
