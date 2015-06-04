package mongo.gen;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class CollectionFactory {

	public static final DBCollection getCollection(String name) {
		Mongo m = MongoFactory.getMongo();

		DB db = m.getDB("mongodb_59001");

		return db.getCollection(name);
	}

}
