package cn.javaplus.plugins.console;

import java.io.PrintStream;

public class Debuger {

	public static void debug(String string) {
		PrintStream ps = ConsoleFactory.getOut();
		ps.println(string);
	}

	public static void err(String string) {
		PrintStream ps = ConsoleFactory.getErr();
		ps.println(string);
	}

}
