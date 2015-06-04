package cn.mxz.listeners;

import cn.mxz.activity.boss.BossActivityStopEvent;
import cn.mxz.events.Listener2;


class ShowMessageOnStopListener implements Listener2 {

	@Override
	public void onEvent(Object e) {

	}

	@Override
	public Class<?> getEventListendClass() {
		return BossActivityStopEvent.class;
	}

}
