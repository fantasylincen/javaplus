package cn.mxz.city;

import cn.mxz.util.message.TextMessageImpl;

public class Messages {

	public static String getText(int code, Object... infos) {
		TextMessageImpl t = new TextMessageImpl();
		t.setCode(code);
		t.setInfo(infos);
		return t.toString();
	}

}
