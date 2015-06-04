package cn.mxz.listeners;

import cn.mxz.events.Listener2;
import cn.mxz.mission.old.events.EnterEvent;

public class ClearStarListener implements Listener2 {

	@Override
	public void onEvent(Object e) {
//		EnterEvent event = (EnterEvent) e;
//		IMissionManager ms = event.getSouce().getMission();
//		MissionStarManager sm = ms.getStarManager();
//		sm.clear(ms.getCurMissionId());
	}

	@Override
	public Class<?> getEventListendClass() {
		return EnterEvent.class;
	}

}
