package cn.mxz.util.debuger;

import org.apache.log4j.Logger;

import com.lemon.commons.logger.Logs.ILogger;

public class SystemLogger implements ILogger {

	private Logger logger;

	public SystemLogger() {
		logger = Logger.getLogger("cn.mxz.systemlog");
	}

	@Override
	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void error(Object message) {
		logger.error(message);
	}

	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void info(Object message, Throwable t) {
		logger.info(message, t);
	}

	@Override
	public void debug(Object head, Object message, Throwable t) {
		logger.debug(buildHead(head, message), t);
	}

	private String buildHead(Object head, Object message) {
		return "[" + head + "]" + message;
	}

	@Override
	public void debug(Object head, Object message) {
		logger.debug(buildHead(head, message));
	}

	@Override
	public void error(Object head, Object message, Throwable t) {
		logger.error(buildHead(head, message), t);
	}

	@Override
	public void error(Object head, Object message) {
		logger.error(buildHead(head, message));
	}

	@Override
	public void info(Object head, Object message, Throwable t) {
		logger.info(buildHead(head, message), t);
	}

	@Override
	public void info(Object head, Object message) {
		logger.info(buildHead(head, message));
	}

}
