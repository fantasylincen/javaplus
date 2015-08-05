package cn.javaplus.clickscreen.tank;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class FireByTapListener extends ActorGestureListener {

	private long lastTapTime;
	private List<Tank> tanks;

	public FireByTapListener(List<Tank> tanks) {
		this.tanks = tanks;
	}

	@Override
	public void tap(InputEvent event, float x, float y, int count, int button) {
//		if (isTooFast())
//			return;
		for (Tank tank : tanks) {
			if (tank.isFireByTap())
				tank.fire();
		}
		lastTapTime = System.currentTimeMillis();
	}

	private boolean isTooFast() {
		return System.currentTimeMillis() - lastTapTime < 6;
	}
}
