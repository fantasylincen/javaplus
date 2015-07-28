package cn.javaplus.twolegs.game;

import org.mxz.events.Listener;

import cn.javaplus.twolegs.App;

public class ShowAds implements Listener<ExitEvent> {

	@Override
	public void onEvent(ExitEvent e) {
		App.getChannel().exit();
	}
}
