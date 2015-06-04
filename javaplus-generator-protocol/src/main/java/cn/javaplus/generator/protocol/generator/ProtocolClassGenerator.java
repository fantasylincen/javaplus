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

public class ProtocolClassGenerator {

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
		List<MethodDeclaration> all = c.getMethods();
		for (MethodDeclaration m : all) {

			if(Utils.isSetUserMethod(m)) {
				continue;
			}
			out.println("		public function " + m.getName() + "(" + buildArgs(m) + "):void {");
			out.println("			var p:Packet = new PacketImpl(_socket);");
			putBody(out, c, m);
			out.println("			p.writeInt(1800000000);");
			out.println("			p.writeUTF(JSON.stringify(obj));");
			out.println("			p.send();");
			out.println("		}");
		}
	}

	private void putBody(PrintWriter out, JavaFile c, MethodDeclaration m) {
		out.println("			var obj:Object = new Object();");
		out.println("			obj.className = \"" + c.getClassFullName() + "\";");
		out.println("			obj.methodName = \"" + m.getName() + "\";");
		out.println("			obj.args = new Array();");

		List<Parameter> ts = m.getParameters();

		ts = Util.Collection.nullToEmpty(ts);

		for (int j = 0; j < ts.size(); j++) {
			out.println("			obj.args[" + j + "] = arg" + j + ";");
		}
	}

	private String buildArgs(MethodDeclaration m) {

		List<Parameter> all = m.getParameters();

		if(all == null) {
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
		out.println("package " + D.COMMUNICATION_CLASS_PACKET);
		out.println("{");
		out.println("	import flash.net.Socket;");
		out.println("	import flash.events.EventDispatcher;");
		out.println("	import cn.javaplus.packets.Packet;");
		out.println("	import cn.javaplus.packets.PacketImpl;");
		out.println("");
		out.println("	public class " + c.getClassSimpleName() + "Impl" + " extends EventDispatcher implements " + c.getClassSimpleName());
		out.println("	{");
		out.println("		public function " + c.getClassSimpleName() + "Impl()");
		out.println("		{");
		out.println("		}");
		out.println("		private var _socket:Socket;");
		out.println("		public function set socket(socket:Socket):void {");
		out.println("			this._socket = socket;");
		out.println("		}");
	}

	private String getPath(JavaFile c) {
		String path = D.AS_SRC_PATH + D.COMMUNICATION_CLASS_PACKET + "/";
		path = path.replaceAll("\\.", "/");
		return path + c.getClassSimpleName() + "Impl.as";
	}
}
