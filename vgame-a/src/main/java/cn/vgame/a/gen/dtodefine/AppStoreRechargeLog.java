package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * AppStore充值日志
 */
@Dao
interface AppStoreRechargeLog {

	@Key
	String getId();
	
	/**
	 * 商品id
	 */
	String getProductId();

	
	int getFee();
	String getRoleId();
	String getNick();
	long getCoin();
}
