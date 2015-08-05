package cn.javaplus.twolegs.mongodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Nick {

	
	@Key
	int getId();

	String getNick();
}
