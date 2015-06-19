package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.DtoInterface;
import cn.javaplus.db.mongo.Key;

@Dao
interface ZfbOrder {

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
	 * 被充值角色ID
	 */
	String getRoleId();
	
	/**
	 * 奖券
	 * @return
	 */
	long getJiangQuan();
}
