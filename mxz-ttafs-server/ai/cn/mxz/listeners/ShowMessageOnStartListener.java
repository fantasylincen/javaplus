package cn.mxz.listeners;

import cn.mxz.activity.boss.BossActivityStartEvent;
import cn.mxz.events.Listener2;


class ShowMessageOnStartListener implements Listener2 {

	@Override
	public void onEvent(Object e) {

//		new MessageSenderToAllUp().sendMessage(S.S71010);
	}

	@Override
	public Class<?> getEventListendClass() {
		return BossActivityStartEvent.class;
	}

}
