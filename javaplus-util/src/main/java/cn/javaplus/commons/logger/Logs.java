package cn.javaplus.commons.logger;

import org.apache.log4j.Logger;

/**
 * 各个日志记录器
 */
public class Logs {

	public static interface ILogger {

		public void debug(Object message, Throwable t);

		public void debug(Object message);

		public void error(Object message, Throwable t);

		public void error(Object message);

		public void info(Object message, Throwable t);

		public void info(Object message);


		public void debug(Object head, Object message, Throwable t);

		public void debug(Object head, Object message);

		public void error(Object head, Object message, Throwable t);

		public void error(Object head, Object message);

		public void info(Object head, Object message, Throwable t);

		public void info(Object head, Object message);
	}

	static {
		init();
	}

	public static ILogger	debug;

	private final static void init() {
		Logger logger = Logger.getLogger("cn.mxz.debug");
		debug = new MyLogger(logger);
	}

	private static class MyLogger implements ILogger {

		private Logger	logger;

		private MyLogger(Logger logger) {
			this.logger = logger;
		}

		@Override
		public void debug(Object message, Throwable t) {
			logger.debug(message, t);
		}

		@Override
		public void debug(Object message) {
			logger.debug(message);
		}

		@Override
		public void error(Object message, Throwable t) {
			logger.error(message, t);
		}

		@Override
		public void error(Object message) {
			logger.error(message);
		}

		@Override
		public void info(Object message, Throwable t) {
			logger.info(message, t);
		}

		@Override
		public void info(Object message) {
			logger.info(message);
		}

		@Override
		public void debug(Object head, Object message, Throwable t) {
			head = fix(head);
			debug(head + "" + message, t);
		}

		@Override
		public void debug(Object head, Object message) {
			head = fix(head);
			debug(head + "" + message);
		}

		@Override
		public void error(Object head, Object message, Throwable t) {
			head = fix(head);
			error(head + "" + message, t);
		}

		@Override
		public void error(Object head, Object message) {
			head = fix(head);
			error(head + "" + message);
		}

		@Override
		public void info(Object head, Object message, Throwable t) {
			head = fix(head);
			info(head + "" + message, t);
		}

		@Override
		public void info(Object head, Object message) {
			head = fix(head);
			info(head + "" + message);
		}

		private Object fix(Object head) {
			if(head == null) {
				head = "";
			}
			return head;
		}

	}
}
