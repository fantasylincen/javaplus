package cn.vgame.a.bank;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;

/**
 * 取钱
 * -----------------
 * 
 * A.正常情况:
 * 		{
 * 			coin: 1, // 身上的金豆
 *          bankCoin:2// 银行中的金豆
 * 		}
 * 
 * B.错误:
 *  标准错误
 */
public class FromBankAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9144084313941092732L;
	private long coin;
	private String password;
	@Override
	public Object run() {
		role.fromBank(coin, password);
		return new BankResult(role);
	}

	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
