package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;

import _util.FileUtils;
import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.preferences.D;

public class JavaXmlGenerator extends AbstractGenerator {

	private static FileUtils	fu	= new FileUtils();

	public JavaXmlGenerator() {
		super("baseClassConfig.txt");
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
						throw new RuntimeException("" + c.className + "表报错了      字段名：" + c.explains[j] + "     值：" + value);
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

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		String fname = store.getString(D.Paths.JAVA_XML + "") + File.separator

		+ c.className + "Config" + ".xml";

		fu.write(fname, s.toString());
	}

}
