package cn.javaplus.shhz.string;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringPrinter {

	private StringWriter sw = new StringWriter();
	private PrintWriter out = new PrintWriter(sw);

	public StringPrinter(String text) {
		print(text);
	}

	public StringPrinter() {
	}

	public void print(Object obj) {
		out.print(obj);
	}

	public void println(Object x) {
		out.println(x);
	}

	@Override
	public String toString() {
		return sw.toString();
	}
}
