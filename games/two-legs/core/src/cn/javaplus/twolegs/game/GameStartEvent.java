package cn.javaplus.twolegs.game;

import cn.javaplus.twolegs.App;

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
