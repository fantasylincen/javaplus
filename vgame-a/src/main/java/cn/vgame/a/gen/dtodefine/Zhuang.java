package cn.vgame.a.gen.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 上庄排队列表
 */
@Dao
interface Zhuang {

	@Key
	String getId();

	/**
	 * 开始排队的时间
	 */
	long getTime();
	
	/**
	 * 是否正在坐庄
	 */
	boolean isZhuang();
}
