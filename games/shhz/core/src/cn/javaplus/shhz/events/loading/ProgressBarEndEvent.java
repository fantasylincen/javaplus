package cn.javaplus.shhz.events.loading;

import cn.javaplus.shhz.App;
import cn.javaplus.shhz.screen.LoadingScreen;

public class ProgressBarEndEvent {

	private App game;
	private LoadingScreen screen;

	public ProgressBarEndEvent(App game, LoadingScreen screen) {
		this.game = game;
		this.screen = screen;
	}

	public App getGame() {
		return game;
	}

	public LoadingScreen getScreen() {
		return screen;
	}
}
