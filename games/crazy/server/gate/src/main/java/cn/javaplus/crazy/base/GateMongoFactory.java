package cn.javaplus.crazy.base;

import java.net.UnknownHostException;

import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.ServerJson;
import cn.javaplus.util.Util;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class GateMongoFactory {

	private static Mongo mongo;

	public static Mongo getMongo() {
		if (mongo == null) {
			try {

				Node node = ServerJson.getRoot();
				node = node.get("gate");
				node = node.get("mongoDb");
				
				String host = node.getString("host");
				int port = node.getInt("port");
				mongo = new MongoClient(host, port);
			} catch (UnknownHostException e) {
				throw Util.Exception.toRuntimeException(e);
			}
		}
		return mongo;
	}

}
