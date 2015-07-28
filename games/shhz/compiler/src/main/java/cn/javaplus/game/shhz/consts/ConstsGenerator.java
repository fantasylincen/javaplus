package cn.javaplus.game.shhz.consts;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Set;

import cn.javaplus.game.shhz.Templet;
import cn.javaplus.shhz.string.StringPrinter;
import cn.javaplus.shhz.util.Closer;

import com.google.common.io.Resources;

public class ConstsGenerator {

	public void generate() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = Resources.getResource("D.properties").openStream();
			InputStreamReader r = new InputStreamReader(is, "utf8");
			prop.load(r);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(is);
		}
		Templet temp = new Templet("D.temp");
		temp.set("DEFINES", buildDefines(prop));
		temp.writeToFile("../core/gen/cn/javaplus/game/shhz/define/D.java");
	}

	private String buildDefines(Properties prop) {
		StringPrinter sp = new StringPrinter();
		Set<Object> ks = prop.keySet();
		for (Object o : ks) {
			String key = o.toString();
			String property = prop.getProperty(key);
			print(sp, key, property);
		}
		return sp.toString();
	}

	private void print(StringPrinter sp, String key, String property) {
		String[] split = property.split("\\|");
		String value = split[0].trim();
		String doc = split[1].trim();

		sp.println("	/**");
		sp.println("	 * " + doc);
		sp.println("	 */");
		sp.println("	 public static final " + buildType(value) + " " + key
				+ " = " + buildValue(value) + ";");

	}

	private String buildType(String value) {
		if (value.matches("[0-9]*\\.[0-9]+")) {
			return "double";
		}
		if (value.equals("true") || value.equals("false")) {
			return "boolean";
		}

		if (value.matches("[0-9]+")) {
			return "int";
		}

		return "String";
	}

	private String buildValue(String value) {
		if (value.matches("[0-9]*\\.[0-9]+") || value.matches("[0-9]+")) {
			return value;
		}

		if (value.equals("true")) {
			return "true";
		}

		if (value.equals("false")) {
			return "false";
		}

		return "\"" + value + "\"";
	}

}
