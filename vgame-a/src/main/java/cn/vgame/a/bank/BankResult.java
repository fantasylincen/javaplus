package cn.vgame.a.bank;

import cn.vgame.a.account.Role;


public class BankResult {

	private final Role role;

	public BankResult(Role role) {
		this.role = role;
	}

	public long getCoin() {
		return role.getCoin();
	}

	public long getBankCoin() {
		return role.getBankCoin();
	}

	
}
