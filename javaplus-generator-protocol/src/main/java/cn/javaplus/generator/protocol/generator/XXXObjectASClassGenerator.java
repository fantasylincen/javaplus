package cn.javaplus.generator.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.type.Type;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.file.FileUtils;
import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.util.ValueObjectClassFilter;
import cn.javaplus.java.JavaFile;

/**
 * 生成所有的 Java 通信接口 的实现到AS目录
 *
 * @author 林岑
 */
public class XXXObjectASClassGenerator {

	public void generate(List<JavaFile> cs) {
		List<JavaFile> all = new ValueObjectClassFilter().filter(cs);
		for (JavaFile c : all) {
			copy(c);
		}
	}

	private static void copy(JavaFile c) {

		TypeDeclaration t = c.getType();
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		out.println("package " + c.getPackage());
		out.println("{");
		String name = t.getName();
		out.println("	public class " + name + "Impl implements " + name);
		out.println("	{");
		out.println("		private var o:Object;");
		out.println("		public function " + name + "Impl(o:Object) {");
		out.println("	 		this.o = o;");
		out.println("	 	}");

		List<MethodDeclaration> methods = c.getMethods();
		for (MethodDeclaration m : methods) {
			if (Utils.isSetUserMethod(m)) {
				continue;
			}
			buildGetter(m, out);
		}

		out.println("	}");
		out.println("}");
		out.flush();
		FileUtils.write(getPath(c), sw.toString());
	}


	private static void buildGetter(MethodDeclaration met, PrintWriter out) {

		if (Utils.isCollection(met)) {
			buildCollectionGetter(met, out);
		} else {
			buildNormalGetter(met, out);
		}

	}

	private static void buildCollectionGetter(MethodDeclaration met, PrintWriter out) {

		String filedType = Utils.getASFiledType(met);

		String elementTypeName = Utils.getElementTypeName(met);

		out.println("		public function " + Utils.getGetterName(met) + " () : " + filedType + " {");
		out.println("			var list : " + filedType + " = new " + filedType + "();");
		out.println("			var ass:Array = o." + Utils.getBaseFiledName(met) + ";");
		out.println("			for each (var i:Object in ass)");
		out.println("			{");
		out.println("				list.push(new " + elementTypeName + "Impl(i));");
		out.println(" 			}");
		out.println(" 			return list;");
		out.println(" 		}");
		out.println();
	}

	private static void buildNormalGetter(MethodDeclaration met, PrintWriter out) {

		String filedType = Utils.getASFiledType(met);

		out.println("		public function " + Utils.getGetterName(met) + " () : " + filedType + " {");

		Type type = met.getType();
		String fName = "o." + Utils.getBaseFiledName(met);

		if (Utils.isBaseType(type)) {
			out.println("			return " + fName + ";");
		} else {

			out.println("			if(" + fName + " == null) {");
			out.println("				return null;");
			out.println("			}");
			out.println("			return new " + type + "Impl(" + fName + ");");
		}

		out.println(" 		}");
		out.println();
	}

	private static String getPath(JavaFile c) {
		String path = D.AS_SRC_PATH + c.getPackage() + "/";
		path = path.replaceAll("\\.", "/");
		return path + c.getClassSimpleName() + "Impl.as";
	}
}
