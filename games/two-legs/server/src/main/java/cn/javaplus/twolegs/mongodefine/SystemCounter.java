package cn.javaplus.twolegs.mongodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface SystemCounter {

	
	@Key
	String getKey();

	/**
	 * 值
	 * 
	 * @return
	 */
	int getCount();
}
