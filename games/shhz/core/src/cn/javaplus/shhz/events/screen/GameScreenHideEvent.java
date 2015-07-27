package cn.javaplus.shhz.events.screen;

import cn.javaplus.shhz.screen.GameScreen;

/**
 * 屏幕-隐藏
 */
public class GameScreenHideEvent {

	private GameScreen gameScreen;

	public GameScreenHideEvent(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public GameScreen getScreen() {
		return gameScreen;
	}
}
