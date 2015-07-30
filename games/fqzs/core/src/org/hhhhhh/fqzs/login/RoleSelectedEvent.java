package org.hhhhhh.fqzs.login;

import org.hhhhhh.fqzs.result.RoleData;

public class RoleSelectedEvent {

	private RoleData roleData;

	public RoleSelectedEvent(RoleData roleData) {
		this.roleData = roleData;
	}

	public RoleData getRoleData() {
		return roleData;
	}
}
