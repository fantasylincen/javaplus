package cn.javaplus.crazy.login;

public class LoginSuccessEvent {

	private String token;
	private String uname;
	private String roleData;
	private LoginStage stage;

	public LoginSuccessEvent(LoginStage stage, String token, String uname, String roleData) {
		this.stage = stage;
		this.token = token;
		this.uname = uname;
		this.roleData = roleData;
	}

	public LoginStage getStage() {
		return stage;
	}
	
	public String getToken() {
		return token;
	}

	public String getUname() {
		return uname;
	}

	public String getRoleData() {
		return roleData;
	}

	public boolean isNewUser() {
		return roleData == null;
	}
}
