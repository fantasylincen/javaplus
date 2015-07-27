package cn.javaplus.shhz.events.stage;

import cn.javaplus.shhz.stage.GameStage;

public class GameStageTouchDownEvent {

	private GameStage stage;
	private int screenX;
	private int screenY;
	private int pointer;
	private int button;

	public GameStageTouchDownEvent(GameStage stage, int screenX, int screenY,
			int pointer, int button) {
		this.stage = stage;
		this.screenX = screenX;
		this.screenY = screenY;
		this.pointer = pointer;
		this.button = button;
	}

	public GameStage getStage() {
		return stage;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
	}

	public int getPointer() {
		return pointer;
	}

	public int getButton() {
		return button;
	}

}
