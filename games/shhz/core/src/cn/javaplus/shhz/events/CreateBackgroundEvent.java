package cn.javaplus.shhz.events;

import cn.javaplus.shhz.App;
import cn.javaplus.shhz.components.Background;
import cn.javaplus.shhz.events.stage.CreateGameStageEvent;
import cn.javaplus.shhz.screen.GameScreen;
import cn.javaplus.shhz.stage.GameStage;

public class CreateBackgroundEvent {

	private Background background;
	private App game;
	private GameScreen screen;
	private GameStage stage;

	public CreateBackgroundEvent(Background background, CreateGameStageEvent e) {
		this.background = background;
		this.game = e.getGame();
		this.screen = e.getGameScreen();
		this.stage = e.getStage();
	}

	public Background getBackground() {
		return background;
	}

	public App getGame() {
		return game;
	}

	public GameScreen getScreen() {
		return screen;
	}

	public GameStage getStage() {
		return stage;
	}

}
