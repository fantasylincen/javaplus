package cn.vgame.a.account;

import javax.servlet.http.HttpSession;

public class CreateRoleEvent {

	private final Role role;
	private final HttpSession session;

	public CreateRoleEvent(Role role, HttpSession session) {
		this.role = role;
		this.session = session;
	}

	public Role getRole() {
		return role;
	}
	
	public HttpSession getSession() {
		return session;
	}
}
