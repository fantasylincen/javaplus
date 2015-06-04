package cn.vgame.a.turntable;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.bank.Bank;
import cn.vgame.a.message.SuccessResult;

/**
 * 强制解锁, 需要N小时等待时间
 * 
 * -----------------
 * 
 * A.正常情况: { }
 * 
 * B.错误: 标准错误
 */
public class UnlockBankPasswordAction extends JsonActionAfterRoleEnterGame {

	public class UnlockSuccessResult {
		public int getCd() {
			return role.getBankPasswordCd();
		}
		private boolean isSuccess = true;

		public boolean isSuccess() {
			return isSuccess;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4234121643459106742L;

	@Override
	protected Object run() {
		Bank bank = role.getBank();
		bank.unlock();
		return new UnlockSuccessResult();
	}
}
