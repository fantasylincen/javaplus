package cn.vgame.a.account;

import javax.servlet.http.HttpSession;

import cn.javaplus.events.Listener;

public class SelectRoleEnterGameSaveRoleZoneId implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {
		HttpSession session = e.getSession();

		Role role = e.getRole();
		String zoneId = (String) session.getAttribute("zoneId");
		role.setZoneId(zoneId);
	}

}