package cn.mxz.base.telnet;

import java.io.PrintWriter;

public class TelnetServerLoginEvent {

	private PrintWriter	out;

	public TelnetServerLoginEvent(PrintWriter out) {
		this.out = out;
	}

	public PrintWriter getOut() {
		return out;
	}
}
