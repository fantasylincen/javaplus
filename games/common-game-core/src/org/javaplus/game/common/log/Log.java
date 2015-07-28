package org.javaplus.game.common.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.javaplus.game.common.util.Util;

public class Log {

	private static Out out;
	private static Out err;

	static {
		out = new SystemOut();
		err = new SystemErr();
	}

	public static void e(Throwable exception) {
		StringWriter sw = new StringWriter();
		PrintWriter p = new PrintWriter(sw);
		exception.printStackTrace(p);
		err.println(sw.toString());
	}

	public static void e(Object... message) {
		err.println(Util.Collection.linkWith(", ", message));
	}

	public static void d(Object... os) {
		out.println(Util.Collection.linkWith(", ", os));
	}

}
