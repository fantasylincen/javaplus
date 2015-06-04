package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Index;

/**
 * 物品消耗日志
 * 
 * @author 林岑
 * 
 */
@Dao
interface PropConsumeLog {

	/**
	 * 时间
	 */
	long getTime();

	/**
	 * 角色ID
	 */
	@Index
	String getUname();

	/**
	 * 道具ID
	 */
	int getPropId();

	/**
	 * 消耗数量
	 * 
	 * @return
	 */
	int getCount();
}
