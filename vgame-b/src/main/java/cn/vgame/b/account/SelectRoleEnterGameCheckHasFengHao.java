package cn.vgame.b.account;

import cn.javaplus.events.Listener;
import cn.vgame.b.result.ErrorResult;

public class SelectRoleEnterGameCheckHasFengHao implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {

		Role role = e.getRole();

		if(role.hasFengHao())
			throw new ErrorResult(10014).toException();
	}

}
