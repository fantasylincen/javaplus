package cn.javaplus.crazy.login;

import cn.javaplus.crazy.http.Parameters;
import cn.javaplus.crazy.http.Request;

public class OneKeyRegistRequest implements Request {

	@Override
	public String getUrl() {
		return GateUrl.getGateUrl() + "/oneKeyRegist";
	}

	@Override
	public Parameters getParameters() {
		Parameters p = new Parameters();
		return p;
	}

}
