package cn.vgame.a.bank;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;

/**
 * 存钱
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
public class ToBankAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6962625153412072072L;
	private long coin;
	
	@Override
	public Object run() {
		role.toBank(coin);
		return new BankResult(role);
	}

	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

}
