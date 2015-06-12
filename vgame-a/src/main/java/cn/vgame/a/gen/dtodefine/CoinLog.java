package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * GM操作日志
 */
@Dao
interface CoinLog {

	@Key
	String getId();

	String getTime();
	
	/**
	 * 送金币者 可能是玩家ID, 可能是系统.....
	 */
	String getFrom();

	/**
	 * 接受金币者ID
	 */
	String getTo();
	
	/**
	 * getFrom() + "|" + getTo()
	 * @return
	 */
	String getFromTo();

	/**
	 * 交易量
	 */
	long getCoin();
	
	/**
	 * 备注说明
	 */
	String getDsc();
}
