package cn.mxz.events.chat;

import cn.mxz.chat.Message;
import cn.mxz.city.City;

/**
 * 当收到别人的私聊消息
 * @author 林岑
 *
 */
public class ReceiveUserChatEvent {

	private City receiver;
	private City sender;
	private Message message;

	public ReceiveUserChatEvent(City receiver, City sender, Message message) {
		this.receiver = receiver;
		this.sender = sender;
		this.message = message;
	}
	
	public City getReceiver() {
		return receiver;
	}
	
	public City getSender() {
		return sender;
	}
	
	public Message getMessage() {
		return message;
	}

}
