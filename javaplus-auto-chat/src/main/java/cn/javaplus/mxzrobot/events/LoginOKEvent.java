package cn.javaplus.mxzrobot.events;

import iqq.im.QQClient;

public class LoginOKEvent {

	private QQClient client;

	public LoginOKEvent(QQClient client) {
		this.client = client;
	}

	public QQClient getClient() {
		return client;
	}
}
