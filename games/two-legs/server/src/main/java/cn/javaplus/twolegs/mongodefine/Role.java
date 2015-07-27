package cn.javaplus.twolegs.mongodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Role {

	@Key
	String getId();

	/**
	 * 密码MD5
	 * @return
	 */
	String getPasswordMd5();
	
	String getNick();
}
