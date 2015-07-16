package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface YinShang {

	@Key
	String getId();

	String getRoleId();
	
	String getPassword();
}
