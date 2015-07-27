package cn.javaplus.twolegs.base;

import java.net.UnknownHostException;

import cn.javaplus.twolegs.json.Node;
import cn.javaplus.twolegs.json.ServerJson;
import cn.javaplus.util.Util;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MongoFactory {

	private static Mongo mongo;

	public static Mongo getMongo() {
		if (mongo == null) {
			try {

				Node node = ServerJson.getRoot();
				node = node.get("server");
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
