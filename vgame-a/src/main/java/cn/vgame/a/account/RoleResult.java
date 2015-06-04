package cn.vgame.a.account;

import cn.vgame.a.receivecoin.CoinStatus;

public class RoleResult {

	/**
	 * 
	 */
	private final Role role;

	/**
	 * @param createRoleResult
	 */
	RoleResult(Role r) {
		role = r;
	}

	public long getCreateTime() {
		return role.getCreateTime();
	}

	public long getLastLoginTime() {
		return role.getLastLoginTime();
	}

	public String getId() {
		return role.getId();
	}

	public String getOwnerId() {
		return role.getOwnerId();
	}

	public String getNick() {
		return role.getNick();
	}

	public long getCoin() {
		return role.getCoin();
	}

	public long getLaBa() {
		return role.getLaBa();
	}

	public long getBankCoin() {
		return role.getBankCoin();
	}

	public long getCd() {
		return role.getCd();
	}

	public int getBankPasswordStatus() {
		return role.getBankPasswordStatus();
	}

	public int getBankPasswordCd() {
		return role.getBankPasswordCd();
	}

	public String getBankPassword() {
		return role.getBankPassword();
	}

	public CoinStatus getCoinStatus() {
		return role.getCoinStatus();
	}

	public boolean hasJinYan() {
		return role.hasJinYan();
	}

	public boolean hasFengHao() {
		return role.hasFengHao();
	}

	public boolean isRobot() {
		return role.isRobot();
	}

	public long getCoinAll() {
		return role.getCoinAll();
	}

}