package cn.vgame.gate.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface User {

	@Key
	String getId();
	
	String getPassword();
}
