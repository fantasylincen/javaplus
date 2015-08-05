package cn.javaplus.game.shhz.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.Set;

import cn.javaplus.game.shhz.Templet;
import cn.javaplus.shhz.string.StringPrinter;
import cn.javaplus.shhz.util.Closer;

import com.google.common.io.Resources;

public class MessageGenerator {

	public void generate() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = Resources.getResource("S.properties").openStream();
			InputStreamReader r = new InputStreamReader(is, "utf8");
			prop.load(r);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			Closer.close(is);
		}
		Templet temp = new Templet("S.temp");
		temp.set("MESSAGES", buildMessages(prop));
		temp.writeToFile("../core/gen/cn/javaplus/game/shhz/message/S.java");
	}

	private String buildMessages(Properties prop) {
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

		sp.println("	/**");
		sp.println("	 * " + property);
		sp.println("	 */");
		String string = "	 public static final String S{0} = \"{1}\";";
		sp.println(MessageFormat.format(string, key, property));

	}
}
