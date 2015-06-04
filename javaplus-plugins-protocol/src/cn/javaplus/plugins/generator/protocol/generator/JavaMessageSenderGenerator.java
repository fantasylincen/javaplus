package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;

import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IField;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class JavaMessageSenderGenerator extends GeneratorImpl {

	public JavaMessageSenderGenerator(GeneratorConfig config) {
		super(config, Paths.JAVA, "messagesender");
	}

	@Override
	public void generator() {

		for (IClass c : config.getClazzs()) {

			generate(c);
		}

	}

	private void generate(IClass c) {

		String content = config.getTemplet("JavaMessageSender");

		content = content.replaceAll("METHODS", buildMethods(c));

		content = content.replaceAll("PACKAGE_NAME", getPackageName());

		content = content.replaceAll("CLASS_NAME", c.getInterfaceName());

		String path = getSrcPath();

		config.getFileUtil(path + File.separator + c.getInterfaceName() + "MessageSender.java").writeToFile(content);
	}

	/**
	 * @param c
	 * @return
	 */
	private String buildMethods(IClass c) {

		StringBuilder sb = new StringBuilder();

		for (IMethod m : c.getServerToClientMethods()) {

			sb.append(build(m));
		}

		return sb.toString();
	}

	private String build(IMethod m) {

		String temp = config.getTemplet("JavaMessageSenderMethod");

		temp = temp.replaceAll("RETURN_TYPE", m.getReturn().getType());

		temp = temp.replaceAll("METHOD_NAME", m.getName());

		temp = temp.replaceAll("ARGS", buildArgs(m));

		return temp;

	}

	private String buildArgs(IMethod m) {

		StringBuilder sb = new StringBuilder();

		for (IField f : m.getArgs()) {

			String p = config.getJavaPackage(f.getTypeSimple());

			if(p == null) {
				p = f.getType();
			}

			sb.append(", " + p + " " + f.getName());
		}

		return sb.toString();
	}
}
