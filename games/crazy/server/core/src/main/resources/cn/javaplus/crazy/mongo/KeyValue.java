package cn.javaplus.crazy.mongo;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Key;

/**
 * 键值 存储器
 * 
 * @author 林岑
 * 
 */
@Dao
interface KeyValue {

	@Key
	String getK();

	String getV();

}
