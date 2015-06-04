package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.type.Type;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class ServerActionsGenerator {
	public String generate(List<JavaFile> cs) {

		StringPrinter o = new StringPrinter();
		for (JavaFile c : cs) {
			if (!c.containsAnnotation(Communication.class)) {
				continue;
			}
			List<MethodDeclaration> methods = c.getMethods();
			for (MethodDeclaration method : methods) {
				if (Utils.isServerToClientOnly(method)) {
					continue;
				}
				createActionClass(c, method, o);
			}
		}
		return o.toString();

	}

	private void createActionClass(JavaFile class1, MethodDeclaration method,
			StringPrinter sp) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		List<Parameter> parameters = method.getParameters();
		String actionName = Utils.getActionClassName(/* class1, */method);
		String className = Utils.getActionClassName(class1, method);
		String full = findFullClassName(class1, method);
		String actionPath = class1.getPackage() + "." + actionName;
		String args = buildArgs(parameters);

		out.println("	private class " + className + " implements Action {");
		out.println();
		out.println("		@Override");
		out.println("		public String excute(Request r, ProtocolContext ctx) {");
		boolean isVoid = isVoid(method);
		if (!isVoid) {
			out.println("			" + full + " excuteResut = null;");
		}
		out.println("			Abstract" + actionName + " action = new " + actionPath
				+ "();");
		parameters = Util.Collection.nullToEmpty(parameters);
		for (Parameter t : parameters) {
			out.println("			" + t.getType() + " " + t.getId() + " = " + "r."
					+ getType(t.getType()) + "(\"arg-" + t.getId() + "\");");
		}
		out.println("			try {");
		out.println("				before(ctx);");
		out.println("				action.before(" + args + ");");
		if (isVoid) {
			out.println("				action.excute(" + args + ");");
		} else {
			out.println("				excuteResut = action.excute(" + args + ");");
		}
		out.println("				after(ctx);");
		out.println("				action.after(" + args + ");");
		if (isVoid) {
			out.println("				return success(r);");
		} else {
			out.println("				return success(excuteResut, r);");
		}
		out.println("			} catch (ServerException e) {");
		out.println("				exception(ctx);");
		out.println("				action.exception(" + args + ");");
		out.println("				return error(e.getMessage(), r);");
		out.println("			} finally {");
		out.println("				onFinally(ctx);");
		out.println("				action.onFinally(" + args + ");");
		out.println("			}");
		out.println("		}");
		out.println("	}");

		String ps = buildPs(parameters);

		out.println("	public static abstract class Abstract" + actionName
				+ " {");
		out.println("		protected void before(" + ps + ") {}");
		out.println("		protected void after(" + ps + ") {}");
		out.println("		protected void exception(" + ps + ") {}");
		out.println("		protected void onFinally(" + ps + ") {}");
		if (isVoid) {
			out.println("		protected abstract void excute(" + ps + ");");
		} else {
			out.println("		protected abstract " + full + " excute(" + ps + ");");
		}
		out.println("	}");

		out.flush();
		out.close();
		sp.println(sw);

	}

	private boolean isVoid(MethodDeclaration method) {
		Type type = method.getType();
		return (type + "").equals("void");
	}

	private String buildPs(List<Parameter> parameters) {
		List<String> ls = Lists.newArrayList("ProtocolContext ctx");
		for (Parameter parameter : parameters) {
			ls.add(parameter + "");
		}
		return Util.Collection.linkWith(", ", ls);
	}

	private String findFullClassName(JavaFile class1, MethodDeclaration method) {
		Type type = method.getType();

		String string = class1.getType().toString();
		Pattern p = Pattern.compile("$import .+" + type + ";");
		Matcher m = p.matcher(string);
		if (m.find()) {
			String group = m.group().replaceFirst("import ", "")
					.replaceAll(";", "");
			return group;
		}
		String package1 = class1.getPackage();

		return package1 + "." + type;
	}

	private String buildArgs(List<Parameter> parameters) {
		List<String> ls = Lists.newArrayList("ctx");
		parameters = Util.Collection.nullToEmpty(parameters);
		for (Parameter p : parameters) {
			ls.add(p.getId() + "");
		}

		return Util.Collection.linkWith(", ", ls);
	}

	private String getType(Type type) {
		String t = type + "";
		if (t.equals("int")) {
			return "getInt";
		}
		if (t.equals("String")) {
			return "getString";
		}
		if (t.equals("boolean")) {
			return "getBoolean";
		}
		if (t.equals("Integer")) {
			return "getInt";
		}
		if (t.equals("Boolean")) {
			return "getBoolean";
		}
		return "null";
	}
}
