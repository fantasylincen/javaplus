package cn.mxz.base.logserver;

import cn.mxz.base.server.Server;
import cn.mxz.base.telnet.TelnetServer;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.listeners.LoginListener;

/**
 * 日志分析服务器
 * @author 林岑
 *
 */
public class LogServer extends EventDispatcher2Impl implements Server {

	private TelnetServer telnetServer;

	public boolean isRunning() {
		return telnetServer.isRunning();
	}

	public LogServer() {

		telnetServer = new TelnetServer("res/log_server_commands.xml");

		telnetServer.setSpringFactory(new LogServerFactory());

		telnetServer.addListener(new LoginListener(this));
	}

	@Override
	public void start() {

		telnetServer.start();
	}

	@Override
	public void stop() {

		telnetServer.stop();
	}
	
	
}
