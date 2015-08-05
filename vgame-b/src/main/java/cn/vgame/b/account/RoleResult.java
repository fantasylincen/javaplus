package cn.vgame.b.account;

import javax.servlet.http.HttpSession;

public class RoleResult {

	/**
	 * 
	 */
	private final Role role;
	private final HttpSession session;

	/**
	 * @param session 
	 * @param createRoleResult
	 */
	public RoleResult(Role r, HttpSession session) {
		role = r;
		this.session = session;
	}

	public long getRechargeHistory() {
		return role.getRechargeHistory();
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
