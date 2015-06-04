package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;

import _util.BaseTypeConfig;
import _util.StringUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IField;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class JavaMessageSenderImplGenerator extends GeneratorImpl {

	public JavaMessageSenderImplGenerator(GeneratorConfig config) {
		super(config, Paths.JAVA, "messagesender");
	}

	@Override
	public void generator() {

		for (IClass c : config.getClazzs()) {

			generate(c);
		}

	}

	private void generate(IClass c) {

		String content = config.getTemplet("JavaMessageSenderImpl");

		content = content.replaceAll("METHODS", buildMethods(c));

		content = content.replaceAll("PACKAGE_NAME", getPackageName());

		content = content.replaceAll("CLASS_NAME", c.getInterfaceName());

		String path = getSrcPath();

		config.getFileUtil(path + File.separator + c.getInterfaceName() + "MessageSenderImpl.java").writeToFile(content);
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

		String temp = config.getTemplet("JavaMessageSenderImplMethod");

		temp = temp.replaceAll("RETURN_TYPE", m.getReturn().getType());

		temp = temp.replaceAll("METHOD_NAME", m.getName());

		temp = temp.replaceAll("ARGS", buildArgs(m));

		temp = temp.replaceAll("PACKET_NUMBER", m.getId() + "");

		temp = temp.replaceAll("PUTS", buildPuts(m));

		return temp;
	}

	private String buildPuts(IMethod m) {

		StringBuilder sb = new StringBuilder();

		for (IField f : m.getArgs()) {

			if (BaseTypeConfig.contains(f.getType())) {
				sb.append("\t\tp.put" + StringUtil.firstToUpperCase(f.getType()) + "(" + f.getName() + ");");
			} else {
				sb.append("\t\tp.put(" + f.getName() + ");");
			}

		}

		return sb.toString();
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
