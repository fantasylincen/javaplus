package cn.javaplus.twolegs.mongodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Score {

	@Key
	String getId();

	float getScore();
}
