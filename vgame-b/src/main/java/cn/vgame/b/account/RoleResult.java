package cn.vgame.b.account;

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