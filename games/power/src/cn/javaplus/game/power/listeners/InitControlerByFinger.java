package cn.javaplus.game.power.listeners;

import cn.javaplus.game.power.events.GameStartEvent;
import cn.javaplus.game.power.stage.GameInputProcessor;
import cn.javaplus.game.power.stage.GameModel;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;

import cn.mxz.events.Listener;

public class InitControlerByFinger implements Listener<GameStartEvent> {

	public class MyGestureAdaptor extends GestureAdapter {

		private GameModel game;
		private float startX;
		private float startY;
		private float endX;
		private float endY;

		public MyGestureAdaptor(GameModel game) {
			this.game = game;
		}

		@Override
		public boolean fling(float vx, float vy, int button) {
			float xabs = Math.abs(vx);
			float yabs = Math.abs(vy);
			if (xabs < 200 && yabs < 200) {
				return false;
			}
			if (xabs > yabs) {
				onX();
			} else {
				onY();
			}
			return true;
		}

		private void onX() {
			if (startX > endX) {
				game.left();
			} else {
				game.right();
			}
		}

		private void onY() {
			if (startY > endY) {
				game.up();
			} else {
				game.down();
			}
		}

		@Override
		public boolean panStop(float x, float y, int pointer, int button) {
			this.endX = x;
			this.endY = y;
			return true;
		}

		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {
			this.startX = x;
			this.startY = y;
			return true;
		}
	}

	@Override
	public void onEvent(GameStartEvent e) {
		GameModel game = e.getGameModel();
		GameInputProcessor input = e.getInput();
		input.add(new GestureDetector(new MyGestureAdaptor(game)));
	}

}
