package cn.javaplus.mail;

import java.io.IOException;
import java.io.Writer;

public class MyStringWriter extends Writer {

	private StringBuilder sb;

	public MyStringWriter(StringBuilder sb) {
		this.sb = sb;
	}

	@Override
	public void close() throws IOException {
	}

	@Override
	public void flush() throws IOException {
		sb.delete(0, sb.length());
	}

	@Override
	public void write(char[] arg0, int arg1, int arg2) throws IOException {

		sb.append(arg0, arg1, arg2);
	}

	@Override
	public String toString() {
		return sb.toString();
	}

}
