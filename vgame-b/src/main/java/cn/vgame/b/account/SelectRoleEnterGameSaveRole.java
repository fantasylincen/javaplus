package cn.vgame.b.account;

import javax.servlet.http.HttpSession;

import cn.javaplus.events.Listener;
import cn.vgame.b.Server;

public class SelectRoleEnterGameSaveRole implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {
		HttpSession session = e.getSession();

		Role role = e.getRole();
		session.setAttribute("roleId", role.getId());

		Server.put(role);
	}

}