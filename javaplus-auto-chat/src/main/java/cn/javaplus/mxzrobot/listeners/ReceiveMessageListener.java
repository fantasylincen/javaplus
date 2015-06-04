package cn.javaplus.mxzrobot.listeners;

import iqq.im.bean.QQMsg;
import iqq.im.bean.QQUser;
import cn.javaplus.mxzrobot.events.ReceiveMessageEvent;
import cn.mxz.events.Listener;

public class ReceiveMessageListener implements Listener<ReceiveMessageEvent> {

	public void onEvent(ReceiveMessageEvent e) {
		QQMsg ms = e.getMsg();
		QQUser user = ms.getFrom();
		System.out.println("ReceiveMessageListener.onEvent() 收到消息: " + ms);
		System.out.println("ReceiveMessageListener.onEvent() 用户: " + user) ;
	}

}
