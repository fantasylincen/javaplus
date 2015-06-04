package cn.vgame.a.gen.dtodefine;

import java.util.List;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * GM操作日志
 */
@Dao
interface GmLog {

	@Key
	String getId();

	/**
	 * 时间
	 */
	String getDate();

	/**
	 * 操作用户
	 */
	String getUser();

	/**
	 * 操作类名
	 * 
	 * @return
	 */
	String getClassName();

	/**
	 * 操作方法名
	 * 
	 * @return
	 */
	String getMethodName();

	/**
	 * 操作参数列表
	 * 
	 * @return
	 */
	List<String> getArgs();
	
	/**
	 * 执行结果
	 * @return
	 */
	String getResult();
}
