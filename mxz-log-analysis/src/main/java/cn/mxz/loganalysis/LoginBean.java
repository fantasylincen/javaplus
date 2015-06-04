package cn.mxz.loganalysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginBean {

	private String roleId;
	private String nick;
	private long time;
	private boolean isLogin;

	public LoginBean(String s) {
		Pattern pattern = Pattern.compile("\'.+\'");
		Matcher m = pattern.matcher(s);
		m.find();
		String group = m.group();
		String[] ss = group.split(",");
		roleId = ss[1].replaceAll("'", "");
		nick = ss[2].replaceAll("'", "");
		time = new Long(ss[3].replaceAll("'", "").trim());
		isLogin = ss[4].replaceAll("'", "").trim().equals("1");
	}

	public String getRoleId() {
		return roleId;
	}
	
	public String getNick() {
		return nick.trim();
	}
	
	public long getTime() {
		return (time - 8 * 3600) * 1000;
	}
	
	public boolean isLogin() {
		return isLogin;
	}
}
