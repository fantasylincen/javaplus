package cn.javaplus.mxzrobot.httpserver;

import java.util.Map;

public class IndexHandler extends AbstractHandler {

	@Override
	protected String doGet(Map<String, Object> parameters) {
		return LoginHtml.build("Please login.");
	}

}
