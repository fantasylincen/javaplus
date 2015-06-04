package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface SystemKeyValue {

	@Key
	String getKey();

	String getValue();
}
