package cn.javaplus.crazy.login;

import cn.javaplus.crazy.bucket.BaiduBucket;
import cn.javaplus.crazy.bucket.Properties;
import cn.javaplus.crazy.http.Parameters;
import cn.javaplus.crazy.http.Request;

public class CreateRoleRequest implements Request {

	private Parameters pars;

	// /**
	// * in:
	// * uname
	// * token
	// * nick
	// * personId
	// *
	// * out-1:
	// * error
	// *
	// * out-2:
	// * roleId
	// */
	public CreateRoleRequest(String uname, String token, String nick,
	int personId) {
		pars = new Parameters();
		pars.put("uname", uname);
		pars.put("token", token);
		pars.put("nick", nick);
		pars.put("personId", personId + "");
	}

	@Override
	public String getUrl() {
		String gateUrl = GateUrl.getGateUrl();
		def name = gateUrl + "/createRole"
		return name;
	}

	@Override
	public Parameters getParameters() {
		return pars;
	}

}
