package cn.javaplus.mxzrobot.httpserver;

import java.util.Map;

import cn.javaplus.file.IProperties;
import cn.javaplus.log.Log;
import cn.javaplus.mxzrobot.token.TokenManager;
import cn.javaplus.util.Util;

public class LoginHandler extends AbstractHandler {

	@Override
	protected String doGet(Map<String, Object> parameters) {
		String uname = parameters.get("uname") + "";
		String password = parameters.get("password") + "";
		Log.d("uname:" + uname);
		Log.d("uname:" + password);
		
		if (isRight(uname, password)) {
			return MessageHtml.build("帮助", "登陆成功!", uname, TokenManager.createToken(uname));
		}
		return LoginHtml.build("<span style=\"color:red\">uname or pwd error.</span>");
	}

	private boolean isRight(String uname, String password) {
		IProperties p = Util.Property.getProperties("config/users.properties");
		Object pwd = p.get(uname);
		if (pwd == null)
			return false;
		if (pwd.equals(""))
			return false;
		return (pwd + "").equals(password);
	}

}
