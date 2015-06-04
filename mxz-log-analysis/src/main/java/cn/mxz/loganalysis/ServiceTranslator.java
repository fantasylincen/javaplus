package cn.mxz.loganalysis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ServiceTranslator {

	private static Properties p;

	public static String translate(String explain) {
		if (p == null) {
			p = new Properties();
			try {
				p.load(new FileReader("D:/workspace/MobileServer/res/build/translate.properties"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if (explain == null) {
			return "other";
		}
		String property = p.getProperty(explain);
		if (property == null) {
			return explain;
		}
		return property;
	}

}
