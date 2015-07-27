package cn.javaplus.game.power.stage;

import com.badlogic.gdx.scenes.scene2d.IActor;

public class OnSelectedEvent {

	private GameStage stage;
	private IActor target;

	public OnSelectedEvent(GameStage stage, IActor target) {
		this.stage = stage;
		this.target = target;
	}

	public GameStage getStage() {
		return stage;
	}

	/**
	 * 被選定的對象
	 * 
	 * @return
	 */
	public IActor getTarget() {
		return target;
	}
}
