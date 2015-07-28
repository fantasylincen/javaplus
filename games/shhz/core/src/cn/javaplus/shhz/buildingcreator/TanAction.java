package cn.javaplus.shhz.buildingcreator;

import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;

public class TanAction extends ScaleToAction {

	public TanAction() {
		setScale(getScale());
		setDuration(getDuration());
	}

	public float getDuration() {
		return 0.07f;
	}

	public float getScale() {
		return 50;
	}

}
