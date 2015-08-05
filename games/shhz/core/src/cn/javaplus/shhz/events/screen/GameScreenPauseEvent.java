package cn.javaplus.shhz.events.screen;

import cn.javaplus.shhz.screen.GameScreen;

/**
 * 屏幕-暂停
 */
public class GameScreenPauseEvent {

	private GameScreen gameScreen;

	public GameScreenPauseEvent(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public GameScreen getScreen() {
		return gameScreen;
	}
}
