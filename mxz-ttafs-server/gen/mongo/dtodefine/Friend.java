package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

@Dao
interface Friend {

	/**
	 * 我的帐号
	 *
	 * @return
	 */
	@Key
	String getUname();

	/**
	 * 朋友的帐号
	 *
	 * @return
	 */
	@Key
	String getFriendName();

}
