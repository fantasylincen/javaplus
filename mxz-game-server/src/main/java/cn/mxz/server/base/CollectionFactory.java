package cn.mxz.server.base;

import cn.mxz.server.json.Node;
import cn.mxz.server.json.ServerJson;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class CollectionFactory {

	public static final DBCollection getCollection(String name) {
		Node node = ServerJson.getRoot();
		node = node.get("server").get("mongoDb");
		String mongoDBName = node.getString("name");
		Mongo m = MongoFactory.getMongo();
		DB db = m.getDB(mongoDBName);
		return db.getCollection(name);
	}

}
