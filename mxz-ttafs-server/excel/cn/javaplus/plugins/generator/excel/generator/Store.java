package cn.javaplus.plugins.generator.excel.generator;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Util;
import cn.mxz.base.MainArgs;

public class Store {

	public static String getString(String key) {
		String value = findInMainArgs(key);
		if(value != null) {
			return value;
		}
		
		IProperties p = Util.Property.getProperties("res/build/config.properties");
		return p.getProperty(key);
	}

//	JAVA_CODE=D:/workspace/MobileServer/gen
//	JAVA_XML=D:/workspace/MobileServer/res/properties
//	INTERFACE_CONFIG=D:/workspace/MobileServer/res/build/templet_interfaces.xml
	
	private static String findInMainArgs(String key) {

		String[] args = MainArgs.getArgs();
		if(args == null) {
			return null;
		}
		for (String v : args) {
			if(v.startsWith("-" + key + ":")) {
				return v.replaceAll("\\-" + key + ":", "");
			}
		}
		return null;
	}

}
