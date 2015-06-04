package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 玩家CD
 *
 * @author 林岑
 *
 */
@Dao
interface ColdDown {

	@Key
	String getUname();

	@Key
	int getIndex();

	long getEndTime();

}
