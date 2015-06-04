package cn.javaplus.chatrobot.extractor;

import java.util.MissingResourceException;
import java.util.Properties;

import cn.javaplus.util.Util;

public class Messages {
	private static final String BUNDLE_NAME = "messages.properties";

	private static final Properties RESOURCE_BUNDLE = Util.Property.getProperties(Messages.class.getResource(BUNDLE_NAME));

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getProperty(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
