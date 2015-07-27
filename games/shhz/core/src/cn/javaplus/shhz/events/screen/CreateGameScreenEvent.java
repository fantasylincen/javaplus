package cn.javaplus.shhz.events.screen;

import cn.javaplus.shhz.App;
import cn.javaplus.shhz.screen.GameScreen;

/**
 * 屏幕初始化完成
 */
public class CreateGameScreenEvent {

	private GameScreen screen;
	private App game;

	public CreateGameScreenEvent(GameScreen screen, App game) {
		this.screen = screen;
		this.game = game;
	}

	public App getGame() {
		return game;
	}

	public GameScreen getScreen() {
		return screen;
	}

}
