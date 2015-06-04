package cn.javaplus.build.protocol.generator;

import japa.parser.ast.Comment;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.random.Fetcher;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public class ProtocolClassGenerator {

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

			buildHead(out, c);
			buildMethods(out, c);
			buildClasses(out, c);
			buildCheckTimeMethod(out, c);
			buildTail(out);

			out.flush();
			out.close();
			o.println(sw);
		}
		return o.toString();
	}

	private void buildCheckTimeMethod(PrintWriter out, JavaFile c) {

		List<MethodDeclaration> all = c.getMethods();
		out.println("		boolean checkTimeOut() {");
		for (MethodDeclaration m : all) {
			if (Utils.isServerToClientOnly(m)) {
				continue;
			}
			String s = Utils.getListenerName(m);
			out.println("			" + s + " = Protocols.this.checkTimeOut(" + s
					+ ");");
		}

		for (MethodDeclaration m : all) {
			if (Utils.isServerToClientOnly(m)) {
				continue;
			}
			String s = Utils.getListenerName(m);
			out.println("			if(" + s + " != null) return false;");
		}
		out.println("			return true;");
		out.println("		}");

	}

	private void buildClasses(PrintWriter out, JavaFile c) {
		List<MethodDeclaration> all = c.getMethods();
		for (MethodDeclaration m : all) {
			String lcn = Utils.getListenerClassName(m);
			out.println("		private " + lcn + " " + Utils.getListenerName(m)
					+ ";");
		}
	}

	private void buildMethods(PrintWriter out, JavaFile c) {
		List<MethodDeclaration> all = c.getMethods();
		for (MethodDeclaration m : all) {
			if (Utils.isServerToClientOnly(m)) {
				printServerToClientOnlyBody(out, c, m);
			} else {
				printBody(out, c, m);
			}
		}
	}

	private void printServerToClientOnlyBody(PrintWriter out, JavaFile c,
			MethodDeclaration m) {
		putDoc(out, m);
		String ff = Util.Str.firstToUpperCase(m.getName());

		String ss = Utils.getListenerClassName(m);
		String ss2 = Utils.getListenerName(m);
		putDoc(out, m);
		out.println("		public void wait" + ff + "(" + ss + " " + ss2 + ") {");
		String ln = Utils.getListenerName(m);
		out.println("			this." + ln + " = " + ln + ";");
		out.println("		}");
	}

	private void printBody(PrintWriter out, JavaFile c, MethodDeclaration m) {
		putDoc(out, m);
		out.println("		public void " + m.getName() + "(" + buildArgs(m) + ") {");
		putBody(out, c, m);
		out.println("			sender.send(obj.toJSONString());");
		out.println("		}");
	}

	private void putDoc(PrintWriter out, MethodDeclaration m) {
		Comment comment = m.getComment();
		if (comment == null) {
			return;
		}
		out.print("		/**");
		buildDoc(out, comment);
		out.println("*/");
	}

	private void buildDoc(PrintWriter out, Comment comment) {
		String[] ss = comment.getContent().split("\r\n");
		for (String string : ss) {
			if (string.isEmpty()) {
				continue;
			}
			out.println();
			out.print("	" + string);
		}
	}

	private void putBody(PrintWriter out, JavaFile c, MethodDeclaration m) {
		out.println("			JSONObject obj = new JSONObject();");
		String ln = Utils.getListenerName(m);
		out.println("			this." + ln + " = " + ln + ";");
		out.println("			obj.put(\"className\", \"" + c.getClassFullName()
				+ "\");");
		out.println("			obj.put(\"methodName\", \"" + m.getName() + "\");");

		List<Parameter> ts = m.getParameters();

		ts = Util.Collection.nullToEmpty(ts);

		for (int j = 0; j < ts.size(); j++) {
			Parameter p = ts.get(j);
			String id = p.getId() + "";
			out.println("			obj.put(\"arg-" + id + "\", " + id + ");");
		}
		out.println("			startTimeOutMonitor();");
	}

	private String buildArgs(MethodDeclaration m) {

		List<Par> all = getParms(m);

		Fetcher<Par, String> fetcher = new Fetcher<Par, String>() {

			@Override
			public String get(Par t) {
				String sName = t.getType() + "";
				return sName + " " + t.getName();
			}
		};

		return Util.Collection.linkWith(", ", all, fetcher);
	}

	private List<Par> getParms(MethodDeclaration m) {
		List<Parameter> ps = m.getParameters();
		ps = Util.Collection.nullToEmpty(ps);
		ArrayList<Par> ls = Lists.newArrayList();
		for (Parameter parameter : ps) {
			String type = parameter.getType() + "";
			String name = parameter.getId() + "";
			ls.add(new Par(name, type));
		}

		String ss = Utils.getListenerClassName(m);
		String ss2 = Utils.getListenerName(m);
		ls.add(new Par(ss2, ss));

		return ls;
	}

	private void buildTail(PrintWriter out) {
		out.println("	}");
	}

	private void buildHead(PrintWriter out, JavaFile c) {
		out.println("	public class " + c.getClassSimpleName() + " {");
	}
}
