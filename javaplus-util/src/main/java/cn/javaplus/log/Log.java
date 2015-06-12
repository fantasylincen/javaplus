package cn.javaplus.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.javaplus.util.Util;

public class Log {

	private static final String formatText = "HH:mm:ss.SSS";

	private static Out out = new SystemOut();
	private static Out err = new SystemErr();

	private static SimpleDateFormat format = new SimpleDateFormat(formatText);

	public static void print(String head, IPrintStream out, Object... message) {
		String m = link(message);
		String time = buildTime();
		String[] split = head.split("\\.");
		head = split[split.length - 2] + "." + split[split.length - 1];
		out.println(time + "|" + head + "|" + m);
	}

	public static void setOut(Out out) {
		Log.out = out;
	}

	public static void setErr(Out err) {
		Log.err = err;
	}

	public static void e(Object... message) {
		String head = getFirstExternal();
		err.println(head, message);
	}

	protected static String buildTime() {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		return format.format(date);
	}

	public static void d(Object... message) {
		String head = getFirstExternal();
		out.println(head, message);
	}

	protected static String link(Object... message) {
		return Util.Collection.linkWith(", ", message);
	}

	private static String getFirstExternal() {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		boolean isFound = false;
		for (StackTraceElement s : trace) {
			if ("cn.javaplus.log.Log".equals(s.getClassName())) {
				isFound = true;
				continue;
			}
			if (isFound) {
				return s.getClassName() + "." + s.getMethodName();
			}
		}
		return "unknown method";
	}
}
