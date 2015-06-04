package cn.mxz.listeners;

import cn.mxz.activity.boss.StartDelayEvent;
import cn.mxz.events.Listener2;


public class ShowMessageOnWaitStartListener implements Listener2 {

	@Override
	public void onEvent(Object e) {

//		new MessageSenderToAllUp().sendMessage(S.S71009, 15 + "");
	}

	@Override
	public Class<?> getEventListendClass() {
		return StartDelayEvent.class;
	}

}
