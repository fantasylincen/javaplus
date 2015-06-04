package cn.mxz.base.telnet.command.system;

import java.io.PrintWriter;

public class RestartServerEvent {

	private PrintWriter	out;
	private String[]	args;

	
	public RestartServerEvent(PrintWriter out, String[] args) {
		this.out = out;
		this.args = args;
	}

	public String[] getArgs() {
		return args;
	}

	public PrintWriter getOut() {
		return out;
	}
}
