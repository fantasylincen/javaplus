package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;
import java.net.URL;

import cn.javaplus.plugins.generator.excel.preferences.D;
import cn.javaplus.util.Util;

public class JavaXmlGenerator extends AbstractGenerator {

	public JavaXmlGenerator() {
//		super("templetConfig.txt");

		URL url = JavaXmlGenerator.class.getResource("templetConfig.txt");
		this.temp = Util.File.getContent(url);
	}

	public void generate(Constent c) {

		StringBuilder s = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r<root>\r\r");
		// s.append("<!-- " + c.explain + " -->\r\r");

		for (int i = 0; i < c.constent.length; i++) {
			String[] cc = c.constent[i];

			String t = "	<item ";
			for (int j = 0; j < c.types.length; j++) {

				String value = cc[j];

				if (c.types[j].trim().equals("int")) {
					try {
						Integer.parseInt(value);
					} catch (NumberFormatException e) {
						throw new RuntimeException("" + c.className + "  ----  " + c.explains[j] + " --- 值为:[" + value + "]" + "  j = " + j + " i = " + i + " id = " + cc[0]);
					}
				}

				value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&", "&amp;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");

				t += c.filedNames[j] + "=\"" + value + "\" ";
			}

			t += "/>" + "\r";

			s.append(t);
		}

		s.append("</root>");
		for (int i = 0; i < 1024; i++) {
			s.append(" ");
		}

		String fname = Store.getString(D.Paths.JAVA_XML + "") + File.separator

		+ c.className + "Config" + ".xml";

		Util.File.write(fname, s.toString());
	}

}
