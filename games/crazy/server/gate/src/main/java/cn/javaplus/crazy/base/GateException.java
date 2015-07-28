package cn.javaplus.crazy.base;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GateException extends RuntimeException {

	private static final long serialVersionUID = -2975084643146473039L;

	public GateException(String message) {
		super(message);
	}

	public String getStackTraceMessage() {
		StringWriter sw = new StringWriter();
		printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}
