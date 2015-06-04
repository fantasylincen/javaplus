package cn.javaplus.stock.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface StockPrice {

	@Key
	String getId();
	
	/**
	 * yyyyMMddHHmm
	 */
	@Key
	String getTime();
	
	
	String getPrice();
	
	String getCloseYestoday();
	String getHighToday();
	String getLowToday();
}
