package cn.javaplus.mxzrobot.define;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;

public class ConfigProperties {

	private static IProperties properties;

	static {
		properties = Util.Property.getProperties("config/config.properties");
	}

	public static int getInt(String key) {
		return new Integer(getString(key));
	}

	public static String getString(String key) {
		return properties.getProperty(key).trim();
	}

}
