package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.file.Templet;
import cn.javaplus.util.Util;

public class ProtocolHandlerGenerator {
	public String generate(List<JavaFile> cs) {

		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		build(cs, out);

		out.flush();
		out.close();
		return sw.toString();
	}

	private void build(List<JavaFile> classz, PrintWriter out) {
		String temp = "ProtocolHandlerDotJavaGenerator.temp";
		URL url = ProtocolHandlerGenerator.class.getResource(temp);
		Templet t = Templet.createByContent(Util.File.getContent(url));

		for (JavaFile c : classz) {
			if (!c.containsAnnotation(Communication.class)) {
				continue;
			}
			printClass(c, t);
		}

		out.println(t);
	}

	private void printClass(JavaFile class1, Templet t) {

		List<MethodDeclaration> all = class1.getMethods();

		for (MethodDeclaration method : all) {
			String actionName = Utils.getActionClassName(class1, method);
			String key = class1.getClassFullName() + "." + method.getName();
			t.append("PUT_ACTIONS", "				actions.put(\"" + key + "\", new "
					+ actionName + "());");
		}
	}
}
