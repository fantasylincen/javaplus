package cn.javaplus.shhz.log;

import java.io.PrintWriter;
import java.io.StringWriter;

import cn.javaplus.game.shhz.define.D;
import cn.javaplus.shhz.debug.TextDebugger;
import cn.javaplus.shhz.util.Util;

public class Log {

	private static Out out;
	private static Out err;

	static {
		out = new SystemOut();
		err = new SystemErr();
	}

	public static void bind(TextDebugger debugger) {
		out = debugger;
		err = debugger;
	}

	public static void e(Throwable exception) {
		if (D.IS_SHOW_ERROR_MESSAGE) {
			StringWriter sw = new StringWriter();
			PrintWriter p = new PrintWriter(sw);
			exception.printStackTrace(p);
			err.println(sw.toString());
		}
	}

	public static void e(Object... message) {
		if (D.IS_SHOW_ERROR_MESSAGE) {
			err.println(Util.Collection.linkWith(", ", message));
		}
	}

	public static void d(Object... os) {
		if (D.IS_SHOW_DEBUG_MESSAGE) {
			out.println(Util.Collection.linkWith(", ", os));
		}
	}

}
