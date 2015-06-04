package cn.vgame.a.bank;

import cn.vgame.a.account.JsonActionAfterRoleEnterGame;

/**
 * 赠送金豆
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
public class SendCoinAction extends JsonActionAfterRoleEnterGame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5219849014783284866L;
	private long coin;
	private String roleId;
	
	private String password;
	
	@Override
	public Object run() {
		role.sendCoin(roleId, coin, password);
		return new BankResult(role);
	}

	public long getCoin() {
		return coin;
	}

	public void setCoin(long coin) {
		this.coin = coin;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
