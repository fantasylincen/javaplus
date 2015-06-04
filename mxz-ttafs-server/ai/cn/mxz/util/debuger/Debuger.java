package cn.mxz.util.debuger;

import cn.mxz.log.MXZLogger;

import com.lemon.commons.logger.Logs.ILogger;

/**
 * 调试器.
 *
 * @author 林岑
 * @since 2013年5月27日 18:14:02
 */
public final class Debuger {

	private static final int HEAD_MAX_SIZE = 32;

	/**
	 * 是否是调试模式.
	 */
	private static boolean	isDebug;

	private static boolean isDevelop;

	/**
	 * .
	 */
	private Debuger() {
	}

	/**
	 * 设置为调试模式.
	 */
	public static void setDebug() {
		isDebug = true;
	}

	/**
	 * 设置为正式版本.
	 */
	public static void setRelease() {
		isDebug = false;
	}

	/**
	 * 是否是调试模式.
	 */
	public static boolean isDebug() {
		return isDebug;
	}

	public static void init(String... args) {

		for (String arg : args) {

			arg = arg.trim();

			if (arg.equals("-debug")) {
				setDebug();
			}

			if(arg.equals("-dev")) {
				setDevelop();
			}
			
			if(arg.equals("-mxz")) {
				isInside = true;
			}
		}
	}

	private static void setDevelop() {
		isDevelop = true;
	}

	/**
	 * 是否是开发模式
	 * @return
	 * @deprecated 建议使用 city.isTester()
	 */
	public static boolean isDevelop() {
		return isDevelop;
	}


	/**
	 * 是否是第三方帐号
	 *
	 * @param id
	 * @return
	 */
	public static boolean isThirdPartId(String id) {
		if(id.matches("[1-9][0-9]{0,9}")) {
			return true;
		}
		if (isStartWith(id, "u", "lc", "qw", "jjy", "zh", "hbs", "mxz", "lk", "whj", "hjl", "dw", "fl", "rcs", "dyy", "zk", "gsk", "sy")) {
			return false;
		}

		return true;
	}

	private static boolean isStartWith(String id, String... start) {
		for (String s : start) {
			if (id.startsWith(s)) {
				return true;
			}
		}
		return false;
	}

	private static ILogger logger;

	private static boolean isInside;

	private static ILogger getDebuger() {
		if(logger == null) {
			logger = new MXZLogger();
		}
		return logger;
	}

	public static void debug(Object debug) {
		getDebuger().debug(debug);
	}

	public static void debug(Object head, Object debug) {
		checkHeadSize(head);
		getDebuger().debug(head, debug);
	}

	private static void checkHeadSize(Object head) {
		if(head != null) {
			int length = head.toString().getBytes().length;
			if(length > HEAD_MAX_SIZE) {
				throw new RuntimeException(head + " 长度不可以超过" + HEAD_MAX_SIZE);
			}
		}
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

	/**
	 * 是否是内部测试帐号
	 * @return
	 */
	public static boolean isInside() {
		return isInside;
	}
}
