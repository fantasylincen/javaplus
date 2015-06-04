package cn.javaplus.generator.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.type.Type;
import japa.parser.ast.type.VoidType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.javaplus.file.FileUtils;
import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.util.ProtocolClassFilter;
import cn.javaplus.java.JavaFile;
import cn.javaplus.string.BaseTypeConfig;

public class XXXEventDotASGenerator {

	public void generate(List<JavaFile> cs) {

		List<JavaFile> classz = new ProtocolClassFilter().filter(cs);

		for (JavaFile class1 : classz) {
			List<MethodDeclaration> methods = class1.getMethods();
			for (MethodDeclaration method : methods) {

				if(Utils.isSetUserMethod(method)) {
					continue;
				}
				createEventClass(class1, method);
			}
		}

	}

	private void createEventClass(JavaFile class1, MethodDeclaration method) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		out.println("package " + D.AS_EVENT_CLASS_PACKET);
		out.println("{");
		out.println();
		out.println("	import flash.utils.ByteArray;");

		Type t = method.getType();
		if (!isVoid(t) && !BaseTypeConfig.isBaseType(t + "")) {
			String type = Utils.getReturnTypePackage(method);
			out.println("	import " + type + ";");
			out.println("	import " + type + "Impl;");
		}

		out.println("	import flash.events.Event;");
		out.println();
		String eventName = Utils.getEventClassName(class1, method);
		out.println("	public class " + eventName + " extends Event");
		out.println("	{");
		out.println();
		out.println("		public static var ID:String = \"" + eventName + "\";");
		out.println();
		out.println("		private var o:Object;");
		out.println();
		out.println("		public function " + eventName + "(o:Object) {");
		out.println("			super(ID);");
		out.println("			this.o = o;");
		out.println("		}");
		out.println();
		out.println("		public function hasError():Boolean {");
		out.println("			return o.error != undefined;");
		out.println("		}");
		out.println();
		out.println("		public function isSuccess():Boolean {");
		out.println("			return !hasError();");
		out.println("		}");

		if (isBaseType(t)) {
			printBaseReturn(out, method);
		} else if (isVoid(t)) {

		} else {
			printBacks(out, method);
		}

		out.println("	}");
		out.println("}");

		out.flush();
		out.close();

		FileUtils.write(getPath(eventName), sw.toString());
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

	private void printBaseReturn(PrintWriter out, MethodDeclaration method) {
		String sn = method.getType() + "";
		String bt = BaseTypeConfig.parseASType(sn);
		out.println("		public function  getBack() : " + bt + "");
		out.println("		{ ");
		out.println("			return o." + Utils.getBaseFiledName(method) + ";");
		out.println("		} ");
	}

	private void printBacks(PrintWriter out, MethodDeclaration method) {
		out.println("		public function  getBack() : " + Utils.getFiledType(method) + "");
		out.println("		{ ");
		out.println("			return new " + Utils.getFiledType(method) + "Impl(o);");
		out.println("		} ");
	}

	private String getPath(String eventName) {
		String path = D.AS_SRC_PATH + D.AS_EVENT_CLASS_PACKET + "/";
		path = path.replaceAll("\\.", "/");
		return path + eventName + ".as";
	}
}
