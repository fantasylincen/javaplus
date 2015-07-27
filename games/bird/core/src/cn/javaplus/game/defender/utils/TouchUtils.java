package cn.javaplus.game.defender.utils;

import cn.javaplus.game.defender.App;
import cn.javaplus.game.defender.screen.Bound;

import com.badlogic.gdx.Gdx;

public class TouchUtils {

	/**
	 * 是否碰到了
	 * 
	 * @param b
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean isTouch(Bound b, int x, int y) {
		if (x < b.getX()) {
			return false;
		}
		if (x > b.getX() + b.getWidth()) {
			return false;
		}
		if (y < b.getY()) {
			return false;
		}
		if (y > b.getY() + b.getHeight()) {
			return false;
		}
		return true;
	}

	/**
	 * 屏幕上的X坐标转换成舞台坐标
	 * 
	 * @param screenX
	 * @return
	 */
	public static float parseY(float screenX) {
		float screenY;
		int gameHeight = App.getStageHeight();
		screenY = gameHeight * (screenX + 0f) / Gdx.graphics.getHeight();
		return gameHeight - screenY;
	}

	/**
	 * 屏幕上的Y坐标转换成舞台坐标
	 * 
	 * @param screenX
	 * @return
	 */
	public static float parseX(float screenX) {
		int gameWidth = App.getStageWidth();
		screenX = gameWidth * (screenX + 0f) / Gdx.graphics.getWidth();
		return screenX;
	}
}
