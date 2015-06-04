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
interface InviteCode {

	/**
	 * 用户
	 */
	@Key
	String getUname();

	/**
	 * 邀请码
	 */
	@Index
	String getCode();
}
