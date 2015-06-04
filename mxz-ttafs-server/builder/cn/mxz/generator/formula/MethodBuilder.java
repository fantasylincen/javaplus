package cn.mxz.generator.formula;

import java.io.PrintWriter;
import java.util.List;

import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;
import cn.mxz.IFormula;

import com.google.common.collect.Lists;

public class MethodBuilder {

	private IFormula	formula;
	List<Field>			fields;

	public void printMethod(IFormula f, PrintWriter out) {

		this.formula = f;
		analysis(f);

		printComment(out);
		out.println("		public static " + f.getReturnType() + " " + f.getMethodName() + " (" + buildArgs() + ") {");
		printBody(out);
		out.println("		}");
	}

	private void printBody(PrintWriter out) {
		String temp = formula.getFormula();
		if(!temp.endsWith(";")) {
			temp = temp + ";";
		}
		for (Field f : fields) {
			temp = temp.replaceAll(f.getComment(), f.getName());
		}
		String[] all = temp.split("\\[newline\\]");
		out.println("		");
		for (String t : all) {
			out.println("			" + t );
		}
	}

	/**
	 *
	 * @param out
	 */
	private void printComment(PrintWriter out) {
		out.println("		/**");
		out.println("		 * " + formula.getComment());
		for (Field f : fields) {
			printArgCommnet(out, f);
		}
		out.println("		 */");
	}

	private void printArgCommnet(PrintWriter out, Field f) {
		out.println("		 * @param " + f.getName() + " " + f.getComment());
	}

	private void analysis(IFormula f) {
		fields = Lists.newArrayList();
		String[] all = f.getArgs().split("\\[newline\\]");
		int index = 0;
		for (String a : all) {
			a = a.trim();
			if (!a.isEmpty()) {
				analysisArg(a, index++);
			}
		}
	}

	private void analysisArg(String a, int index) {
		String[] fs = a.split(" ");
		if (fs.length != 2) {
			throw new RuntimeException(a + " 格式不合法! 有且仅由一个空格! 空格前面是字段类型, 后面是字段名字!");
		}

		fields.add(new Field(fs[0], fs[1], index));
	}

	private String buildArgs() {
		Fetcher<Field, String> f = new Fetcher<Field, String>() {

			@Override
			public String get(Field t) {
				return t.getType() + " " + t.getName();
			}
		};
		return Util.Collection.linkWith(", ", fields, f);
	}
}
