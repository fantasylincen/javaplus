package cn.javaplus.crazy.mongo;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Role {

	@Key
	int getId();

	int getLevel();
}
