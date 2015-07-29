package cn.javaplus.clickscreen;

import cn.javaplus.clickscreen.define.D;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	public static void main(String[] arg) {
		int w = (int) (D.STAGE_W * 0.5);
		int h = (int) (D.STAGE_H * 0.5);
		App.setOs(new Windows());
		new LwjglApplication(App.getApp(), "", w, h);
	}
}
