package cn.javaplus.mxzrobot.db;

import java.net.UnknownHostException;

import cn.javaplus.util.Util.Exception;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MongoFactory {

	private static Mongo	mongo;

	public static Mongo getMongo() {
		if(mongo == null) {
			try {
				mongo = new MongoClient();
			} catch (UnknownHostException e) {
				throw Exception.toRuntimeException(e);
			}
		}
		return mongo;
	}

}
