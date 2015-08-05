package cn.javaplus.shhz.events.screen;

import cn.javaplus.shhz.screen.GameScreen;

/**
 * 屏幕-大小变更
 */
public class GameScreenResizeEvent {

	private GameScreen gameScreen;
	private int width;
	private int height;

	public GameScreenResizeEvent(GameScreen gameScreen, int width, int height) {
		this.gameScreen = gameScreen;
		this.width = width;
		this.height = height;
	}

	public GameScreen getScreen() {
		return gameScreen;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
