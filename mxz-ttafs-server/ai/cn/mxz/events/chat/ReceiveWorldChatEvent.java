package cn.mxz.events.chat;

import cn.mxz.chat.Message;
import cn.mxz.city.City;

/**
 * 收到世界聊天信息
 */
public class ReceiveWorldChatEvent {

	private City sender;
	private Message message;

	public ReceiveWorldChatEvent(City sender, Message message) {
		this.sender = sender;
		this.message = message;
	}
	
	public City getSender() {
		return sender;
	}
	
	public Message getMessage() {
		return message;
	}
	
}
