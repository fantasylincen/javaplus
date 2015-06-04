package cn.mxz.chat;

public interface ChatMessageUI {
	
	/**
	 * 发送者头像(神将ID)
	 */
	int getTypeId();
	
	/**
	 * 发送者(品质) 头像颜色
	 */
	int getStep();
	
	/**
	 * 发送者昵称
	 */
	String getNick();
	
	/**
	 * 发送者的ID
	 * @return
	 */
	String getId();
	
	/**
	 * 发送这是否是男性
	 * @return
	 */
	boolean isMan();
	
	/**
	 * 发送者等级
	 */
	int getLevel();

	/**
	 * 发送者vip等级
	 */
	int getVipLevel();

	/**
	 * 聊天内容
	 * @return
	 */
	String getContent();
	
	/**
	 * 这个人是不是我的仇敌
	 * @return
	 */
	boolean isEnemy();
	
	/**
	 * 是否是朋友
	 * @return
	 */
	boolean isFriend();
	
	/**
	 * 时间
	 * @return
	 */
	String getTime();
}
