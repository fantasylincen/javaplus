package cn.javaplus.twolegs.ads;

import org.mxz.events.Listener;

import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.game.GameOverEvent;

public class ShowPopAds implements Listener<GameOverEvent> {

	@Override
	public void onEvent(GameOverEvent e) {
		App.getChannel().gameOver();
	}

}
