package cn.javaplus.smonitor.gen.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface User {

	@Key
	String getId();

	double getRmb();
	
	List<GuPiao> getGupiaos();
	
	List<Select> getSelects();
}
