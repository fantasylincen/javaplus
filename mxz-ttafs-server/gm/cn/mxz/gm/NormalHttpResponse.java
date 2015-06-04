package cn.mxz.gm;

import cn.javaplus.util.Util;

public class NormalHttpResponse {

	private static String content;

	public NormalHttpResponse() {
		if (content == null) {
			content = Util.File.getContent("res/http/normalHttpResponse");
		}
	}

	public String setValue(int value) {
		String v = new String(content);
		return v.replaceAll("VALUE", value + "");
	}

}
