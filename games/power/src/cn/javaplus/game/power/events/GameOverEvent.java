package cn.javaplus.game.power.events;

import cn.javaplus.game.power.stage.GameModel;

public class GameOverEvent {

	private GameModel gameModel;

	public GameOverEvent(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public GameModel getGameModel() {
		return gameModel;
	}
}
