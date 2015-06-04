package _util;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BaseTypeConfig {

	private static Map<String, String> map = new HashMap<String,String>();
	private static Map<String, String> asmap = new HashMap<String,String>();

	static {
		map.put("int", "Integer");
		map.put("boolean", "Boolean");
		map.put("Boolean", "Boolean");
		map.put("float", "Float");
		map.put("byte", "Byte");
		map.put("double", "Double");
		map.put("long", "Long");
		map.put("String", "String");
		map.put("short", "Short");
	}

	static {
		asmap.put("int", "int");
		asmap.put("boolean", "Boolean");
		asmap.put("Boolean", "Boolean");
		asmap.put("float", "Number");
		asmap.put("byte", "Number");
		asmap.put("double", "Number");
		asmap.put("long", "Number");
		asmap.put("String", "String");
		asmap.put("short", "Number");
	}

	public static boolean contains(String keyWord) {
		return map.containsKey(keyWord);
	}

	public static String parseToPackagingType(String type) {
		return map.get(type);
	}

	public static String parseToBaseType(String type) {

		for (Entry<String, String> e : map.entrySet()) {

			if(e.getValue().equals(type)) {

				return e.getKey();
			}
		}

		return null;
	}

	public static String parseASType(String type) {
		return asmap.get(type);
	}
}
