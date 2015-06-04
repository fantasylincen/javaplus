package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.List;

import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class JavaMessageFactoryGenerator extends GeneratorImpl {

	public JavaMessageFactoryGenerator(GeneratorConfig config) {
		super(config, Paths.JAVA, "messagesender");
	}

	@Override
	public void generator() {

		String content = config.getTemplet("JavaMessageFactory");

		content = content.replaceAll("GETTERS", buildGetters());

		content = content.replaceAll("PACKAGE_NAME", getPackageName());

		String path = getSrcPath();

		config.getFileUtil(path + File.separator + "MessageFactory.java").writeToFile(content);
	}

	private String buildGetters() {

		List<IClass> all = config.getClazzs();

		StringBuilder gts = new StringBuilder();

		for (IClass c : all) {

			gts.append(buildGetter(c));
		}

		return gts + "";
	}

	private String buildGetter(IClass c) {

		String temp = config.getTemplet("JavaMessageFactoryGetter");

		temp = temp.replaceAll("CLASS_NAME", c.getInterfaceName());

		return temp;
	}
}
