package cn.javaplus.chatrobot.define;

import java.util.Properties;

import cn.javaplus.util.Util;

import com.google.common.io.Resources;

public class D {

	static final Properties properties = Util.Property.getProperties(Resources.getResource("properties.properties"));

	public static final String COMMAND_HEAD = properties.getProperty("COMMAND_HEAD");

	public static String get(String key) {
		return properties.getProperty(key);
	}

}
