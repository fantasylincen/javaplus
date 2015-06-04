package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Index;
import cn.javaplus.db.mongo.Key;

/**
 * 某人的邀请码
 * 
 * @author 林岑
 * 
 */
@Dao
interface InviteUsers {

	/**
	 * 被邀请方
	 */
	@Key
	String getA();

	/**
	 * 邀请方
	 */
	@Index
	String getB();
}
