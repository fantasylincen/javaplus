package cn.javaplus.twolegs.game;

import org.javaplus.game.common.Logger;
import org.mxz.events.Listener;

import cn.javaplus.twolegs.App;

public class SaveLog implements Listener<GameOverEvent> {

	@Override
	public void onEvent(GameOverEvent e) {
//		String score = e.getScoreText();
		Logger logger = App.getLogger();
		logger.onCountEvent("gameover");
	}

}
