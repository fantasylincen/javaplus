package org.hhhhhh.fqzs.desktop;

import org.hhhhhh.fqzs.core.App;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopLauncher {
	public static void main (String[] arg) {
		App listener = new App();
		new LwjglApplication(listener, "fqzs", 760, 1280);
	}
}
