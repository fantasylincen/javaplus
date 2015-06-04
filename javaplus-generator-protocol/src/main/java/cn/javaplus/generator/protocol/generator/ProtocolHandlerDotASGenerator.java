package cn.javaplus.generator.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.file.FileUtils;
import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.util.ProtocolClassFilter;
import cn.javaplus.java.JavaFile;
import cn.javaplus.util.Util;

public class ProtocolHandlerDotASGenerator {
	public void generate(List<JavaFile> cs) {

		List<JavaFile> classz = new ProtocolClassFilter().filter(cs);

		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		build(classz, out);

		out.flush();
		out.close();

		FileUtils.write(getPath(), sw.toString());
	}

	private void build(List<JavaFile> classz, PrintWriter out) {
		out.println("package " + D.AS_PROTOCOL_HANDLER_PACKET);
		out.println("{");
		out.println("	import flash.utils.ByteArray;");
		out.println("	public class ProtocolHandler");
		out.println("	{");
		out.println("		public function ProtocolHandler()");
		out.println("		{");
		out.println("		}");
		out.println("		public function onData(packet:ByteArray):void {");
		out.println("			var data:ByteArray = new ByteArray();");
		out.println("			packet.readBytes(data);");
		out.println("			var d:String = data.readUTF();");
		out.println("			var obj:Object = JSON.parse(d);");
		out.println("			");
		out.println("			switch(obj.className)");
		out.println("			{");


		for (JavaFile class1 : classz) {
			printClass(class1, out);
		}

		out.println("			}");

		out.println("		}");
		out.println("	}");
		out.println("}");
	}


	private void printClass(JavaFile class1, PrintWriter out) {
		out.println("				case \"" + class1.getClassFullName() + "\":");
		out.println("				{");
		out.println("					switch(obj.methodName)");
		out.println("					{");

		List<MethodDeclaration> all = class1.getMethods();

		for (MethodDeclaration method : all) {
			if(Utils.isSetUserMethod(method)) {
				continue;
			}
			printMethod(class1, out, method);
		}

		out.println("					}");
		out.println("					break;");
		out.println("				}");

	}

	private void printMethod(JavaFile class1, PrintWriter out, MethodDeclaration method) {
		out.println("						case \"" + method.getName() + "\":"	);
		out.println("						{");
		out.println("							Server." + getFieldName(class1) + ".dispatchEvent(" + buildNewEvent(class1, method) +   ");");
		out.println("							break;");
		out.println("						}");
	}

	private String buildNewEvent(JavaFile class1, MethodDeclaration method) {
		return "new " + Utils.getEventClassName(class1, method) + "(obj)";
	}

	private String getFieldName(JavaFile class1) {
		return Util.Str.firstToLowerCase(class1.getClassSimpleName());
	}

	private String getPath() {
		String path = D.AS_SRC_PATH + D.AS_PROTOCOL_HANDLER_PACKET + "/";
		path = path.replaceAll("\\.", "/");
		return path + "ProtocolHandler.as";
	}
}
