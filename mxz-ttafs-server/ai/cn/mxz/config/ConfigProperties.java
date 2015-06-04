package cn.mxz.config;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;

public class ConfigProperties {

	public static final String PATH = "config/config.properties";
	private static IProperties p;

	public static String getString(String properties) {
		if (p == null) {
			p = Util.Property.getProperties(PATH);
		}
		return p.getProperty(properties);
	}

}
