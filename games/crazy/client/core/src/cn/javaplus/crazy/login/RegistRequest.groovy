package cn.javaplus.crazy.login;

import cn.javaplus.crazy.bucket.BaiduBucket;
import cn.javaplus.crazy.bucket.Properties;
import cn.javaplus.crazy.http.Parameters;
import cn.javaplus.crazy.http.Request;
import cn.javaplus.crazy.util.Util;

public class RegistRequest implements Request {

	private String uname;
	private String password;

	/**
	 * @param uname
	 * @param password
	 *            明文
	 */
	public RegistRequest(String uname, String password) {
		this.uname = uname;
		this.password = password;
	}

	@Override
	public String getUrl() {
		String path = "regist";
		return GateUrl.getGateUrl() + "/" + path;
	}

	@Override
	public Parameters getParameters() {
		Parameters ps = new Parameters();
		ps.put("uname", uname);
		ps.put("password", Util.Secure.md5(password));
		return ps;
	}

}
