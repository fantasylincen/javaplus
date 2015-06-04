package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Index;
import cn.javaplus.db.mongo.Key;

/**
 * 所有的世界聊天记录
 * @author 林岑
 */
@Dao
interface WorldChatRecordAll {

	@Key
	long getId();
	
	/**
	 * 发送者ID
	 * @return
	 */
	@Index
	String getSender();
	
	/**
	 * 发送时间
	 * @return
	 */
	long getTime();
	/**
	 * 消息内容
	 * @return
	 */
	String getMessage();

}
