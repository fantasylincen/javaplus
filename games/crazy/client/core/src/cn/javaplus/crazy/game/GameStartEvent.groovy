package cn.javaplus.crazy.game;

import cn.javaplus.crazy.App;

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
