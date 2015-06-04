package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface MxzToken {

	@Key
	String getLineKongUname();

	@Key
	String getToken();
	
	String getUserId();

	long getGenerateTime();
}
