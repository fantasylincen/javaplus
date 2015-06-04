package cn.javaplus.plugins.util;

public class StringUtils {

	public static String generateClassName(String tableName) {
		return xxx(tableName).replaceFirst(tableName.substring(0, 1), tableName.substring(0, 1).toUpperCase());
	}

	public static String xxx(String columnName) {
		String[] labels = columnName.split("_");
		String r = labels[0];
		for (int i = 1; i < labels.length; i++)
			r = r + generateClassName(labels[i]);
		return r;
	}

	public static final String firstToUpperCase(String src) {
		return src.replaceFirst(src.substring(0, 1), src.substring(0, 1).toUpperCase());
	}

	public static String generateParameterName(String tableName) {

		String name = hump(tableName);

		if (name.length() > 7) {
			StringBuilder sb = new StringBuilder();

			char[] chars = name.toCharArray();

			for (int i = 0; i < chars.length; i++) {
				char c = chars[i];
				if (i == 0 || Character.isUpperCase(c)) {
					sb.append(Character.toLowerCase(c));
				}
			}
			return sb.toString() + "o";
		} else {
			return name + "o";
		}
	}

	public static String hump(String srcText) {

		// 把下划线之后的一个字符都变为大写
		StringBuilder sb = new StringBuilder();
		char[] chars = srcText.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (i != 0 && chars[i - 1] == '_') {
				sb.append(Character.toUpperCase(chars[i]));
			} else {
				sb.append(Character.toLowerCase(chars[i]));
			}
		}

		String text = sb.toString().replace("_", "");
		return text;
	}

}
