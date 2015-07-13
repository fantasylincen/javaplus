package cn.vgame.a.account;

import cn.javaplus.events.Listener;
import cn.vgame.a.Server;
import cn.vgame.a.system.Const;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;

public class CreateRoleSendCoin implements Listener<CreateRoleEvent> {

	@Override
	public void onEvent(CreateRoleEvent e) {
		Role role = e.getRole();

		Const c = Server.getConst();
		int coin = c.getInt("CREATE_ROLE_SEND_COIN");
		if (coin > 0)
			role.addCoin(coin);
		
		//减库存
		Controller ccc = Turntable.getInstance().getController();
		ccc.setKuCun(ccc.getKuCun() - coin);
	}

}
