package cn.mxz.listeners;

import cn.mxz.activity.boss.StopDelayEvent;
import cn.mxz.events.Listener2;


public class ShowMessageOnWaitStopListener implements Listener2 {

	@Override
	public void onEvent(Object e) {

//		new MessageSenderToAllUp().sendMessage(S.S71012);
	}

	@Override
	public Class<?> getEventListendClass() {
		return StopDelayEvent.class;
	}

}
