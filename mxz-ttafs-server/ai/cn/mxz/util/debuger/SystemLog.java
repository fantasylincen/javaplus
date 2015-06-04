package cn.mxz.util.debuger;

import com.lemon.commons.logger.Logs.ILogger;

/**
 * 系统日志
 *
 * @author 林岑
 * @since 2013年5月27日 18:14:02
 */
public final class SystemLog {

	private SystemLog() {
	}

	private static ILogger logger;


	private static ILogger getDebuger() {
		if(logger == null) {
			logger = new SystemLogger();
		}
		return logger;
	}

	public static void debug(Object debug) {
		getDebuger().debug(debug);
	}

	public static void debug(Object head, Object debug) {
		getDebuger().debug(head, debug);
	}


	public static void info(Object info) {
		getDebuger().info(info);
	}
	public static void error(Object error) {
		getDebuger().error(error);
	}

	public static void error(Object error, Throwable t) {
		getDebuger().error(error, t);
	}

	public static void info(Object head, Object info) {
		getDebuger().info(head, info);
	}
	public static void error(Object head, Object error) {
		getDebuger().error(head, error);
	}

	public static void error(Object head, Object error, Throwable t) {
		getDebuger().error(head, error, t);
	}

	public static void debug() {
		debug("");
	}
}
