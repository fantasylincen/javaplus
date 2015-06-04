package com.cnbizmedia.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Index;
import cn.javaplus.db.mongo.Key;

@Dao
interface User {

	@Key
	String getId();

	@Index
	String getEmail();
	
	@Index
	String getQQOpenId();

	String getPwdMD5();
	
	String getNick();
	
	boolean isMan();
	
	int getAge();
	
	/**
	 * v币
	 */
	int getVb();
}
