package cn.mxz.listeners;

import cn.mxz.base.logserver.LogServer;
import cn.mxz.base.telnet.TelnetServerLoginEvent;
import cn.mxz.events.Listener2;

public class LoginListener implements Listener2 {

	/**
	 * @param logServer
	 */
	public LoginListener(LogServer logServer) {
	}

	@Override
	public void onEvent(Object e) {

		TelnetServerLoginEvent event = (TelnetServerLoginEvent) e;

		event.getOut().println("欢迎进入日志分析服务器");
	}

	@Override
	public Class<?> getEventListendClass() {
		return TelnetServerLoginEvent.class;
	}

}