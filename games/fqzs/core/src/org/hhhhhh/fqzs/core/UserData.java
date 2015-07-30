package org.hhhhhh.fqzs.core;

public class UserData {

	private String jsessionid;
	private String userId;
	private String roleId;

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	public void setUserId(String id) {
		this.userId = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;

	}

	public String getRoleId() {
		return roleId;
	}
}
