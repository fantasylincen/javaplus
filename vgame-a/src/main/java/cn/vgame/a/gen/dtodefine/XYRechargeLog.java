package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 易捷充值日志
 */
@Dao
interface XYRechargeLog {

	@Key
	String getOrderid();

	String getUid();

	String getAmount();

	String getServerid();

	String getExtra();

	String getTs();

	String getSign();

	String getSig();

	String getRoleId();

	String getNick();

	long getCoin();

	long getJiangQuan();

}
