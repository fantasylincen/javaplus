package cn.javaplus.game.power.events;

import cn.javaplus.game.power.stage.GameModel;

public class MoveEvent {

	private GameModel gameModel;
	private int[][] values;

	public MoveEvent(GameModel gameModel, int [] [] values) {
		this.gameModel = gameModel;
		this.values = values;
	}

	public GameModel getGameModel() {
		return gameModel;
	}
	public int[][] getValues() {
		return values;
	}
}
