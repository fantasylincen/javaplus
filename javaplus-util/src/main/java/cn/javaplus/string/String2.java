package cn.javaplus.string;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 增强String
 * @author 林岑
 *
 */
public class String2 {


	private StringWriter sw ;
	private PrintWriter out;

	public String2(Object o) {
		this();
		print(o);
	}

	public String2() {
		init();
	}

	public void print(Object obj) {
		out.print(obj);
	}

	public void println(Object x) {
		out.println(x);
	}


	/**
	 * 删除字符串中  正则表达式所表示的所有字符串
	 * @param regex
	 */
	public void delete(String regex) {
		String o = toString();
		o = o.replaceAll(regex, "");
		resave(o);
	}

	private void resave(String o) {
		init();
		print(o);
	}

	private void init() {
		sw = new StringWriter();
		out = new PrintWriter(sw);
	}

	@Override
	public String toString() {
		return sw.toString();
	}

	public void trim() {
		String o = toString();
		o = o.trim();
		resave(o);
	}
}
