package cn.javaplus.stock.gen.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface MoniPlayer {

	@Key
	String getId();

	List<MyStock> getStocks();
	
	double getRmb();
	
	List<String> getTradeLogs();
}
