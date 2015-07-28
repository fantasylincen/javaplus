package cn.javaplus.twolegs;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	public static void main(String[] arg) {
		// int w = (int) (D.STAGE_W * 0.6);
		// int h = (int) (D.STAGE_H * 0.6);
		// int w = (int) (D.STAGE_W * 1);
		// int h = (int) (D.STAGE_H * 1);
		int w = (int) (800);
		int h = 480;
		App.setOs(new Windows());
		// Chain app = new Chain();
		// DemoGame app = new DemoGame();
		new LwjglApplication(App.getApp(), "", w, h);
	}
}
