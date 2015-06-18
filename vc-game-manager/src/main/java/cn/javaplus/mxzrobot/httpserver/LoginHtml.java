package cn.javaplus.mxzrobot.httpserver;

import cn.javaplus.util.Util;

public class LoginHtml {

	public static String build(String text) {
		String content = Util.File.getContent("config/login.temp");
		return content.replaceAll("HOME_TEXT", text);
	}

}
