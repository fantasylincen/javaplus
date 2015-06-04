package db.dao.factory;

import cn.mxz.base.config.ServerConfig;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class CollectionFactory {

	public static final DBCollection getCollection(String name) {
		Mongo m = MongoFactory.getMongo();
		String path = ServerConfig.getConfig().getDataBasePath();

		// example: jdbc:mysql://localhost:3306/gamedb_559001

		String[] split = path.split("/");

		path = split[split.length - 1].trim();

		DB db = m.getDB(path);

		return db.getCollection(name);
	}

}
