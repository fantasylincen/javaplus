package cn.javaplus.game.power.listeners;

import cn.javaplus.game.power.events.GameStartEvent;
import cn.mxz.events.Listener;
import cn.javaplus.game.power.stage.GameActor;
import cn.javaplus.game.power.stage.GameModel;
import cn.javaplus.game.power.stage.GameStage;
import cn.javaplus.game.power.stage.ScoreActor;

public class InitActors implements Listener<GameStartEvent> {

	@Override
	public void onEvent(GameStartEvent e) {
		GameModel game = e.getGameModel();
		GameStage stage = e.getStage();
		stage.addActor(new GameActor(game));
		stage.addActor(new ScoreActor());		
	}

}
