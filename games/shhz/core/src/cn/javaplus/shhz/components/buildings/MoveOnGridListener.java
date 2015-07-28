package cn.javaplus.shhz.components.buildings;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class MoveOnGridListener extends ActorGestureListener implements Switch {

	private Building building;
	private float startX;
	private float startY;

	public MoveOnGridListener(Building building) {
		this.building = building;
	}

	@Override
	public void touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		if (!enable) {
			return;
		}
		this.startX = x;
		this.startY = y;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
	}

	@Override
	public void pan(InputEvent event, float x, float y, float deltaX,
			float deltaY) {
		if (!enable) {
			return;
		}
		float stageX = event.getStageX();
		float stageY = event.getStageY();
		int xx = (int) (stageX - startX);
		int yy = (int) (stageY - startY);
		building.setPosition(xx, yy);
	}

	private boolean enable;

	public boolean isEnable() {
		return enable;
	}

	public void enable() {
		this.enable = true;
	}

	public void disable() {
		this.enable = false;
	}
}
