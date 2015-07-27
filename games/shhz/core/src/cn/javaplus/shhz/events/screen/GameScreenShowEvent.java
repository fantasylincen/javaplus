package cn.javaplus.shhz.events.screen;

import cn.javaplus.shhz.screen.GameScreen;

/**
 * 屏幕-显示
 */
public class GameScreenShowEvent {

	private GameScreen gameScreen;

	public GameScreenShowEvent(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public GameScreen getScreen() {
		return gameScreen;
	}
}
