package cn.javaplus.plugins.console;

import java.io.PrintStream;

public class ConsoleFactory {

	public static PrintStream getOut() {
		return System.out;
	}

	public static PrintStream getErr() {
		return System.err;
	}

}
