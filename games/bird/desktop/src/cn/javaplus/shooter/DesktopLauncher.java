package cn.javaplus.shooter;

import cn.javaplus.shooter.App;
import cn.javaplus.shooter.define.D;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	public static void main(String[] arg) {
		int w = (int) (D.STAGE_W * 0.6);
		int h = (int) (D.STAGE_H * 0.6);
		App app = new App(new DesktopPlantform());
		new LwjglApplication(app, "", w, h);
	}
}
