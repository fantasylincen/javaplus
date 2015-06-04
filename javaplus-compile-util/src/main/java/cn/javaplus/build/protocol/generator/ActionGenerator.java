package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.string.StringPrinter;

public class ActionGenerator {

	public String generate(List<JavaFile> cs) {

		StringPrinter o = new StringPrinter();
		for (JavaFile c : cs) {
			if (!c.containsAnnotation(Communication.class)) {
				continue;
			}
			List<MethodDeclaration> methods = c.getMethods();
			for (MethodDeclaration method : methods) {
				if (Utils.isServerToClientOnly(method)) {
					createActionClassServerToClientOnly(c, method, o);
				} else {
					createActionClass(c, method, o);
				}
			}
		}
		return o.toString();

	}

	private void createActionClassServerToClientOnly(JavaFile class1,
			MethodDeclaration method, StringPrinter sp) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		String actionName = Utils.getActionClassName(class1, method);
		String eventName = Utils.getEventClassName(class1, method);
		out.println("	private class " + actionName + " implements Action {");
		out.println();
		out.println();
		out.println("		@Override");
		out.println("		public void run(String className, String methodName, JsonValue obj) {");

		String handlerName = Utils.getHandlerField(class1);
		String listenerName = Utils.getListenerName(method);

		out.println("			if(" + handlerName + "." + listenerName + " != null) {");
		out.println("				" + handlerName + "." + listenerName
				+ ".completed(new " + eventName + "(obj));");
		out.println("			}");
		out.println("		}");
		out.println("	}");
		out.flush();
		out.close();
		sp.println(sw);
	}

	private void createActionClass(JavaFile class1, MethodDeclaration method,
			StringPrinter sp) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		String actionName = Utils.getActionClassName(class1, method);
		String eventName = Utils.getEventClassName(class1, method);
		out.println("	private class " + actionName + " implements Action {");
		out.println();
		out.println();
		out.println("		@Override");
		out.println("		public void run(String className, String methodName, JsonValue obj) {");

		String handlerName = Utils.getHandlerField(class1);
		String listenerName = Utils.getListenerName(method);

		out.println("			if(" + handlerName + "." + listenerName + " != null) {");
		out.println("				JsonValue e = obj.get(\"error\");");
		out.println("				if(e != null) {");
		out.println("					String error = e.asString();");
		out.println("					" + handlerName + "." + listenerName
				+ ".faild(error);");
		out.println("				} else {");
		out.println("					" + handlerName + "." + listenerName
				+ ".completed(new " + eventName + "(obj));");
		out.println("					" + handlerName + "." + listenerName + " = null;");
		out.println("				}");
		out.println("			}");
		out.println("		}");
		out.println("	}");
		out.flush();
		out.close();
		sp.println(sw);
	}

}
