package cn.vgame.a.account;

import cn.javaplus.events.Listener;
import cn.vgame.a.Server;
import cn.vgame.a.bag.Bag;
import cn.vgame.a.system.Const;

public class CreateRoleSendLaba implements Listener<CreateRoleEvent> {

	@Override
	public void onEvent(CreateRoleEvent e) {
		Role role = e.getRole();
		Bag bag = role.getBag();

		Const c = Server.getConst();
		int labaCount = c.getInt("CREATE_ROLE_SEND_LABA");
		if (labaCount > 0)
			bag.add(10001, labaCount);
	}

}
