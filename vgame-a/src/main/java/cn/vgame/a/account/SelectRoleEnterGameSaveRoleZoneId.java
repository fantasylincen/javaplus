package cn.vgame.a.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.javaplus.events.Listener;
import cn.javaplus.log.Log;

public class SelectRoleEnterGameSaveRoleZoneId implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {
		HttpSession session = e.getSession();
		HttpServletRequest request = e.getRequest();
		String remoteAddr = request.getRemoteAddr();

		Role role = e.getRole();
		String zoneId = (String) session.getAttribute("zoneId");
		String deviceId = (String) session.getAttribute("deviceId");
		role.setZoneId(zoneId);
		if(deviceId != null)
			role.getKeyValueSaveOnly().set("DEVICE_ID", deviceId);
		Log.d("deviceId", role.getId(), role.getNick(), deviceId);
		Log.d("ip", role.getId(), role.getNick(), remoteAddr);
	}

}