package cn.javaplus.build.ssh;

import java.util.List;

public class SSHExecException extends RuntimeException {

	private List<String> backLines;

	public SSHExecException(List<String> backLines) {
		this.backLines = backLines;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2889949234899589724L;

	public List<String> getBackLines() {
		return backLines;
	}
}
