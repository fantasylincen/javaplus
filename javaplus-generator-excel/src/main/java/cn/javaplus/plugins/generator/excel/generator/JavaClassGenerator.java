package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.javaplus.plugins.generator.excel.preferences.D;
import cn.javaplus.util.Util;

public class JavaClassGenerator extends AbstractGenerator {

	public JavaClassGenerator() {
//		super("templet.txt");
		URL url = JavaClassGenerator.class.getResource("templet.txt");
		this.temp = Util.File.getContent(url);
	}

	@SuppressWarnings("unchecked")
	public void generate(Constent c) {
		String temp = "//" + c.explain + "\r";

		String implement = "";

		Document doc = XMLLoader.getDoc();
		Element excel = doc.getRootElement();

		Element e = excel.element("implements");
		List<Element> es = e.elements("interface");
		for (Element in : es) {
			String interfaceName = in.attributeValue("name");
			List<Element> childs = in.elements("child");

			for (Element child : childs) {
				if (c.className.equals(child.attributeValue("name"))) {
					if (implement.isEmpty()) {
						implement = " implements " + interfaceName;
					} else {
						implement += "," + interfaceName;
					}
				}
			}
		}

		String extendss = "";

		Element ext = excel.element("extends");
		List<Element> classs = ext.elements("class");
		for (Element superClass : classs) {
			String classNameName = superClass.attributeValue("name");
			List<Element> childs = superClass.elements("child");

			for (Element child : childs) {
				if (c.className.equals(child.attributeValue("name"))) {
					if (extendss.isEmpty()) {
						extendss = " extends " + classNameName;
					} else {
						extendss += "," + classNameName;
					}
				}
			}
		}


		String v = Store.getString(D.Paths.PACKAGE_NAME + "");

		temp += super.temp.replace("PACKAGE_NAME", v).replace("CLASS_NAME", c.className + "Templet" + extendss + implement).replace("PROPERTIES", buildProperties(c)).replace("SETS_AND_GETS", buildGetterSetters(c));

		String fileName = Store.getString(D.Paths.JAVA_CODE + "") + File.separator + v.replace(".", File.separator) + File.separator + c.className + "Templet" + ".java";

		Util.File.write(fileName, temp);
	}


	private String				METHOD_TEMP	= "	/**\r" + "	 * EXPlAIN\r" + "	 */\r" + "	void setMETHOD_NAME(TYPE_NAME PROPERTY_NAME) {\r" + "		this.PROPERTY_NAME = PROPERTY_NAME;\r" + "	}\r\r" + "	/**\r" + "	 * EXPlAIN\r" + "	 */\r" + "	public TYPE_NAME getMETHOD_NAME() {\r" + "		return this.PROPERTY_NAME;\r" + "	}\r\r";

	private String buildGetterSetters(Constent c) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < c.filedNames.length; i++) {
			String f = c.filedNames[i];
			String t = c.types[i];

			if (f == null || f.isEmpty()) {
				throw new NullPointerException(c.className + " 字段名缺 index = " + i);
			}

			if (t == null || t.isEmpty()) {
				throw new NullPointerException(c.className + " 类型缺失! index = " + i);
			}

			sb.append(METHOD_TEMP.replaceAll("EXPlAIN", c.explains[i]).replaceAll("METHOD_NAME", Util.Str.firstToUpperCase(f)).replaceAll("TYPE_NAME", t).replaceAll("PROPERTY_NAME", f));
		}

		return sb.toString();
	}

	private String	PROPERTY_TEMP	= "	/**\r" + " 	 * EXPlAIN\r" + " 	 */\r" + "	private TYPE_NAME PROPERTY_NAME;\r";

	private String buildProperties(Constent c) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < c.filedNames.length; i++) {
			String f = c.filedNames[i];
			String t = c.types[i];
			sb.append(PROPERTY_TEMP.replaceAll("EXPlAIN", c.explains[i]).replaceAll("TYPE_NAME", t).replaceAll("PROPERTY_NAME", f));
		}

		return sb.toString();
	}
}
