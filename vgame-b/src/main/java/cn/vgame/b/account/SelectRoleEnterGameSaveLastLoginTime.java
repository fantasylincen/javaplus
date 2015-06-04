package cn.vgame.b.account;

import cn.javaplus.events.Listener;
import cn.vgame.share.KeyValue;

public class SelectRoleEnterGameSaveLastLoginTime implements
		Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {

		Role role = e.getRole();

		KeyValue v = role.getKeyValueForever();
		v.set("LAST_LOGIN_TIME", System.currentTimeMillis());
	}

}
