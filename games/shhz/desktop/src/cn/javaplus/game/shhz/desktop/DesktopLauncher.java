package cn.javaplus.game.shhz.desktop;

import cn.javaplus.shhz.App;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	public static void main(String[] arg) {
		// new LwjglApplication(Game.createInstance(), "", 240, 160);
		new LwjglApplication(new App(), "", 1024, 768);
	}
}
