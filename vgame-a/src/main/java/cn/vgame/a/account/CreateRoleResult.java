package cn.vgame.a.account;

import javax.servlet.http.HttpSession;

public class CreateRoleResult {

	final Role role;
	private final HttpSession session;

	public CreateRoleResult(Role role, HttpSession session) {
		this.role = role;
		this.session = session;
	}

	public RoleResult getRole() {
		return new RoleResult(role, session);
	}

}
