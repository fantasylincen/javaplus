package cn.javaplus.mxzrobot.db.define;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 属性
 */
@Dao
public interface Attribute {
	
	@Key
	String getKey();
	
	String getValue();
}
