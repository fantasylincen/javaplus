package cn.mxz.listeners;

import cn.mxz.bag.AddPropEvent;
import cn.mxz.events.Listener2;

public class ShowMessageOnAddListener implements Listener2 {

	@Override
	public void onEvent(Object e) {
	}

	@Override
	public Class<?> getEventListendClass() {
		return AddPropEvent.class;
	}
}
