package cn.javaplus.commons.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class LoggerOnThread {

	private Logger logger;

	protected LoggerOnThread(Logger logger) {
		this.logger = logger;
	}

	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	public void error(Object message) {
		logger.error(message);
	}

	public void fatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	public void fatal(Object message) {
		logger.fatal(message);
	}

	public final Level getLevel() {
		return logger.getLevel();
	}

	public final String getName() {
		return logger.getName();
	}

	public void info(Object message, Throwable t) {
		logger.info(message, t);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void log(Priority priority, Object message, Throwable t) {
		logger.log(priority, message, t);
	}

	public void log(Priority priority, Object message) {
		logger.log(priority, message);
	}

	public void log(String callerFQCN, Priority level, Object message,
			Throwable t) {
		logger.log(callerFQCN, level, message, t);
	}

	public void trace(Object message, Throwable t) {
		logger.trace(message, t);
	}

	public void trace(Object message) {
		logger.trace(message);
	}

	public void warn(Object message, Throwable t) {
		logger.warn(message, t);
	}

	public void warn(Object message) {
		logger.warn(message);
	}
	
	
	

}
