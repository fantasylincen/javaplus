package cn.javaplus.shhz.events.screen;

import cn.javaplus.shhz.screen.GameScreen;

/**
 * 屏幕-销毁
 */
public class GameScreenDisposeEvent {

	private GameScreen gameScreen;

	public GameScreenDisposeEvent(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public GameScreen getScreen() {
		return gameScreen;
	}
}
