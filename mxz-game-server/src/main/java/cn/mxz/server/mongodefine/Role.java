package cn.mxz.server.mongodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Role {

	@Key
	String getId();

	/**
	 * 形象ID
	 * 
	 * @return
	 */
	int getPersonId();

	/**
	 * 密码MD5
	 * @return
	 */
	String getPasswordMd5();
	
	String getNick();
}
