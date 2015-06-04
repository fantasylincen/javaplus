package cn.javaplus.build.protocol.generator;
//package cn.javaplus.crazy.protocol.generator;
//
//import japa.parser.ast.body.MethodDeclaration;
//import japa.parser.ast.body.TypeDeclaration;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.List;
//
//import cn.javaplus.comunication.annotations.CommunicationDto;
//import cn.javaplus.crazy.protocol.define.D;
//import cn.javaplus.crazy.protocol.util.Utils;
//import cn.javaplus.file.FileUtils;
//
///**
// * 拷贝所有的 Java 通信接口到AS目录
// *
// * @author 林岑
// */
//public class ValueObjectJavaInterfaceDuplicator {
//
//	public void copy(List<JavaFile> cs) {
//		for (JavaFile c : cs) {
//			if (c.containsAnnotation(CommunicationDto.class)) {
//				copy(c);
//			}
//		}
//	}
//
//	private static void copy(JavaFile c) {
//
//		TypeDeclaration t = c.getType();
//
//		StringWriter sw = new StringWriter();
//		PrintWriter out = new PrintWriter(sw);
//
//		out.println("package " + D.CLIENT_FILES_PACKAGE + ";");
//		// buildImport(out, c);
//		out.println(Utils.getComment(c));
//		out.println("public interface " + t.getName() + "{");
//
//		List<MethodDeclaration> methods = c.getMethods();
//		for (MethodDeclaration m : methods) {
//			if(Utils.isSetUserMethod(m)) {
//				continue;
//			}
//			build(m, out);
//		}
//
//		out.println("}");
//		out.flush();
//		FileUtils.write(getPath(c), sw.toString());
//	}
//
//	private static void build(MethodDeclaration m, PrintWriter out) {
//		out.print(Utils.getComment(m));
//		out.print("		"+Utils.getASFiledType(m)+" ");
//		out.print(Utils.getGetterName(m));
//		out.print(" () { ");
//		out.println();
//	}
//
//	private static String getPath(JavaFile c) {
//		return D.CLIENT_SRC_FILES_PATH + "/" + c.getClassSimpleName() + ".java";
//	}
// }
