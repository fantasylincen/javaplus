package cn.javaplus.monichaogu.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface KeyValue {
	
	@Key
	String getKey();
	String getValue();
}
