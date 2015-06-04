package cn.vgame.a.account;

public class CreateRoleResult {

	final Role role;

	public CreateRoleResult(Role role) {
		this.role = role;
	}

	public RoleResult getRole() {
		return new RoleResult(role);
	}

}
