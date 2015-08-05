package cn.javaplus.crazy.handlers;

import cn.javaplus.crazy.mongo.RoleDto;
import cn.javaplus.crazy.role.RoleData;

public class RoleDataAdaptor implements RoleData {

	private RoleDto role;

	public RoleDataAdaptor(RoleDto role) {
		this.role = role;
	}

	@Override
	public int getRoleId() {
		return role.getId();
	}

	@Override
	public String getUname() {
		return role.getUname();
	}

	@Override
	public String getNick() {
		return role.getNick();
	}

}
