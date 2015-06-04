package cn.javaplus.db.mongo;

/**
 * 该字段作为主键
 *
 * 1.这个标记可以同时作用于多个字段, 如果有多个字段被作用, 那么这多个字段, 就会用:连接这多个字段写入 mongodb的_id字段中去,
 * 2.如果只有一个字段被作用, 那么这个字段被写入_id中
 *
 * 比如:
 *
 * 		Fighter {
 *
 * 			@Key
 * 			getUserId();
 *
 * 			@Key
 * 			getTempletId();
 *
 * 			getAttack();
 * 		}
 *
 * 那么  存入数据库中的结构将会是:  {"_id"="userId:templetId", "userId"=userId, "templetId"=templetId, "attack"=attack }
 *
 * @author 林岑
 */
public @interface Key {

}
