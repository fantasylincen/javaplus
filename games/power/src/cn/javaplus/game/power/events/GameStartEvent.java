package cn.javaplus.game.power.events;

import cn.javaplus.game.power.assets.GameAssetsManager;
import cn.javaplus.game.power.record.Record;
import cn.javaplus.game.power.screen.GameScreen;
import cn.javaplus.game.power.stage.GameInputProcessor;
import cn.javaplus.game.power.stage.GameModel;
import cn.javaplus.game.power.stage.GameStage;

public class GameStartEvent {

	private GameInputProcessor input;
	private GameScreen screen;
	private int screenWidth;
	private int screenHeight;
	private Record record;
	private GameAssetsManager assetsManager;
	private GameModel gameModel;

	public GameStartEvent(GameInputProcessor input, GameScreen currentScreen,
			int screenWidth, int screenHeight, Record record, GameAssetsManager assetsManager, GameModel gameModel) {
				this.input = input;
				this.screen = currentScreen;
				this.screenWidth = screenWidth;
				this.screenHeight = screenHeight;
				this.record = record;
				this.assetsManager = assetsManager;
				this.gameModel = gameModel;
	}

	
	public GameAssetsManager getAssetsManager() {
		return assetsManager;
	}
	
	public GameScreen getScreen() {
		return screen;
	}
	
	public GameInputProcessor getInput() {
		return input;
	}
	
	public Record getRecord() {
		return record;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}

	public GameStage getStage() {
		return screen.getStage();
	}
	
	public GameModel getGameModel() {
		return gameModel;
	}
}
