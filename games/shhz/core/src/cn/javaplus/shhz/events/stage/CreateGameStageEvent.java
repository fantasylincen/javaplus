package cn.javaplus.shhz.events.stage;

import cn.javaplus.shhz.App;
import cn.javaplus.shhz.screen.ControllerStage;
import cn.javaplus.shhz.screen.GameScreen;
import cn.javaplus.shhz.stage.GameStage;

public class CreateGameStageEvent {

	private App game;
	private GameScreen gameScreen;

	public CreateGameStageEvent(App game, GameScreen gameScreen) {
		this.game = game;
		this.gameScreen = gameScreen;
	}

	public GameStage getStage() {
		return gameScreen.getGameStage();
	}

	public App getGame() {
		return game;
	}

	public ControllerStage getControllerStage() {
		return gameScreen.getControllerStage();
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

}
