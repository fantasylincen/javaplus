package cn.javaplus.crazy.mongo;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 用户
 * 
 * @author 林岑
 * 
 */
@Dao
interface User {

	@Key
	String getUname();

	/**
	 * 密码 加密后
	 * 
	 * @return
	 */
	String getKey();

	/**
	 * 当前使用的角色ID
	 * 
	 * @return
	 */
	int currentRoleId();
}
