package cn.javaplus.mxzrobot.httpserver;

import cn.javaplus.util.Util;

public class MessageHtml {

	public static String build(String heSay, String string, String uname, String token) {
		String content = Util.File.getContent("config/index.temp");
		string = string.replaceAll("\\$", "\\\\\\$");
		content = content.replaceAll("BODY_CONTENT", string);
		content = content.replaceAll("HELP_CONTENT", new HelpContent().getValue());
		content = content.replaceAll("INPUT_BOX_VALUE", heSay);
		content = content.replaceAll("TOKEN_VALUE", token);
		content = content.replaceAll("USER_NAME_VALUE", uname);
		content = content.replaceAll("\r\n", "<br>");
		return content;
	}

}
