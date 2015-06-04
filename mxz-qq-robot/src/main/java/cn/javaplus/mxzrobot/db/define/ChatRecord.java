package cn.javaplus.mxzrobot.db.define;

import cn.javaplus.db.mongo.Dao;

/**
 * 聊天记录
 */
@Dao
public interface ChatRecord {
	
	String getTime();
	
	String getTalker();
	
	String getContent();
}
