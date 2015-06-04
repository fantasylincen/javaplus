package mongo.dtodefine;

import cn.javaplus.db.mongo.Dao;
import cn.javaplus.db.mongo.Index;
import cn.javaplus.db.mongo.Key;

/**
 * 用户聊天记录
 * @author 林岑
 */
@Dao
interface UserChatRecord {

	@Key
	long getId();
	
	/**
	 * 所属玩家
	 * @return
	 */
	@Index
	String getSender();

	/**
	 * 接受者消息
	 *
	 * @return
	 */
	@Key
	String getReceiver();
	
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
	
	/**
	 * 已读?
	 * @return
	 */
	boolean getHasRead();
}
