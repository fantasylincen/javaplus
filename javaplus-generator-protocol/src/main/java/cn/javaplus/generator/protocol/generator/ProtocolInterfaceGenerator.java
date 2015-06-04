package cn.javaplus.generator.protocol.generator;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.javaplus.file.FileUtils;
import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.util.ProtocolClassFilter;
import cn.javaplus.java.JavaFile;
import cn.javaplus.random.Fetcher;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

/**
 * XXXService.as生成器
 *
 * @author 林岑
 *
 */
public class ProtocolInterfaceGenerator {

	public void generate(List<JavaFile> cs) {

		List<JavaFile> classz = new ProtocolClassFilter().filter(cs);

		for (JavaFile c : classz) {
			StringWriter sw = new StringWriter();
			PrintWriter out = new PrintWriter(sw);

			buildHead(out, c);
			buildMethods(out, c);
			buildTail(out);

			out.flush();
			out.close();

			FileUtils.write(getPath(c), sw.toString());
		}
	}

	private void buildMethods(PrintWriter out, JavaFile c) {
		List<MethodDeclaration> methods = c.getMethods();
		for (MethodDeclaration m : methods) {
			if(Utils.isSetUserMethod(m)) {
				continue;
			}
			out.println(Utils.getComment(m));
			String as = buildArgs(m);
			String name = m.getName();
			out.println("		function " + name + "(" + as + "):void;");
		}
	}

	private String buildArgs(MethodDeclaration m) {

		List<Parameter> all = m.getParameters();

		if (all == null) {
			all = Lists.newArrayList();
		}

		final AtomicInteger i = new AtomicInteger(0);
		Fetcher<Parameter, String> fetcher = new Fetcher<Parameter, String>() {

			@Override
			public String get(Parameter t) {
				String sName = t.getType() + "";
				return "arg" + i.getAndAdd(1) + ":" + sName;
			}
		};

		return Util.Collection.linkWith(", ", all, fetcher);
	}

	private void buildTail(PrintWriter out) {
		out.println("	}");
		out.println("}");
	}

	private void buildHead(PrintWriter out, JavaFile c) {
		out.println("package " + D.COMMUNICATION_INTERFACE_PACKET);
		out.println("{");
		out.println("	import flash.events.IEventDispatcher;");
		out.println("");
		out.println("	public interface " + c.getType().getName()
				+ " extends IEventDispatcher");
		out.println("	{");
	}

	private String getPath(JavaFile c) {
		String path = D.AS_SRC_PATH + D.COMMUNICATION_INTERFACE_PACKET + "/";
		path = path.replaceAll("\\.", "/");
		return path + c.getType().getName() + ".as";
	}

}
