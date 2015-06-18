package cn.javaplus.mxzrobot.define;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;


public class D {
	static final IProperties properties = Util.Property.getProperties("config/properties.properties");

	public static final String COMMAND_HEAD = properties.getProperty("COMMAND_HEAD");

	public static String get(String key) {
		return properties.getProperty(key);
	}

}
