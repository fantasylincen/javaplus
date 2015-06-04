package cn.javaplus.generator.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.TypeDeclaration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.file.FileUtils;
import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.util.ValueObjectClassFilter;
import cn.javaplus.java.JavaFile;

/**
 * 拷贝所有的 Java 通信接口到AS目录
 *
 * @author 林岑
 */
public class XXXObjectASInterfaceDuplicator {

	public void copy(List<JavaFile> cs) {
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
		out.println(Utils.getComment(c));
		out.println("	public interface " + t.getName());
		out.println("	{");

		List<MethodDeclaration> methods = c.getMethods();
		for (MethodDeclaration m : methods) {
			if(Utils.isSetUserMethod(m)) {
				continue;
			}
			build(m, out);
		}

		out.println("	}");
		out.println("}");
		out.flush();
		FileUtils.write(getPath(c), sw.toString());
	}

	private static void build(MethodDeclaration m, PrintWriter out) {
		out.print(Utils.getComment(m));
		out.print("		function ");
		out.print(Utils.getGetterName(m));
		out.print(" () : ");
		out.print(Utils.getASFiledType(m) + ";");
		out.println();
	}

	private static String getPath(JavaFile c) {
		String path = D.AS_SRC_PATH + c.getPackage() + "/";
		path = path.replaceAll("\\.", "/");
		return path + c.getClassSimpleName() + ".as";
	}
}
