package cn.vgame.b.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 易捷充值日志
 */
@Dao
interface YiJieRechargeLog {

	@Key
	String getSsid();
	
	String getApp();
	String getCbi();
	long getCt();
	int getFee();
	long getPt();
	String getSdk();
	String getSign();
	int getSt();
	String getTcd();
	String getUid();
	String getVer();
	
	String getRoleId();
	String getNick();
	long getCoin();
}
