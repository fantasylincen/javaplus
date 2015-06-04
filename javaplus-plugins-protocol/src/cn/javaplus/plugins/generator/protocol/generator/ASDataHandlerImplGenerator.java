package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.List;

import cn.javaplus.plugins.generator.protocol.config.FileUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class ASDataHandlerImplGenerator extends GeneratorImpl {

	public ASDataHandlerImplGenerator(GeneratorConfig config) {
		super(config, Paths.AS, "handler");
	}

	@Override
	public void generator() {
		FileUtil fu = config.getFileUtil(getSrcPath() + File.separator + "Handlers.as");

		String content = config.getTemplet("ASHandlerManager");
		content = content.replaceAll("HANDLER_GETTERS", buildGetters(config));
		content = content.replaceAll("PACKAGE_NAME", getPackageName());

		String pack = getPackageName();
		content = content.replaceAll("HANDLER_PACK", pack);

		fu.writeToFile(content);
	}

	private String buildGetters(GeneratorConfig config) {
		StringBuilder sb = new StringBuilder();
		List<IClass> all = config.getClazzs();
		for (IClass c : all) {
			String name = c.getInterface().getName();
			sb.append("		function get" + name + "Handler():" + name + "Handler;\r");
		}
		return sb.toString();
	}
}
