package cn.vgame.a.account;

import cn.javaplus.events.Listener;

public class SelectRoleEnterGameSaveLastLoginTime implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {

		Role role = e.getRole();

		role.getKeyValueForever().set("LAST_LOGIN_TIME",
				System.currentTimeMillis());
	}

}
