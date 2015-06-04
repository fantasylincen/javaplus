package cn.mxz.notice;

import cn.mxz.base.telnet.command.system.sendmessage.MessageType;
import cn.mxz.city.AbstractMessageSender;
import cn.mxz.city.City;
import cn.mxz.city.IMessageSender;

public class NoticeSender extends AbstractMessageSender implements
		IMessageSender {

	public NoticeSender(City city) {
		super(city);
	}

	@Override
	protected int getMessageType() {
		return MessageType.NOTICE;
	}

}
