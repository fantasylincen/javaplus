package cn.javaplus.crazy.login;

import cn.javaplus.crazy.http.JsonResult;

public class CreateRoleCallBack extends AbstractCallBack {

	private LoginSuccessEvent e;
	private EnterGameServer es;

	public CreateRoleCallBack(EnterGameServer es, LoginSuccessEvent e) {
		this.es = es;
		this.e = e;
	}

	@Override
	public void completed(JsonResult result) {
		String roleData = result.getString("roleData");
		es.enterGameServer(e, roleData);
	}

	@Override
	public void failed(String error) {
		super.failed(error);
	}
}