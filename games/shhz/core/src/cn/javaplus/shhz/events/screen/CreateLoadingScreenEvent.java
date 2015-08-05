package cn.javaplus.shhz.events.screen;

import cn.javaplus.shhz.App;
import cn.javaplus.shhz.screen.LoadingScreen;

/**
 * 屏幕初始化完成
 */
public class CreateLoadingScreenEvent {

	private LoadingScreen screen;
	private App game;

	public CreateLoadingScreenEvent(LoadingScreen screen, App game) {
		this.screen = screen;
		this.game = game;
	}

	public App getGame() {
		return game;
	}

	public LoadingScreen getScreen() {
		return screen;
	}

}
