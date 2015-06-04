package cn.javaplus.build.protocol.generator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import cn.javaplus.build.protocol.util.Utils;
import cn.javaplus.comunication.annotations.Communication;

public class CheckTimeMethodGenerator {

	private List<JavaFile> classz;

	public String generate(List<JavaFile> cs) {
		classz = cs;

		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		build(out);

		out.flush();
		out.close();
		return sw.toString();
	}

	private void build(PrintWriter out) {
		for (JavaFile c : classz) {
			if (!c.containsAnnotation(Communication.class)) {
				continue;
			}

			String name = Utils.getHandlerField(c);
			out.println("		r.add(" + name + ".checkTimeOut());");
		}
	}

}
