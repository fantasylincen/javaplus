package cn.mxz.server.base;

import java.net.UnknownHostException;

import cn.javaplus.util.Util;
import cn.mxz.server.json.Node;
import cn.mxz.server.json.ServerJson;

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
