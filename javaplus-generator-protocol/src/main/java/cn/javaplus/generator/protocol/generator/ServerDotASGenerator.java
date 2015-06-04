package cn.javaplus.generator.protocol.generator;

import japa.parser.ast.body.TypeDeclaration;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.file.FileUtils;
import cn.javaplus.generator.protocol.define.D;
import cn.javaplus.generator.protocol.generator.util.Utils;
import cn.javaplus.generator.protocol.util.ProtocolClassFilter;
import cn.javaplus.java.JavaFile;
import cn.javaplus.util.Util;

/**
 *
 * 生成Server.as
 *
 * @author 林岑
 *
 */
public class ServerDotASGenerator {

	private List<JavaFile> classz;

	public void generate(List<JavaFile> cs) {
		classz = new ProtocolClassFilter().filter(cs);

		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		buildHead(out);
		buildFields(out);
		buildSocketSetter(out);
		buildGetters(out);
		buildTail(out);

		out.flush();
		out.close();

		FileUtils.write(getPath(), sw.toString());
	}

	private void buildGetters(PrintWriter out) {
		for (JavaFile c : classz) {
			buildGetter(out, c);
		}
	}

	private void buildGetter(PrintWriter out, JavaFile c) {
		TypeDeclaration type = c.getType();
		String UNAME = type.getName();
		String LNAME = Util.Str.firstToLowerCase(UNAME);
		out.println(Utils.getComment(c));
		out.println("		public static function get " + LNAME + "():" + UNAME);
		out.println("		{");
		out.println("			return _" + LNAME + ";");
		out.println("		}");
	}

	private void buildSocketSetter(PrintWriter out) {
		out.println("		public static function set socket(value:Socket):void");
		out.println("		{");

		for (JavaFile c : classz) {
			buildSet(out, c);
		}

		out.println("			");
		out.println("		}");
	}

	private void buildSet(PrintWriter out, JavaFile c) {
		String LNAME = Util.Str.firstToLowerCase(c.getType().getName());
		out.println("			_" + LNAME + ".socket = value;");
	}

	private void buildTail(PrintWriter out) {
		out.println("	}");
		out.println("}");
	}

	private void buildFields(PrintWriter out) {
		for (JavaFile f : classz) {
			String UNAME = f.getType().getName();
			String LNAME = Util.Str.firstToLowerCase(UNAME);
			String t = "		private static var _" + LNAME + ":" + UNAME
					+ "Impl = new " + UNAME + "Impl();";
			out.println(t);
		}
	}

	private void buildHead(PrintWriter out) {
		out.println("package " + D.SERVER_DOT_AS_PACKET);
		out.println("{");
		out.println("	import flash.net.Socket;");
		out.println("");
		out.println("	public class Server");
		out.println("	{");
	}

	private String getPath() {
		String path = D.AS_SRC_PATH + D.SERVER_DOT_AS_PACKET + "/";
		path = path.replaceAll("\\.", "/");
		return path + "Server.as";
	}
}
