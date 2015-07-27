package cn.javaplus.game.power;

import cn.mxz.events.Events;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class WindowsApp {

	public static void main(String[] a) {
		Events.getInstance().loadListeners("cn.javaplus.game.power.listeners");
		Game game = new Game("C:/Users/Administrator/git/games/javaplus-game-power/assets/");
		new LwjglApplication(game, "Cubocy", 480, 480, true);
	}

	static public boolean isPowerOfTwo(int value) {
		return value != 0 && (value & value - 1) == 0;
	}
}
