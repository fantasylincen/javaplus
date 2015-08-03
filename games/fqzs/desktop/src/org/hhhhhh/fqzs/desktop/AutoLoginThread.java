package org.hhhhhh.fqzs.desktop;

import org.javaplus.game.common.util.Util;

public class AutoLoginThread extends Thread {
	/**
	 * 
	 */
	private final LoginFrame loginFrame;

	/**
	 * @param loginFrame
	 */
	AutoLoginThread(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	@Override
	public void run() {
		Util.Thread.sleep(500);
//		this.loginFrame.login();
	}

}