package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;
import java.net.URL;

import cn.javaplus.plugins.generator.excel.preferences.D;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class JavaConfigClassGenerator extends AbstractGenerator {

	public JavaConfigClassGenerator() {
//		super("templetConfig.txt");
		URL url = JavaConfigClassGenerator.class.getResource("templetConfig.txt");
		this.temp = Util.File.getContent(url);
	}

	public void generate(Constent c) {
		String puts = "";

		for (int j = 0; j < c.types.length; j++) {
			puts += "\t\tx.set" + Util.Str.firstToUpperCase(c.filedNames[j]) + "( " + parse(c.types[j], c.filedNames[j]) + " );\r";
		}

		String temp = "//" + c.explain + "\r";

		String value = Store.getString(D.Paths.PACKAGE_NAME + "");

		temp += super.temp.

		replaceAll("PACKAGE_NAME", value).

		replaceAll("PARSE_SETS", puts).

		replaceAll("FINDERS", buildFinders(c)).

		replaceAll("GETARRAYS", buildGetArrays(c)).

		replaceAll("GETLISTS", buildGetLists(c)).

		replaceAll("CLASS_NAME", c.className + "Templet").

		replaceAll("CLASS_BASE_NAME", c.className).

		replaceAll("KEY_TYPE", parseToKeyType(c.types[0])).

		replaceAll("KEY_NAME", Util.Str.firstToUpperCase(c.filedNames[0]));

		String configName = Store.getString(D.Paths.JAVA_CODE + "") + File.separator

		+ Store.getString(D.Paths.PACKAGE_NAME + "").replace(".", File.separator)

		+ File.separator + c.className + "TempletConfig"

		+ ".java";

		Util.File.write(configName, temp);
	}

	private String buildFinders(Constent c) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < c.types.length; i++) {
			String f = c.filedNames[i];
			String t = c.types[i];

			sb.append(buildFinder(f, t) + "\r");
		}

		return sb + "";
	}


	private String buildGetArrays(Constent c) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < c.types.length; i++) {
			String f = c.filedNames[i];
			String t = c.types[i];

			sb.append(buildGetArray(f, t) + "\r");
		}

		return sb + "";
	}

	private String buildGetLists(Constent c) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < c.types.length; i++) {
			String f = c.filedNames[i];
			String t = c.types[i];

			sb.append(buildGetList(f, t) + "\r");
		}

		return sb + "";
	}

	private String buildGetArray(String f, String t) {

		StringPrinter p = new StringPrinter();
		p.println("	public static FILED_TYPE[] getArrayByFILED_NAME_UPPER_FIRST() {");
		p.println("		FILED_TYPE[] all = new FILED_TYPE[map.size()];");
		p.println("		for (int i = 0; i < keys.size(); i++) {");
		p.println("			CLASS_NAME f = get(keys.get(i));");
		p.println("			all[i] = f.getFILED_NAME_UPPER_FIRST();");
		p.println("		}");
		p.println("		return all;");
		p.println("	}");

		String up = Util.Str.firstToUpperCase(f);

		return p.toString().replaceAll("FILED_NAME_UPPER_FIRST", up).replaceAll("FILED_TYPE", t);
	}

	private String buildGetList(String f, String t) {

		StringPrinter p = new StringPrinter();
		p.println("	public static List<FILED_TYPE> getListByFILED_NAME_UPPER_FIRST() {");
		p.println("		List<FILED_TYPE> all = new ArrayList<FILED_TYPE>();");
		p.println("		for (CLASS_NAME f : getAll()) {");
		p.println("			all.add(f.getFILED_NAME_UPPER_FIRST());");
		p.println("		}");
		p.println("		return all;");
		p.println("	}");

		String up = Util.Str.firstToUpperCase(f);

		return p.toString().replaceAll("FILED_NAME_UPPER_FIRST", up).replaceAll("FILED_TYPE", parseToKeyType(t));
	}

	private String buildFinder(String f, String t) {

		StringPrinter p = new StringPrinter();
		p.println("	public static List<CLASS_NAME> findByFILED_NAME_UPPER_FIRST(FILED_TYPE value) {");
		p.println("		List<CLASS_NAME> all = new ArrayList<CLASS_NAME>();");
		p.println("		for (CLASS_NAME f : getAll()) {");
		p.println("			if(equals(f.getFILED_NAME_UPPER_FIRST(), value)) {");
		p.println("				all.add(f);");
		p.println("			}");
		p.println("		}");
		p.println("		return all;");
		p.println("	}");

		String up = Util.Str.firstToUpperCase(f);

		return p.toString().replaceAll("FILED_NAME_UPPER_FIRST", up).replaceAll("FILED_TYPE", t);
	}

	/**
	 * 转换成包装类
	 *
	 * @param string
	 * @return
	 */
	private String parseToKeyType(String type) {
		if (type.equals("int")) {
			return "Integer";
		} else if (type.equals("short")) {
			return "Short";
		} else if (type.equals("float")) {
			return "Float";
		} else if (type.equals("String")) {
			return "String";
		} else if (type.equals("double")) {
			return "Double";
		} else if (type.equals("byte")) {
			return "Byte";
		} else if (type.equals("char")) {
			return "Char";
		} else if (type.equals("boolean")) {
			return "Boolean";
		} else if (type.equals("long")) {
			return "Long";
		} else {
			return "???";
		}
	}

	private String parse(String type, String attributeName) {
		if (type.equals("int")) {
			return "Integer.decode( e.attributeValue(\"" + attributeName + "\").trim() )";
		} else if (type.equals("short")) {
			return "Short.parseShort( e.attributeValue(\"" + attributeName + "\").trim() )";
		} else if (type.equals("float")) {
			return "Float.parseFloat( e.attributeValue(\"" + attributeName + "\").trim() )";
		} else if (type.equals("String")) {
			return "e.attributeValue(\"" + attributeName + "\")";
		} else if (type.equals("double")) {
			return "Double.parseDouble( e.attributeValue(\"" + attributeName + "\").trim() )";
		} else if (type.equals("byte")) {
			return "Byte.parseByte( e.attributeValue(\"" + attributeName + "\").trim() )";
		} else if (type.equals("char")) {
			return "(char)" + "Integer.parseInt( e.attributeValue(\"" + attributeName + "\") )";
		} else if (type.equals("boolean")) {
			return "Boolean.parseBoolean( e.attributeValue(\"" + attributeName + "\").trim() )";
		} else if (type.equals("long")) {
			return "Long.parseLong( e.attributeValue(\"" + attributeName + "\").trim() )";
		} else {
			return "???";
		}
	}
}
