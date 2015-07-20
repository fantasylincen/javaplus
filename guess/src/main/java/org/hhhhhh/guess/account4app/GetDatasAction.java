package org.hhhhhh.guess.account4app;

import org.hhhhhh.guess.action.JsonActionAfterRoleEnterGame;

public class GetDatasAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4263510261983379143L;

	@Override
	protected Object run() {
		return new MainUI(user);
	}

}
