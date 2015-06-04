package com.cnbizmedia.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.DtoInterface;
import cn.javaplus.db.mongo.Key;

@Dao
@DtoInterface("com.cnbizmedia.recharge.IOrderDto")
interface Order {

	/**
	 * 订单ID
	 */
	@Key
	String getId();

	/**
	 * 对应到的游戏服务器ID
	 */
	String getServerId();

	/**
	 * 充值游戏币数量
	 */
	int getCount();

	/**
	 * 订单提交时间
	 */
	long getTime();

	/**
	 * 发起充值的用户id
	 */
	String getRechargeUserId();

	/**
	 * 被充值用户ID
	 */
	String getUserId();

	/**
	 * 是否发生了错误
	 */
	boolean getIsError();

	/**
	 * 最后订单一次处理时间
	 */
	long getLastProcessTime();

	/**
	 * 订单重新提交时间间隔 毫秒
	 */
	long getRetrySpace();
	
	/**
	 * 错误原因
	 */
	String getReason();
}
