package cn.javaplus.jigsaw;

import cn.javaplus.jigsaw.define.D;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	public static void main(String[] arg) {
//		 int w = 480;
//		 int h = 800;
		 int w = (int) (D.STAGE_W * 0.5);
		 int h = (int) (D.STAGE_H * 0.5);
		App.setOs(new Windows());
		// Chain app = new Chain();
		// DemoGame app = new DemoGame();
		
		new LwjglApplication(App.getApp(), "", w, h);
//		new LwjglApplication(new MyGdxGame(), "", w, h);
	}
}
