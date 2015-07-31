package cn.vgame.a.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SelectRoleEnterGameEvent {

	private final Role role;
	private final HttpSession session;
	private final HttpServletRequest request;

	public SelectRoleEnterGameEvent(Role role, HttpSession session,
			HttpServletRequest request) {
		this.role = role;
		this.session = session;
		this.request = request;
	}

	public Role getRole() {
		return role;
	}

	public HttpSession getSession() {
		return session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

}
