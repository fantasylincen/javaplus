package cn.javaplus.log;

public class Debuger {

	public static void debug(Object message) {
		System.out.println(message);
	}

	public static void error(Object error) {
		System.err.println(error);
	}

}
