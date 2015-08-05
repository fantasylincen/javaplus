package cn.javaplus.twolegs.game;

import org.javaplus.game.common.log.Log;

import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.define.D;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class RobotTouchPad extends Actor {

	private RobotController controller;

	private final class InputListenerImpl extends InputListener {
		boolean hasTouchd;

		@Override
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
			if (!hasTouchd) {
				onTouchFirst();
				hasTouchd = true;
			}
			controller.touchDown();
			logUserHabit(x, y);
			return true;
		}

		/**
		 * 分析用户点击习惯
		 * @param x
		 * @param y
		 */
		private void logUserHabit(float x, float y) {
			Log.d("RobotTouchPad.InputListenerImpl", "logUserHabit", x, y);
		}

		private void onTouchFirst() {
			World world = ((GameStage) App.getApp().getStage()).getWorld();
			world.setGravity(new Vector2(D.GRAVITY_X, D.GRAVITY_Y));
		}
	}

	public RobotTouchPad(RobotController controller) {
		this.controller = controller;
		addCaptureListener(new InputListenerImpl());
	}
}
