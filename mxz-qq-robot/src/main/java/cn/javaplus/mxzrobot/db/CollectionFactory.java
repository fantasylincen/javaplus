package cn.javaplus.mxzrobot.db;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class CollectionFactory {

	public static final DBCollection getCollection(String name) {
		Mongo m = MongoFactory.getMongo();
		DB db = m.getDB("qq-robot");
		return db.getCollection(name);
	}

}
