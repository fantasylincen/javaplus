package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.string.StringPrinter;

public class CallBackImportsGenerator {
	public class Par {

		private String name;
		private String type;

		public Par(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public String getName() {
			return name;
		}

	}

	public String generate(List<JavaFile> cs) {

		StringPrinter o = new StringPrinter();
		for (JavaFile c : cs) {

			if (!c.containsAnnotation(Communication.class)) {
				continue;
			}

			StringWriter sw = new StringWriter();
			PrintWriter out = new PrintWriter(sw);

			buildClasses(out, c);

			out.flush();
			out.close();
			o.println(sw);
		}
		return o.toString();
	}

	private void buildClasses(PrintWriter out, JavaFile c) {
		List<MethodDeclaration> all = c.getMethods();
		String name = Utils.getListenersClassName(c);

		for (MethodDeclaration m : all) {
			String lcn = Utils.getListenerClassName(m);
			out.println("import cn.javaplus.crazy.Protocols." + name + "."
					+ lcn + ";");
		}
	}
}
