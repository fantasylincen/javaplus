package cn.javaplus.chatrobot.extractor;

import cn.javaplus.util.Util;

/**
 * 一个参数, 这个参数有一个名字, 有一个用于匹配的正则表达式
 */
public class Arg {
	String name;
	String regex;

	public Arg(String argName, String regex) {
		name = Util.Str.toSBC(argName);
		this.regex = regex;
	}

	public String getName() {
		return name;
	}

	public String getRegex() {
		return regex;
	}
}
