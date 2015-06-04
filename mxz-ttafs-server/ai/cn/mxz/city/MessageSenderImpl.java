package cn.mxz.city;

import cn.mxz.base.telnet.command.system.sendmessage.MessageType;

public class MessageSenderImpl extends AbstractMessageSender implements
		IMessageSender {

	public MessageSenderImpl(City city) {
		super(city);
	}

	protected int getMessageType() {
		return MessageType.APPEND;
	}

}
