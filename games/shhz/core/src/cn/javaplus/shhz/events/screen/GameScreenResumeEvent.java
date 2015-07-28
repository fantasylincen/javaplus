package cn.javaplus.shhz.events.screen;

import cn.javaplus.shhz.screen.GameScreen;

/**
 * 屏幕-恢复
 */
public class GameScreenResumeEvent {

	private GameScreen gameScreen;

	public GameScreenResumeEvent(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public GameScreen getScreen() {
		return gameScreen;
	}
}
