package cn.javaplus.mxzrobot.db.define;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 能力
 */
@Dao
public interface Ability {
	
	@Key
	String getKey();
	
	String getValue();
}
