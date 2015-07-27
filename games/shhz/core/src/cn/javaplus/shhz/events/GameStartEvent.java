package cn.javaplus.shhz.events;

import cn.javaplus.shhz.App;

/**
 * 游戏启动
 */
public class GameStartEvent {

	private App game;

	public GameStartEvent(App game) {
		this.game = game;
	}

	public App getGame() {
		return game;
	}
}
