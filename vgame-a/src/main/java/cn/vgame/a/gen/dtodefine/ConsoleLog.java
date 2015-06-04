package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * GM操作日志
 */
@Dao
interface ConsoleLog {

	@Key
	String getId();

	/**
	 * 时间
	 */
	String getDate();

	/**
	 * 日志内容
	 */
	String getLog();
}
