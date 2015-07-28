package cn.javaplus.crazy.mongo;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
public interface Role {

	@Key
	String getUname();

	@Key
	int getId();

	/**
	 * 形象ID
	 * 
	 * @return
	 */
	int getPersonId();

	String getNick();
}
