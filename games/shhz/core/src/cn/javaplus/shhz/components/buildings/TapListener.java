package cn.javaplus.shhz.components.buildings;

import cn.javaplus.shhz.stage.GameStage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class TapListener extends ActorGestureListener implements Switch {

	/**
	 * 
	 */
	private final Building building;

	/**
	 * @param building
	 */
	TapListener(Building building) {
		this.building = building;
	}

	@Override
	public void tap(InputEvent event, float x, float y, int c, int b) {
		if (!enable) {
			return;
		}
		GameStage s = (GameStage) this.building.getStage();
		if (s.isSelect(this.building)) {
			s.cancelSelected();
		} else {
			s.select(this.building);
		}
	}

	private boolean enable = true;

	public void enable() {
		this.enable = true;
	}

	public void disable() {
		this.enable = false;
	}

	public boolean isEnable() {
		return enable;
	}
}