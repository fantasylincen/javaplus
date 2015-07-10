package cn.vgame.a.receivecoin;

import cn.vgame.a.Server;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.account.Role;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.a.turntable.Turntable.Controller;

/**
 * 领取金币 -----------------
 * 
 * A.正常情况: 跟 "获取玩家数据" 返回值一样
 * 
 * B.错误: 标准错误
 */
public class ReceiveCoinAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2228099610246931113L;

	@Override
	public Object run() {
		Role role = Server.getRole(session);
		long old = role.getCoin();
		role.receiveCoin();
		long now = role.getCoin();
		
		long receive = now - old;//玩家领取了的金币
		
		Turntable ins = Turntable.getInstance();
		Controller c = ins.getController();
		c.setKuCun(c.getKuCun()  - receive);
		
		return role;
	}
}
