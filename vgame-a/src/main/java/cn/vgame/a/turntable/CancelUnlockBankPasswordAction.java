package cn.vgame.a.turntable;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.message.SuccessResult;

/**
 * 取消强制解锁 
 * 
 * -----------------
 * 
 * A.正常情况: { bankPasswordCd:1, //解锁cd bankPasswordStatus:0, 密码状态 }
 * 
 * B.错误: 标准错误
 */
public class CancelUnlockBankPasswordAction extends
		JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4234121643459106742L;

	@Override
	protected Object run() {
		role.getBank().cancelLock();
		return new SuccessResult();
	}
}
