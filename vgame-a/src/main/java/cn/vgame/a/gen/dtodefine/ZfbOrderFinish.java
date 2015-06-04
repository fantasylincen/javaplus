package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.DtoInterface;
import cn.javaplus.db.mongo.Key;

@Dao
interface ZfbOrderFinish {

	/**
	 * 订单ID
	 */
	@Key
	String getId();

	/**
	 * 充值游戏币数量
	 */
	int getCount();
	
	/**
	 * 支付金额
	 */
	String getPrice();

	/**
	 * 订单提交时间
	 */
	long getTime();

	/**
	 * 被充值用户ID
	 */
	String getUserId();
}
