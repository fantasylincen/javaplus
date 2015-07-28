package cn.javaplus.crazy.enter;

public class EnterResultPtl implements EnterResultP {

	private String uname;
	private int roleId;

	public EnterResultPtl(int roleId, String uname) {
		this.roleId = roleId;
		this.uname = uname;
	}

	public String getUname() {
		return uname;
	}

	@Override
	public String getRoleId() {
		return roleId + "";
	}

}
