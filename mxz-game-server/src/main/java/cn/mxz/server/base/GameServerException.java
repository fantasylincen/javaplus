package cn.mxz.server.base;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GameServerException extends RuntimeException {

	private static final long serialVersionUID = -2975084643146473039L;

	public GameServerException(String message) {
		super(message);
	}

	public String getStackTraceMessage() {
		StringWriter sw = new StringWriter();
		printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}
