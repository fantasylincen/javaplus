package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 拥有过的战士列表
 *
 * @author 林岑
 *
 */
@Dao
interface FighterHad {

	@Key
	String getUname();

	@Key
	int getFighterTypeId();
}
