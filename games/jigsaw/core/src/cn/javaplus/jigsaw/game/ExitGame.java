package cn.javaplus.jigsaw.game;

import org.mxz.events.Listener;

import cn.javaplus.jigsaw.App;

public class ExitGame implements Listener<ExitEvent> {

	@Override
	public void onEvent(ExitEvent e) {
		App.getChannel().exit();
	}
}
