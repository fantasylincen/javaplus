package cn.javaplus.shhz.components.buildings;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class HasTouchDownListener extends InputListener {
	private boolean isTouchDown;

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) {
		isTouchDown = true;
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer,
			int button) {
		isTouchDown = false;
	}

	public boolean isTouchDown() {
		return isTouchDown;
	}

}
