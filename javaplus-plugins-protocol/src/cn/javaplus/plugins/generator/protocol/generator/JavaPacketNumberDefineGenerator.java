package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;

import _util.StringUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class JavaPacketNumberDefineGenerator extends GeneratorImpl {

	public JavaPacketNumberDefineGenerator(GeneratorConfig config) {
		super(config, Paths.JAVA, "handler");
	}

	@Override
	public void generator() {

		String content = config.getTemplet("JavaPacketNumberDefine");

		content = content.replaceAll("VALUES", buildConsts());

		content = content.replaceAll("PACKAGE_NAME", getPackageName());

		String path = getSrcPath();

		config.getFileUtil(path + File.separator + "PacketDefine.java").writeToFile(content);
	}

	private String buildConsts() {

		StringBuilder sb = new StringBuilder();

		for (IClass c : config.getClazzs()) {

			for (IMethod m : c.getMethods()) {

				sb.append("	/**\r");

				sb.append("	 *" + m.getMethodDoc() + "\r");

				sb.append("	 */\r");

				sb.append("	public static final int " + StringUtil.firstToUpperCase(c.getInterfaceName()) + StringUtil.firstToUpperCase(m.getName()) + " = " + m.getId() + ";\r");
			}
		}

		return sb.toString();
	}

}
