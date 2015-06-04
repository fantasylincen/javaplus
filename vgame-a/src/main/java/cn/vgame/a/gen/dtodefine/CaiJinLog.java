package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 彩金历史记录
 */
@Dao
interface CaiJinLog {

	@Key
	String getId();

	/**
	 * 获得彩金的玩家ID
	 */
	String getRoleId();

	/**
	 * 获得彩金的玩家昵称
	 */
	String getNick();

	/**
	 * 彩金数量
	 */
	long getCaiJin();
	
	/**
	 * 是否是获得的小彩金
	 * @return
	 */
	boolean getIsSmall();
}
