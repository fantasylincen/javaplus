package cn.javaplus.generator.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;

import java.text.MessageFormat;
import java.util.List;

import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.util.Templet;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.resources.JavaProtocolHanderTemp;
import cn.javaplus.generator.protocol.util.ProtocolClassFilter;
import cn.javaplus.java.JavaFile;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class ProtocolHandlerDotJavaGenerator {
	public void generate(List<JavaFile> cs) {
		List<JavaFile> classz = new ProtocolClassFilter().filter(cs);
		Templet t = JavaProtocolHanderTemp.getTemplet();
		build(classz, t);
		t.writeToFile(getPath());
	}

	private void build(List<JavaFile> classz, Templet t) {
		t.set("PACKAGE_NAME", D.JAVA_PROTOCOL_HANDLER_PACKET);
		for (JavaFile class1 : classz) {
			t.append("PUT_ACTIONS", buildClass(class1));
		}
	}

	private String buildClass(JavaFile class1) {
		StringPrinter out = new StringPrinter();
		List<MethodDeclaration> all = class1.getMethods();

		for (MethodDeclaration method : all) {
			if (Utils.isSetUserMethod(method)) {
				continue;
			}
			printMethod(class1, out, method);
		}
		return out.toString();

	}

	private void printMethod(JavaFile class1, StringPrinter out,
			MethodDeclaration method) {

		String cn = class1.getClassFullName();
		String mn = method.getName();
		String an = buildActionName(cn, mn);

		String format = "		actions.put(key(\"{0}\", \"{1}\"), new {2}());";
		out.println(MessageFormat.format(format, cn, mn, an));
	}

	private String buildActionName(String cn, String mn) {
		return cn + Util.Str.firstToUpperCase(mn) + "Action";
	}

	private String getPath() {
		String path = D.JAVA_SRC_PATH + D.JAVA_PROTOCOL_HANDLER_PACKET + "/";
		path = path.replaceAll("\\.", "/");
		return path + "ProtocolHandler.java";
	}
}
