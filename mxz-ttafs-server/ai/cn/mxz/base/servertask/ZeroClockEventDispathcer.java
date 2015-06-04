package cn.mxz.base.servertask;

import cn.mxz.events.Events;
import cn.mxz.events.ZeroClockEvent;


class ZeroClockEventDispathcer extends TaskSafetyLogToFile {

	private ServerTask	serverTask;

	ZeroClockEventDispathcer(ServerTask serverTask) {
		this.serverTask = serverTask;
	}

	@Override
	public void runSafty() {
		serverTask.dispatchEvent(new ZeroClockEvent());
		Events.getInstance().dispatch(new ZeroClockEvent());
	}


}
