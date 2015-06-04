package cn.javaplus.plugins.generator.excel.generator;

import java.net.URL;
import java.util.Properties;

import cn.javaplus.util.Util;

import com.google.common.io.Resources;

public class Store {

	public static String getString(String key) {
		URL url = Store.class.getResource("config.properties");
		Properties p = Util.Property.getProperties(url);
		return p.getProperty(key);
	}

}
