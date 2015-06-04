package cn.javaplus.mxzrobot.listeners;

import cn.javaplus.mxzrobot.MessageHandler;
import cn.javaplus.mxzrobot.events.LoginOKEvent;
import cn.mxz.events.Listener;

public class StartMessageHandler implements Listener<LoginOKEvent> {

	public void onEvent(LoginOKEvent e) {
		new MessageHandler(e.getClient()).start();
	}

}
