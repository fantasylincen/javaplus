package cn.mxz.base.servertask;

import cn.mxz.events.Events;
import cn.mxz.events.RateSystemEvent;

public class DispatchSystemEvent extends TaskSafetyLogToFile {

	@Override
	public void runSafty() {
		Events instance = Events.getInstance();
		instance.dispatch(new RateSystemEvent());
	}

}
