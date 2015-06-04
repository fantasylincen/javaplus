package cn.mxz.listeners;

import cn.mxz.base.telnet.TelnetServer;
import cn.mxz.base.telnet.command.system.StopServerEvent;
import cn.mxz.events.Listener2;


public class StopTelnetServerListener implements Listener2 {

	private TelnetServer	telnetServer;

	public StopTelnetServerListener(TelnetServer telnetServer) {
		this.telnetServer = telnetServer;
	}

	@Override
	public void onEvent(Object e) {
		telnetServer.stop();
	}

	@Override
	public Class<?> getEventListendClass() {
		return StopServerEvent.class;
	}

}
