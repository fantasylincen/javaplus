package cn.javaplus.mxzrobot.db.define;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
public interface KeyValue {

	@Key
	String getKey();
	String getValue();
}
