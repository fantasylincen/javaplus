package org.hhhhhh.fqzs.login;

public class LoginEvent {

	private String id;
	private String token;

	public LoginEvent(String id, String token) {
		this.id = id;
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public String getToken() {
		return token;
	}
}
