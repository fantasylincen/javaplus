package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.TypeDeclaration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.util.Util;

/**
 * 
 * 生成Server.as
 * 
 * @author 林岑
 * 
 */
public class HandlersGenerator {

	private List<JavaFile> classz;

	public String generate(List<JavaFile> cs) {
		classz = cs;

		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		buildFields(out);
		buildGetters(out);

		out.flush();
		out.close();
		return sw.toString();
	}

	private void buildGetters(PrintWriter out) {
		for (JavaFile c : classz) {
			if (!c.containsAnnotation(Communication.class)) {
				continue;
			}
			buildGetter(out, c);
		}
	}

	private void buildGetter(PrintWriter out, JavaFile c) {
		TypeDeclaration type = c.getType();
		String UNAME = type.getName();
		String LNAME = Util.Str.firstToLowerCase(UNAME);
		out.println(Utils.getComment(c));

		out.println("	public " + UNAME + " get" + UNAME + "() {");
		out.println("		return " + LNAME + ";");
		out.println("	}");
	}

	private void buildFields(PrintWriter out) {
		for (JavaFile f : classz) {
			if (!f.containsAnnotation(Communication.class)) {
				continue;
			}
			String UNAME = f.getType().getName();
			String LNAME = Util.Str.firstToLowerCase(UNAME);
			String t = "	private " + UNAME + " " + LNAME + " = " + "new "
					+ UNAME + "();";
			out.println(t);
		}
	}

}
