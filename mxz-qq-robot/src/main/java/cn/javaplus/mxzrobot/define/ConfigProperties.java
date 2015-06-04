package cn.javaplus.mxzrobot.define;

import java.util.Properties;

import com.google.common.io.Resources;

import cn.javaplus.util.Util;

public class ConfigProperties {

	private static Properties properties;

	static {
		properties = Util.Property.getProperties(Resources
				.getResource("config.properties"));
	}

	public static int getInt(String key) {
		return new Integer(getString(key));
	}

	public static String getString(String key) {
		return properties.getProperty(key).trim();
	}

}
