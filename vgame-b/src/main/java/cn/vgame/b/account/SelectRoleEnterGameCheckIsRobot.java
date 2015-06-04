package cn.vgame.b.account;

import cn.javaplus.events.Listener;
import cn.vgame.b.result.ErrorResult;

public class SelectRoleEnterGameCheckIsRobot implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {

		Role role = e.getRole();

		if(role.isRobot())
			throw new ErrorResult(10016).toException();
	}

}
