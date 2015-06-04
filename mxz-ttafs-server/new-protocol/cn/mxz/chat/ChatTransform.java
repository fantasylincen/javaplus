package cn.mxz.chat;

import cn.javaplus.comunication.annotations.Communication;
import cn.mxz.city.City;

@Communication
public interface ChatTransform {

	/**
	 * 打开聊天消息
	 * 
	 * @param type
	 *            0:世界 1:联盟 2:私聊 3: 客服
	 */
	ChatUI openMessage(int type);


	/**
	 * 关闭消息提示
	 * 
	 * @param type
	 *            0:世界 1:联盟 2:私聊 3: 客服
	 */
	void closeTips(int type);
	
	/**
	 * 打开你跟某人的私聊消息
	 * @param userId
	 * @return
	 */
	ChatUI openPrivateMessage(String userId);
	
	void setUser(City user);
}
