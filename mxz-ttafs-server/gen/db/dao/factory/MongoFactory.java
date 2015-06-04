package db.dao.factory;

import java.net.UnknownHostException;
import java.util.List;

import cn.javaplus.util.StringResolver;
import cn.javaplus.util.Util;
import cn.javaplus.util.Util.Exception;
import cn.mxz.base.config.ServerConfig;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MongoFactory {

	private static Mongo	mongo;

	public static Mongo getMongo() {
		if(mongo == null) {
			try {
				String path = ServerConfig.getConfig().getMongoDbPath();
				StringResolver r = Util.Str.resolve(path);
				List<StringResolver> ss = r.split(":");
				
				mongo = new MongoClient(ss.get(0).getString(), ss.get(1).getInt());
			} catch (UnknownHostException e) {
				throw Exception.toRuntimeException(e);
			}
		}
		return mongo;
	}

}
