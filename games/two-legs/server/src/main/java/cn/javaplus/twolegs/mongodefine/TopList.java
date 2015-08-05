package cn.javaplus.twolegs.mongodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 前N名排行
 */
@Dao
interface TopList {

	@Key
	String getId();
	String getNick();

	String getScore();
}
