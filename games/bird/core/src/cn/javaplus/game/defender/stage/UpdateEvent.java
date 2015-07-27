package cn.javaplus.game.defender.stage;

import com.badlogic.gdx.scenes.scene2d.Event;

public class UpdateEvent extends Event {

	private GameStage stage;

	public UpdateEvent(GameStage stage) {
		this.stage = stage;
	}

	public GameStage getStage() {
		return stage;
	}
}
