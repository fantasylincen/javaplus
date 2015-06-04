package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface KeyValueData {

	@Key
	String getUname();

	String getData();
}
