package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 玩家引导记录的信息
 *
 * @author 林岑
 *
 */
@Dao
interface UserGuide {

	@Key
	String getUname();

	@Key
	int getGuideId();

	String getGuideStatus();

}
