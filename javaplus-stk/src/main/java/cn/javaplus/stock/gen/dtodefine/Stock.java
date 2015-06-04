package cn.javaplus.stock.gen.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;
import cn.javaplus.stock.gen.dto.MongoGen.MongoMap;

@Dao
interface Stock {

	@Key
	String getId();

	/**
	 * 复权后
	 */
	MongoMap<Day> getDays();
	
	/**
	 * 除权日
	 */
	List<ChuQuanDate> getChuQuanDates();
}
