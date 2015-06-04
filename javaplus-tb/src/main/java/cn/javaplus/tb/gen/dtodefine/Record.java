package cn.javaplus.tb.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Record {

	@Key
	String getSourceId();

	String getMineId();
	
	TbItem getSource();

	TbItem getMine();
	
	long getLastUpdateTime();
}
