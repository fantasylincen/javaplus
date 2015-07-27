package cn.javaplus.crazy.server;

import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.NodeArray;
import cn.javaplus.crazy.config.ServerJson;
import cn.javaplus.util.MainArgs;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class ServerCollectionFactory {

	public static final DBCollection getCollection(String name) {
		Mongo m = ServerMongoFactory.getMongo();

		Node node = ServerJson.getRoot();
		NodeArray array = node.getArray("servers");
		node = array.find("id", MainArgs.findInteger("id"));
		
		node = node.get("mongoDb");
		String mongoDBName = node.getString(name);
		
		DB db = m.getDB(mongoDBName);
		return db.getCollection(name);
	}

}
