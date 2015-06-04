package cn.mxz.chat;

import java.util.List;

/**
 * 聊天消息列表
 * @author 林岑
 *
 */
public interface ChatUI {
	
	/**
	 * 所有消息
	 * @return
	 */
	List<ChatMessageUI> getMessages();
}
