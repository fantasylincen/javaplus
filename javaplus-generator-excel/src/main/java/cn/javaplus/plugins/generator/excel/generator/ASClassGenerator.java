//package cn.javaplus.plugins.generator.excel.generator;
//
//import java.io.File;
//import java.util.List;
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//
//import cn.javaplus.plugins.generator.excel.preferences.D;
//import cn.javaplus.util.Util;
//
//public class ASClassGenerator extends AbstractGenerator {
//
//
//	public ASClassGenerator() {
//		super("asClassTemp.txt");
//	}
//
//	public void generate(Constent c) {
//
//		if (!c.isClientNeed()) {
//			return;
//		}
//
//		String temp = "//" + c.explain + "\r";
//
//		String h = "Vo";
//
//
//		String v = Store.getString(D.Paths.PACKAGE_NAME + "");
//
//		temp += super.temp.replace("PACKAGE_NAME", v).replace("CLASS_NAME", c.className + h).replace("PROPERTIES", buildProperties(c)).replace("OTHER_IMPLEMENTS", buildExtends(c)).replace("SETS_AND_GETS", buildGetterSetters(c));
//
//		String string = Store.getString(D.Paths.PACKAGE_NAME + "").replace(".", File.separator) + File.separator;
//
//		String fileName = Store.getString(D.Paths.AS_CODE + "") + File.separator + string + "vo" + File.separator + c.className + h + ".as";
//
//		Util.File.write(fileName, temp);
//
//		Debuger.debug("generate xxxVo.as:" + fileName);
//
//		Debuger.debug("Generate AS vo:" + fileName);
//	}
//
//	private String buildExtends(Constent c) {
//
//		Document doc = XMLLoader.getDoc();
//		Element excel = doc.getRootElement();
//
//		Element e = excel.element("asimplements");
//		if (e != null) {
//			return buildExtends(e, c);
//		} else {
//			return "";
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	private String buildExtends(Element e, Constent c) {
//
//		List<Element> es = e.elements("interface");
//
//		String implement = "";
//
//		for (Element in : es) {
//			String interfaceName = in.attributeValue("name");
//			List<Element> childs = in.elements("child");
//
//			for (Element child : childs) {
//				if (c.className.equals(child.attributeValue("name"))) {
//					// if(implement.isEmpty()) {
//					// implement = " implements " + interfaceName;
//					// } else {
//					implement += "," + interfaceName;
//					// }
//				}
//			}
//		}
//		return implement;
//	}
//
//	private String	METHOD_TEMP	= "		/**\r" + "		 * EXPlAIN\r" + "		 */\r" + "		public function set METHOD_NAME(PROPERTY_NAME:TYPE_NAME):void {\r" + "			this._PROPERTY_NAME = PROPERTY_NAME;\r" + "		}\r\r" + "		/**\r" + "		 * EXPlAIN\r" + "		 */\r" + "		public function get METHOD_NAME():TYPE_NAME {\r" + "			return this._PROPERTY_NAME;\r" + "		}\r\r";
//
//	private String buildGetterSetters(Constent c) {
//		StringBuilder sb = new StringBuilder();
//
//		for (int i = 0; i < c.filedNames.length; i++) {
//			if (c.marks[i].equals("1")) {// 不为1才生
//				continue;
//			}
//			String f = c.filedNames[i];
//			String t = c.types[i];
//			t = parse(t);
//			sb.append(METHOD_TEMP.replaceAll("EXPlAIN", c.explains[i]).replaceAll("METHOD_NAME", f).replaceAll("TYPE_NAME", t).replaceAll("PROPERTY_NAME", f));
//		}
//
//		return sb.toString();
//	}
//
//	public static String parse(String t) {
//		if (t.equals("byte"))
//			return "int";
//
//		if (t.equals("short"))
//			return "int";
//
//		if (t.equals("long"))
//			return "Number";
//
//		if (t.equals("char"))
//			return "int";
//
//		if (t.equals("float"))
//			return "Number";
//
//		if (t.equals("double"))
//			return "Number";
//
//		if (t.equals("boolean")) {
//			t = "Boolean";
//		}
//		return t;
//	}
//
//	private String	PROPERTY_TEMP	= "		/**\r" + "		 * EXPlAIN\r" + "		 */\r" + "		private var _PROPERTY_NAME:TYPE_NAME;\r";
//
//	private String buildProperties(Constent c) {
//		StringBuilder sb = new StringBuilder();
//
//		for (int i = 0; i < c.filedNames.length; i++) {
//			if (c.marks[i].equals("1")) {// 不为1才生
//				continue;
//			}
//			String f = c.filedNames[i];
//			String t = c.types[i];
//			t = parse(t);
//			sb.append(PROPERTY_TEMP.replaceAll("EXPlAIN", c.explains[i]).replaceAll("TYPE_NAME", t).replaceAll("PROPERTY_NAME", f));
//		}
//
//		return sb.toString();
//	}
//
//}
