package cn.javaplus.build.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.type.Type;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.CommunicationDto;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

/**
 * 生成所有的 Java 通信接口 的实现到AS目录
 * 
 * @author 林岑
 */
public class ValueObjectJavaClassGenerator {

	public String generate(List<JavaFile> cs) {
		StringPrinter out = new StringPrinter();
		for (JavaFile c : cs) {
			if (c.containsAnnotation(CommunicationDto.class)) {
				out.println(copy(c));
			}
		}
		return out.toString();
	}

	private static String copy(JavaFile c) {

		TypeDeclaration t = c.getType();
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		String name = t.getName();

		out.println();
		out.println("	public static class " + name + " {");
		out.println("		private JsonValue o;");
		out.println("		public " + name + "(JsonValue o) {");
		out.println("		 	this.o = o;");
		out.println("		}");

		List<MethodDeclaration> methods = c.getMethods();
		for (MethodDeclaration m : methods) {
			build(m, out);
		}

		out.println("	}");
		out.flush();
		return sw.toString();
	}

	private static void build(MethodDeclaration met, PrintWriter out) {
		buildGetter(met, out);
	}

	private static void buildGetter(MethodDeclaration met, PrintWriter out) {

		if (Utils.isCollection(met)) {
			buildCollectionGetter(met, out);
		} else {
			buildNormalGetter(met, out);
		}

	}

	private static void buildCollectionGetter(MethodDeclaration met,
			PrintWriter out) {

		String filedType = Utils.getJavaFiledType(met);

		String elementTypeName = Utils.getElementTypeName(met);

		String getterName = Utils.getGetterName(met);

		out.println("		public " + filedType + " " + getterName + " () {");
		out.println(MessageFormat.format("			{0} list = new ArrayList<{1}>();",
				filedType, elementTypeName));
		out.println("			JsonValue v = o.get(\"" + Utils.getBaseFiledName(met)
				+ "\");");
		out.println("			if(v == null) {");
		out.println("				return null;");
		out.println("			}");
		out.println("			for (JsonValue vv : v) {");
		out.println("				list.add(new " + elementTypeName + "(vv));");
		out.println(" 			}");
		out.println(" 			return list;");
		out.println(" 		}");
		out.println();
	}

	private static void buildNormalGetter(MethodDeclaration met, PrintWriter out) {

		String filedType = Utils.getJavaFiledType(met);

		out.println("		public " + filedType + " " + Utils.getGetterName(met)
				+ " () {");

		Type type = met.getType();

		if (Utils.isBaseType(type)) {
			String fName = "	o.get" + Util.Str.firstToUpperCase(type + "")
					+ "(\"" + Utils.getBaseFiledName(met) + "\")";
			out.println("			return " + fName + ";");
		} else {

			out.println("			JsonValue vvv = o.get(\""
					+ Utils.getBaseFiledName(met) + "\");");
			out.println("			if(vvv == null) {");
			out.println("				return null;");
			out.println("			}");
			out.println("			return new " + type + "(vvv);");
		}

		out.println("	 	}");
		out.println();
	}
}
