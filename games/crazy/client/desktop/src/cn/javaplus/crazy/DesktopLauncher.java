package cn.javaplus.crazy;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	public static void main(String[] arg) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		App app = new App();
		new LwjglApplication(app, "", app.getW(), app.getH());
	}
}
