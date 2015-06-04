package cn.mxz.listeners;

import cn.mxz.base.config.KeyValueDefine;
import cn.mxz.base.config.KeyValueManagerImpl;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.base.server.AiSpringFactory;
import cn.mxz.base.telnet.TelnetServer;
import cn.mxz.base.telnet.TelnetServerLoginEvent;
import cn.mxz.events.Listener;
import cn.mxz.events.Listener2;
import cn.mxz.events.ServerStartEvent;

//服务器启动时
public class AIServerStartListener implements Listener<ServerStartEvent> {

	private class LoginListener implements Listener2 {

		@Override
		public void onEvent(Object e) {

			TelnetServerLoginEvent event = (TelnetServerLoginEvent) e;

			event.getOut().println("");
			event.getOut().println("欢迎进入游戏服务器控制台," + "服务器名字: " + ServerConfig.getConfig().getName());
		}

		@Override
		public Class<?> getEventListendClass() {
			return TelnetServerLoginEvent.class;
		}

	}

	@Override
	public void onEvent(ServerStartEvent e) {

		KeyValueManagerImpl k = new KeyValueManagerImpl();

		k.get(KeyValueDefine.SERVER_OPEN_TIME);// 初始化开服时间

		TelnetServer telnetServer = new TelnetServer("res/commands.xml");

		telnetServer.addListener(new LoginListener());

		AiSpringFactory springFactory = new AiSpringFactory();

		telnetServer.setSpringFactory(springFactory);

		telnetServer.start();
	}

}
