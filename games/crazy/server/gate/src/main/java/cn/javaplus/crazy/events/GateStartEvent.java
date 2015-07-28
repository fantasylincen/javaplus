package cn.javaplus.crazy.events;

import cn.javaplus.crazy.handlers.Gate;

public class GateStartEvent {

	private Gate server;

	public GateStartEvent(Gate server) {
		this.server = server;
	}

	public Gate getGate() {
		return server;
	}
}
