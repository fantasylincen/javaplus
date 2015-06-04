package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.type.Type;
import japa.parser.ast.type.VoidType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.string.StringPrinter;

public class EventDotJavaGenerator {

	public String generate(List<JavaFile> cs) {

		StringPrinter o = new StringPrinter();
		for (JavaFile c : cs) {
			if (!c.containsAnnotation(Communication.class)) {
				continue;
			}
			List<MethodDeclaration> methods = c.getMethods();
			for (MethodDeclaration method : methods) {

				if (Utils.isServerToClientOnly(method)) {
					createEventClassServerToClientOnly(c, method, o);
				} else {
					createEventClass(c, method, o);
				}
			}
		}

		return o.toString();

	}

	private void createEventClassServerToClientOnly(JavaFile class1,
			MethodDeclaration method, StringPrinter sp) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		Type t = method.getParameters().get(0).getType();

		String eventName = Utils.getEventClassName(class1, method);
		printHead(out, eventName);

		if (isBaseType(t)) {
			throw new RuntimeException("不支持基本数据类型!");
		} else {
			printBacksServerToClientOnly(out, method);
		}

		out.println("	}");

		out.flush();
		out.close();
		sp.println(sw);
	}

	private void printBacksServerToClientOnly(PrintWriter out,
			MethodDeclaration method) {
		Parameter p = method.getParameters().get(0);
		Type t = p.getType();
		out.println("		public " + Utils.getFiledType(t) + " getBack() {");
		out.println("			return new " + Utils.getFiledType(t) + "(o);");
		out.println("		} ");
	}

	private void printHead(PrintWriter out, String eventName) {
		out.println("	public static class " + eventName + " {");
		out.println();
		out.println("		private JsonValue o;");
		out.println();
		out.println("		public " + eventName + "(JsonValue o) {");
		out.println("			this.o = o;");
		out.println("		}");
		out.println();
	}

	private void createEventClass(JavaFile class1, MethodDeclaration method,
			StringPrinter sp) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		Type t = method.getType();

		String eventName = Utils.getEventClassName(class1, method);
		printHead(out, eventName);

		if (isBaseType(t)) {
			throw new RuntimeException("不支持基本数据类型!");
		} else if (isVoid(t)) {

		} else {
			printBacks(out, method);
		}

		out.println("	}");

		out.flush();
		out.close();
		sp.println(sw);
	}

	private boolean isBaseType(Type rt) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("int", "Integer");
		map.put("boolean", "Boolean");
		map.put("Boolean", "Boolean");
		map.put("float", "Float");
		map.put("byte", "Byte");
		map.put("double", "Double");
		map.put("long", "Long");
		map.put("String", "String");
		map.put("short", "Short");

		String s = rt + "";
		return map.containsKey(rt + "") || map.containsValue(s);
	}

	private boolean isVoid(Type rt) {
		return rt instanceof VoidType;
	}

	private void printBacks(PrintWriter out, MethodDeclaration method) {
		out.println("		public " + Utils.getFiledType(method) + " getBack() {");
		out.println("			return new " + Utils.getFiledType(method) + "(o);");
		out.println("		} ");
	}
}
