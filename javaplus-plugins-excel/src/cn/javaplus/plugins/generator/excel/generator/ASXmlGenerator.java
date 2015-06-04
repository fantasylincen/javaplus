package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.jface.preference.IPreferenceStore;

import _util.FileUtils;
import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.preferences.D;

public class ASXmlGenerator extends AbstractGenerator {

	private static FileUtils	fu		= new FileUtils();

	private static final String	KEY		= "content";
	private static final String	except	= "";				// 排除列表

	// private static final String SPECIAL_CLASSNAME = "Message";
	// private static final String SPECIAL_CLASS_FILE_NAME = "message.xml";

	public ASXmlGenerator() {
		super("baseClassConfig.txt");
	}

	@SuppressWarnings("unchecked")
	public void generate(Constent c) {

		if (!c.isClientNeed()) {
			return;
		}

		StringBuilder s = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r<root>\r\r");
		s.append("<!-- " + c.explain + " -->\r\r");

		String C_NAME = "";

		Document doc = XMLLoader.getDoc();
		Element e = doc.getRootElement().element("specialVoName");

		List<Element> es = e.elements();

		if (contains(es, c.className)) {
			C_NAME = getProperty(es, c.className);
			s.append("\t<classPath>cn.mxz.vo." + C_NAME + "</classPath>\r");
		} else if (containsClassPath(c.className)) {
			C_NAME = getClassPath(c.className);
			s.append("\t<classPath>" + C_NAME + "</classPath>\r");
		} else {
			C_NAME = c.className;
			s.append("\t<classPath>cn.mxz.vo." + C_NAME + "Vo</classPath>\r");
		}

		Element excel = doc.getRootElement();
		Element pfx = excel.element("voPrefix");

		String RES_PATH;

		String head = "res/images/";

		if (pfx != null) {
			Element ele = pfx.element("head");
			if (ele != null) {
				head = ele.getTextTrim();
			}
		}

		RES_PATH = head + c.className.toLowerCase() + "/";

		if (pfx != null && getPfx(pfx, c.className) != null) {
			String p = getPfx(pfx, c.className);
			// String p = pfx.getTextTrim();
			String picType = excel.element("voPicType").getTextTrim();
			s.append("\t<config prefix=\"" + p + "\" pictype=\"" + picType + "\" index=\"" + c.filedNames[0] + "\"/>\r");
		} else {

			s.append("\t<config prefix=\"" + RES_PATH + "\" pictype=\".jpg\" index=\"" + c.filedNames[0] + "\"/>\r");
		}

		if (contains(c.filedNames, KEY) && !except.equals(c.className)) {
			build1(c, s);
		} else {
			build2(c, s);
		}

		s.append("</root>");
		for (int i = 0; i < 1024; i++) {
			s.append(" ");
		}

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		String path = store.getString(D.Paths.AS_XML + "") + File.separator;

		String fname;
		fname = path + c.className + "Config" + ".xml";
		fu.write(fname, s.toString());
	}

	private String getClassPath(String className) {
		Document doc = XMLLoader.getDoc();
		@SuppressWarnings("unchecked")
		List<Element> es = doc.getRootElement().elements("classPath");
		for (Element element : es) {
			if (element.getName().equals("classPath")) {
				if (element.attributeValue("class").equals(className)) {
					return element.attributeValue("name");
				}
			}
		}

		return null;
	}

	private boolean containsClassPath(String className) {

		return getClassPath(className) != null;
	}

	@SuppressWarnings("unchecked")
	private String getPfx(Element pfx, String className) {

		List<Element> all = pfx.elements("class");
		for (Element element : all) {
			if (element.attributeValue("name").equals(className)) {
				return element.attributeValue("to");
			}
		}
		return null;
	}

	private String getProperty(List<Element> es, String className) {
		for (Element ea : es) {
			boolean equals = ea.attributeValue("base").equals(className);
			if (equals) {
				return ea.attributeValue("to");
			}
		}
		return null;
	}

	private boolean contains(List<Element> es, String className) {
		for (Element ea : es) {
			boolean equals = ea.attributeValue("base").equals(className);
			if (equals) {
				return true;
			}
		}
		return false;
	}

	private boolean contains(String[] filedNames, String string) {
		for (String s : filedNames) {
			if (s.equals(string)) {
				return true;
			}
		}
		return false;
	}

	private void build1(Constent c, StringBuilder s) {
		for (int i = 0; i < c.constent.length; i++) {
			String[] cc = c.constent[i];

			String t = "	<item ";

			String CONTENT_VALUE = "<![CDATA[CONTENT]]> ";

			int containIndex = -1;

			for (int j = 0; j < c.types.length; j++) {
				String value = cc[j];
				if (!c.marks[j].equals("1")) {// 不为1才生成
					if (c.filedNames[j].trim().equals(KEY)) {
						t += c.filedNames[j] + "=\"" + "" + "\" ";
						containIndex = j;
					} else {
						t += c.filedNames[j] + "=\"" + value + "\" ";
					}
				}
			}

			try {
				t += ">" + CONTENT_VALUE.replaceAll("CONTENT", cc[containIndex]);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			t += "</item>\r";

			s.append(t);
		}
	}

	private void build2(Constent c, StringBuilder s) {
		for (int i = 0; i < c.constent.length; i++) {
			String[] cc = c.constent[i];

			String t = "	<item ";
			for (int j = 0; j < c.types.length; j++) {
				String value = cc[j];
				if (!c.marks[j].equals("1")) {// 不为1才生成
					t += c.filedNames[j] + "=\"" + value + "\" ";
				}
			}
			t += "/>" + "\r";
			s.append(t);
		}
	}

}
