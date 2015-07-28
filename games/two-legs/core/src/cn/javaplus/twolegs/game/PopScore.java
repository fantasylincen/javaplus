package cn.javaplus.twolegs.game;

import org.mxz.events.Listener;

public class PopScore implements Listener<GameOverEvent> {

	@Override
	public void onEvent(GameOverEvent e) {
		GameStage stage = e.getStage();
		stage.popScore(e.getScoreText());
	}

}
