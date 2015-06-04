package cn.mxz.system;

public class Args {

	public static String[]	args;

	public static String get(String key) {
		key = "-" + key + ":";
		for (String c : args) {
			if(c.startsWith(key)) {
				return c.replaceFirst(key, "");
			}
		}
		return null;
	}
}
