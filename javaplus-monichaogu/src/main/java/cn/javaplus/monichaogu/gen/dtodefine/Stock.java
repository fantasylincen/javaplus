package cn.javaplus.monichaogu.gen.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;
import cn.javaplus.monichaogu.gen.dto.MongoGen.MongoMap;

@Dao
interface Stock {

	@Key
	String getId();

	/**
	 * 正常
	 */
	MongoMap<Price> getPricesNormal();
	
	/**
	 * 复权
	 */
	MongoMap<Price> getPricesFuQuan();
	
	/**
	 * 除权日
	 */
	List<ChuQuanDate> getChuQuanDates();
}
