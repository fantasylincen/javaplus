package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;

import _util.FileUtils;
import _util.StringUtil;
import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.preferences.D;
import cn.javaplus.plugins.util.StringUtils;

public class JavaConfigClassGenerator extends AbstractGenerator {

	private static FileUtils	fu	= new FileUtils();

	public JavaConfigClassGenerator() {
		super("baseClassConfig2.txt");
	}

	public void generate(Constent c) {
		String puts = "";

		for (int j = 0; j < c.types.length; j++) {
			puts += "\t\tx.set" + StringUtils.firstToUpperCase(c.filedNames[j]) + "( " + parse(c.types[j], c.filedNames[j]) + " );\r";
		}

		String temp = "//" + c.explain + "\r";

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		String value = store.getString(D.Paths.PACKAGE_NAME + "");

		temp += super.temp.

		replaceAll("PACKAGE_NAME", value).

		replaceAll("PARSE_SETS", puts).

		replaceAll("FINDERS", buildFinders(c)).

		replaceAll("GETARRAYS", buildGetArrays(c)).

		replaceAll("GETLISTS", buildGetLists(c)).

		replaceAll("CLASS_NAME", c.className + "Templet").

		replaceAll("CLASS_BASE_NAME", c.className).

		replaceAll("KEY_TYPE", parseToKeyType(c.types[0])).

		replaceAll("KEY_NAME", StringUtils.firstToUpperCase(c.filedNames[0]));

		String configName = store.getString(D.Paths.JAVA_CODE + "") + File.separator

		+ store.getString(D.Paths.PACKAGE_NAME + "").replace(".", File.separator)

		+ File.separator + c.className + "TempletConfig"

		+ ".java";

		fu.write(configName, temp);
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

	private String buildGetArray(String f, String t) {

		String temp = "	public static FILED_TYPE[] getArrayByFILED_NAME_UPPER_FIRST() {\r" + "\r" + "		FILED_TYPE[] all = new FILED_TYPE[map.size()];\r" + "		\r" + "		for (int i = 0; i < keys.size(); i++) {\r" + "\r" + "			CLASS_NAME f = get(keys.get(i));\r" + "\r" + "			all[i] = f.getFILED_NAME_UPPER_FIRST();\r" + "\r" + "		}\r" + "		return all;\r" + "	}\r";

		return temp.replaceAll("FILED_NAME_UPPER_FIRST", StringUtil.firstToUpperCase(f)).replaceAll("FILED_TYPE", t);
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

	private String buildGetList(String f, String t) {

		String temp = "	public static List<FILED_TYPE> getListByFILED_NAME_UPPER_FIRST() {\r" + "\r" + "		List<FILED_TYPE> all = new ArrayList<FILED_TYPE>();\r" + "		\r" + "		for (int i = 0; i < keys.size(); i++) {\r" + "\r" + "			CLASS_NAME f = get(keys.get(i));\r" + "\r" + "			all.add(f.getFILED_NAME_UPPER_FIRST());\r" + "\r" + "		}\r" + "		return all;\r" + "	}\r";

		return temp.replaceAll("FILED_NAME_UPPER_FIRST", StringUtil.firstToUpperCase(f)).replaceAll("FILED_TYPE", parseToKeyType(t));
	}

	// public static List<String> getListByDescription() {
	//
	//
	// List<String> all = new ArrayList<String>();
	//
	// for (int i = 0; i < all.size(); i++) {
	//
	// EquipmentTemplet f = get(keys.get(i));
	//
	// all.add(f.getDescription());
	//
	// }
	// return all;
	// }

	private String buildFinder(String f, String t) {

		String temp = "	public static List<CLASS_NAME> findByFILED_NAME_UPPER_FIRST(FILED_TYPE value) {\r" + "\r" + "		List<CLASS_NAME> all = new ArrayList<CLASS_NAME>();\r" + "\r" + "		for (CLASS_NAME f : map.values()) {\r" + "\r" + "			if(f.getFILED_NAME_UPPER_FIRST() == value) {\r" + "\r" + "				all.add(f);\r" + "\r" + "			}\r" + "		}\r" + "\r" + "		return all;\r" + "	}\r";

		return temp.replaceAll("FILED_NAME_UPPER_FIRST", StringUtil.firstToUpperCase(f)).replaceAll("FILED_TYPE", t);
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
