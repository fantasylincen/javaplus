package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;

import java.text.MessageFormat;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.string.StringPrinter;

public class PutActionsGenerator {

	public String generate(List<JavaFile> cs) {
		StringPrinter out = new StringPrinter();
		for (JavaFile class1 : cs) {
			if (class1.containsAnnotation(Communication.class)) {
				List<MethodDeclaration> methods = class1.getMethods();
				for (MethodDeclaration method : methods) {
					if (Utils.isServerToClientOnly(method)) {
						continue;
					}
					printMethod(class1, out, method);
				}
			}
		}
		return out.toString();
	}

	private void printMethod(JavaFile class1, StringPrinter out,
			MethodDeclaration method) {

		String cn = class1.getClassFullName();
		String mn = method.getName();
		String actionName = Utils.getActionClassName(class1, method);

		String format = "		actions.put(key(\"{0}\", \"{1}\"), new {2}());";

		out.println(MessageFormat.format(format, cn, mn, actionName));
	}
}
