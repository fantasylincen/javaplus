package org.hhhhhh.fqzs.desktop;

import org.javaplus.game.common.util.Util;

public class AutoEnterThread extends Thread {
	/**
	 * 
	 */
	private final RoleFrame roleFrame;

	/**
	 * @param roleFrame
	 */
	AutoEnterThread(RoleFrame roleFrame) {
		this.roleFrame = roleFrame;
	}

	@Override
	public void run() {
		Util.Thread.sleep(500);
//		this.roleFrame.selectRoleAndEnterGame();
	}
}