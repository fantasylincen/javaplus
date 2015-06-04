package cn.javaplus.mxzrobot.events;

import iqq.im.bean.QQMsg;

public class ReceiveMessageEvent {

	private QQMsg msg;

	public ReceiveMessageEvent(QQMsg msg) {
		this.msg = msg;
	}
	
	public QQMsg getMsg() {
		return msg;
	}

}
