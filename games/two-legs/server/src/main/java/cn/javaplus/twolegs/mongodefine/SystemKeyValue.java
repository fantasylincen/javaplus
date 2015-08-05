package cn.javaplus.twolegs.mongodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface SystemKeyValue {

	
	@Key
	String getKey();

	/**
	 * 值
	 * 
	 * @return
	 */
	String getValue();
}
