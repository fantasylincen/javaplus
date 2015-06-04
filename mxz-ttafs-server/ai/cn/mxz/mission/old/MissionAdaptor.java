package cn.mxz.mission.old;

import cn.mxz.mission.old.events.EndEvent;
import cn.mxz.mission.old.events.EnterEvent;
import cn.mxz.mission.old.listeners.MissionListener;

public class MissionAdaptor implements MissionListener{

	@Override
	public void onEnter(EnterEvent e) {}

	@Override
	public void onEnd(EndEvent e) {}

	@Override
	public void onNewPlayerEnd(NewPlayerEndEvent e) {}

	@Override
	public void onGiveUp(GiveUpEvent giveUpEvent) {}

	@Override
	public void onBeforeEnter(BeforeEnterEvent beforeEnterEvent) {}
}