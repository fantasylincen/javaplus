package util;

import java.util.ArrayList;
import java.util.List;

public class MainArgs {

	private static String[] args;

	public static void set(String[] args) {
		if (MainArgs.args != null) {
			appendArgs(args);
		} else {
			MainArgs.args = args;
		}
	}

	private static void appendArgs(String[] args) {
		List<String> ls = new ArrayList<String>();
		for (String s : MainArgs.args) {
			ls.add(s);
		}
		for (String s : args) {
			ls.add(s);
		}
		MainArgs.args = toArray(ls);
	}

	private static String[] toArray(List<String> ls) {
		String[] a = new String[ls.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = ls.get(i);
		}
		return a;
	}

	public static String[] getArgs() {
		return args;
	}

	public static String find(String name) {
		boolean isFind = false;
		for (String a : args) {
			if (isFind) {
				return a;
			}
			if (a.equals("-" + name)) {
				isFind = true;
			}
		}
		throw new ArgsNotFoundException(name);
	}

	public static Integer findInteger(String name) {
		String find = find(name);
		if (find == null) {
			return null;
		}
		return new Integer(find);
	}

	public static boolean contains(String name) {
		for (String a : args) {
			if (a.equals("-" + name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 空格连接的参数列表 比如 -host 10.1.1.1 port 22
	 * 
	 * @param args
	 */
	public static void set(String args) {
		set(args.split(" "));
	}

}
