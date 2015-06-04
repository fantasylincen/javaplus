package cn.javaplus.build.protocol.generator;
//package cn.javaplus.crazy.protocol.generator;
//
//import japa.parser.ast.body.MethodDeclaration;
//import japa.parser.ast.body.Parameter;
//
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import cn.javaplus.comunication.annotations.Communication;
//import cn.javaplus.crazy.protocol.define.D;
//import cn.javaplus.crazy.protocol.util.Utils;
//import cn.javaplus.file.FileUtils;
//import cn.javaplus.random.Fetcher;
//import cn.javaplus.util.Util;
//
//import com.google.common.collect.Lists;
//
///**
// * XXXService.as生成器
// * 
// * @author 林岑
// * 
// */
//public class ProtocolInterfaceGenerator {
//
//	public void generate(List<JavaFile> cs) {
//
//		for (JavaFile c : cs) {
//			if (!c.containsAnnotation(Communication.class)) {
//				continue;
//			}
//			StringWriter sw = new StringWriter();
//			PrintWriter out = new PrintWriter(sw);
//
//			buildHead(out, c);
//			buildMethods(out, c);
//			buildTail(out);
//
//			out.flush();
//			out.close();
//
//			FileUtils.write(getPath(c), sw.toString());
//		}
//	}
//
//	private void buildMethods(PrintWriter out, JavaFile c) {
//		List<MethodDeclaration> methods = c.getMethods();
//		for (MethodDeclaration m : methods) {
//			if (Utils.isSetUserMethod(m)) {
//				continue;
//			}
//			out.println(Utils.getComment(m));
//			String as = buildArgs(m);
//			String name = m.getName();
//			out.println("		void " + name + "(" + as + ");");
//		}
//	}
//
//	private String buildArgs(MethodDeclaration m) {
//
//		List<Parameter> all = m.getParameters();
//
//		if (all == null) {
//			all = Lists.newArrayList();
//		}
//
//		final AtomicInteger i = new AtomicInteger(0);
//		Fetcher<Parameter, String> fetcher = new Fetcher<Parameter, String>() {
//
//			@Override
//			public String get(Parameter t) {
//				String sName = t.getType() + "";
//				return sName + " arg" + i.getAndAdd(1);
//			}
//		};
//
//		return Util.Collection.linkWith(", ", all, fetcher);
//	}
//
//	private void buildTail(PrintWriter out) {
//		out.println("	}");
//		out.println("}");
//	}
//
//	private void buildHead(PrintWriter out, JavaFile c) {
//		out.println("package " + D.CLIENT_SRC_PATH);
//		out.println("{");
//		out.println("	import flash.events.IEventDispatcher;");
//		out.println("");
//		out.println("	public interface " + c.getType().getName()
//				+ " extends IEventDispatcher");
//		out.println("	{");
//	}
//
//	private String getPath(JavaFile c) {
//		return D.CLIENT_SRC_FILES_PATH + "/" + c.getType().getName() + ".java";
//	}
//
// }
