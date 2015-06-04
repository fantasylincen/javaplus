package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.type.Type;

import java.text.MessageFormat;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.file.Templet;
import cn.javaplus.string.StringPrinter;

public class ServerMessageSenderGenerator {

	public String generate(List<JavaFile> cs) {
		StringPrinter out = new StringPrinter();
		for (JavaFile class1 : cs) {
			if (class1.containsAnnotation(Communication.class)) {
				List<MethodDeclaration> methods = class1.getMethods();

				printField(out, class1);
				printGetter(out, class1);

				out.println("	public class " + class1.getClassSimpleName()
						+ " {");
				for (MethodDeclaration method : methods) {
					if (Utils.isServerToClientOnly(method)) {
						printMethod(out, method, class1);
					}
				}

				out.println("	}");
			}
		}
		return out.toString();
	}

	private void printMethod(StringPrinter out, MethodDeclaration method,
			JavaFile class1) {
		List<Parameter> parameters = method.getParameters();

		if (parameters.size() != 1) {
			throw new RuntimeException("一个方法  只能有1个参数 : " + method);
		}
		Parameter p = parameters.get(0);

		Templet t = new Templet("ServerMessageSenderGenerator.printClass");
		t.set("METHOD_NAME", method.getName());
		Type type = p.getType();

		t.set("PAR_TYPE", Utils.getFullPath(class1, type + ""));
		t.set("PAR_NAME", p.getId() + "");
		t.set("FULL_CLASS_PATH", class1.getClassFullName());
		out.println(t.toString());

	}

	private void printGetter(StringPrinter out, JavaFile class1) {
		String fieldName = Utils.getHandlerField(class1);
		String typeName = class1.getClassSimpleName();
		String ss = "	public " + typeName + " get" + typeName
				+ "() {\r		return " + fieldName + ";\r	}";
		out.println(ss);
	}

	private void printField(StringPrinter out, JavaFile class1) {
		String fieldName = Utils.getHandlerField(class1);
		String typeName = class1.getClassSimpleName();
		String ss = "	private {0} {1} = new {2}();";
		out.println(MessageFormat.format(ss, typeName, fieldName, typeName));
	}

}
