package cn.vgame.a.turntable;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.account.RoleResult;

/**
 * 获取玩家数据
 * -----------------
 * 
 * A.正常情况:
 * 		{
 * 				id 角色ID,
 * 				ownerId 所属帐号,
 * 				nick 昵称,
 * 				coin 金币数量,
 * 				cd 开奖CD时间(秒)
 * 
 * 				coinStatus : {
 * 					boolean canReceive 是否可以领取
 * 					int cd 剩余时间秒
 * 					long coin 可领金币数量
 * 				}
 * 		}
 * 
 * B.错误:
 *  标准错误  10007
 */
public class GetUserDataAction extends JsonActionAfterRoleEnterGame {

	private static final long serialVersionUID = -6099859675509539457L;

	@Override
	public Object run() {
		return new RoleResult(role, session);
	}

}
