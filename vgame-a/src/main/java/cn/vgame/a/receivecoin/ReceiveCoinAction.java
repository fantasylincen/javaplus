package cn.vgame.a.receivecoin;

import cn.vgame.a.Server;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.account.Role;

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
		role.receiveCoin();
		return role;
	}
}
