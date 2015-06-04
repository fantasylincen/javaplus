package cn.mxz.city;

import message.S;
import cn.mxz.messagesender.MessageFactory;

public abstract class AbstractMessageSender {

	private static final String	FORMAT_STRING_SEPARATOR	= "$";

	private City	city;

	private boolean	isOpen = true;

	public AbstractMessageSender(City city) {
		this.city = city;
	}

	public void send(int code, Object... infos) {
		if(!isOpen) {
			return;
		}

		MessageFactory.getSystem().sendMessage(city.getSocket(), code, buildInfos(infos), getMessageType());
	}

	protected abstract int getMessageType();

	public void sendText(String text) {
		send(S.S0, text);
	}

	private String buildInfos(Object[] infos) {

		StringBuilder s = new StringBuilder();

		for (int i = 0; i < infos.length; i++) {

			s.append(infos[i]);

			if(i != infos.length - 1) {

				s.append(FORMAT_STRING_SEPARATOR);
			}
		}

		return s.toString();
	}

	public String buildText(int code, Object... infos) {
		return Messages.getText(code, infos);
	}

	public void close() {
		this.isOpen = false;
	}

	public void open() {
		this.isOpen = true;
	}
}
