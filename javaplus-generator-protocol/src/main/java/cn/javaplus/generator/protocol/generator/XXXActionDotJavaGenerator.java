package cn.javaplus.generator.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;

import java.util.List;

import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.util.Templet;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.resources.XXXActionTemp;
import cn.javaplus.generator.protocol.util.ProtocolClassFilter;
import cn.javaplus.java.JavaFile;
import cn.javaplus.random.Fetcher;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class XXXActionDotJavaGenerator {

	public void generate(List<JavaFile> cs) {

		List<JavaFile> classz = new ProtocolClassFilter().filter(cs);

		for (JavaFile class1 : classz) {
			List<MethodDeclaration> methods = class1.getMethods();
			for (MethodDeclaration method : methods) {

				if (Utils.isSetUserMethod(method)) {
					continue;
				}
				createActionClass(class1, method);
			}
		}
	}

	private void createActionClass(JavaFile class1, MethodDeclaration method) {

		Templet t = XXXActionTemp.getTemplet();

		t.set("PACKAGE_NAME", class1.getPackage());
		t.set("CLASS_NAME", buildClassName(class1, method));
		t.set("ARG_FIELDS", buildFields(method));
		t.set("SET_FIELDS", buildSetFields(method));

		t.set("EXCUTE_EXCUTE", "");
//		t.set("EXCUTE_EXCUTE", buildExcute(class1, method));
		t.set("EXCUTE_BEFORE", "");
		t.set("EXCUTE_AFTER", "");
		t.set("EXCUTE_EXCEPTION", "");
		t.set("EXCUTE_FINALLY", "");

		t.writeToFile(getPath(class1, method));
	}

	private String buildExcute(JavaFile class1, MethodDeclaration method) {
		return "		new " + class1.getClassSimpleName() + "Impl()."
				+ method.getName() + "(" + buildArgs(method) + ");";
	}

	private String buildArgs(MethodDeclaration method) {
		
		List<Parameter> ps = method.getParameters();
		
		ps = Util.Collection.nullToEmpty(ps);
		
		Fetcher<Parameter, String> f = new Fetcher<Parameter, String>() {

			@Override
			public String get(Parameter t) {
				return "as." + t.getId();
			}
		};
		return Util.Collection.linkWith(", ", ps, f);
	}

	private String buildFields(MethodDeclaration method) {
		List<Parameter> ts = method.getParameters();
		ts = Util.Collection.nullToEmpty(ts);
		StringPrinter out = new StringPrinter();
		for (int j = 0; j < ts.size(); j++) {
			Parameter p = ts.get(j);
			out.println("		" + p + ";");
		}
		return out.toString();
	}

	private String buildSetFields(MethodDeclaration method) {
		List<Parameter> ts = method.getParameters();
		ts = Util.Collection.nullToEmpty(ts);
		StringPrinter out = new StringPrinter();
		for (int j = 0; j < ts.size(); j++) {
			Parameter p = ts.get(j);
			String type = Util.JavaType.toPackagingType(p.getType() + "");
			out.println("			this." + p.getId() + " = (" + type + ") args[" + j
					+ "];");
		}
		return out.toString();
	}

	private String buildClassName(JavaFile class1, MethodDeclaration method) {
		String name = class1.getClassSimpleName();
		String f = Util.Str.firstToUpperCase(method.getName());
		return name + "" + f + "Action";
	}

	private String getPath(JavaFile class1, MethodDeclaration method) {
		String path = D.JAVA_SRC_PATH + class1.getPackage() + "/";
		path += buildClassName(class1, method);
		path = path.replaceAll("\\.", "/");
		return path + ".java";
	}
}
