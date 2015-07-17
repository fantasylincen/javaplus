package cn.vgame.a.login;

import cn.javaplus.events.Listener;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.account.SelectRoleEnterGameEvent;
import cn.vgame.share.KeyValue;

public class EnterServerSendLaba implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {
		Role role = e.getRole();

		if (isSend(role)) {
			return;
		}
		
		sendLaba(role);
	}

	private boolean isSend(Role role) {
		KeyValue kv = role.getKeyValueDaily();
		return kv.getBoolean("SEND_LABA");
	}

	private void sendLaba(Role role) {
		int count = Server.getConst().getInt("DAY_SEND_LABA");
		role.getBag().add(10001, count);
		
		markSendToday(role);
	}

	private void markSendToday(Role role) {
		KeyValue kv = role.getKeyValueDaily();
		kv.set("SEND_LABA", true);
	}

}
