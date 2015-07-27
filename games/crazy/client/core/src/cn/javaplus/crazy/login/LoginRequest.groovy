package cn.javaplus.crazy.login;

import cn.javaplus.crazy.bucket.BaiduBucket;
import cn.javaplus.crazy.bucket.Properties;
import cn.javaplus.crazy.http.Parameters;
import cn.javaplus.crazy.http.Request;
import cn.javaplus.crazy.util.Util;

public class LoginRequest implements Request {

	private Parameters pars;

	/**
	 * @param uname
	 * @param pwd
	 *            明文
	 */
	public LoginRequest(String uname, String pwd) {
		pars = new Parameters();
		pars.put("pwdMd5", Util.Secure.md5(pwd));
		pars.put("uname", uname);
	}

	@Override
	public String getUrl() {
		String path = "login";
		return GateUrl.getGateUrl() + "/" + path;
	}

	@Override
	public Parameters getParameters() {
		return pars;
	}

}
