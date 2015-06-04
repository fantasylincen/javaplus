package cn.mxz.generator.formula;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.util.Util;
import cn.mxz.IFormula;

public class ScriptClass {

	private String	className;
	private List<IFormula>	all;

	/**
	 * @param className	类名
	 * @param all	所有子类
	 */
	public ScriptClass(String className, List<IFormula> all) {
		this.className = className;
		this.all = all;
	}

	@Override
	public String toString() {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		out.println("	public static class " + className + " {");

		for (IFormula f : all) {

			new MethodBuilder().printMethod(f, out);//打印方法
		}

		out.println("	}");


		String t = sw.toString();
		t = t.replaceAll("\\&gt;", ">");
		t = t.replaceAll("\\&lt;", "<");
		t = Util.Str.toDBC(t);
		return t;
	}
}
