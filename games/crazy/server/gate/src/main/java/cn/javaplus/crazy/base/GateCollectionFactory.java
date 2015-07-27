package cn.javaplus.crazy.base;

import cn.javaplus.crazy.config.Node;
import cn.javaplus.crazy.config.ServerJson;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class GateCollectionFactory {

	public static final DBCollection getCollection(String name) {
		Node node = ServerJson.getRoot();
		node = node.get("gate").get("mongoDb");
		String mongoDBName = node.getString("name");
		Mongo m = GateMongoFactory.getMongo();
		DB db = m.getDB(mongoDBName);
		return db.getCollection(name);
	}

}
