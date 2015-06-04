package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.List;

import cn.javaplus.plugins.generator.protocol.config.FileUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;
import cn.javaplus.plugins.generator.protocol.util.Store;

public class ASActionFactoryGenerator extends GeneratorImpl {

	public ASActionFactoryGenerator(GeneratorConfig config) {
		super(config, Paths.AS, "manager");
	}

	@Override
	public void generator() {
		FileUtil fu = config.getFileUtil(getSrcPath() + File.separator + ASActionInterfaceGenerator.TAIL + "Factory.as");

		String content = config.getTemplet("AS" + ASActionInterfaceGenerator.TAIL + "Factory");
		content = content.replaceAll("CALLER_GETTERS", buildGetters(config));
		content = content.replaceAll("PACKAGE_NAME", getPackageName());

		String pack = Store.getString(Paths.PACKAGE_NAME) + "." + ASActionInterfaceGenerator.PACKAGE_NAME;
		content = content.replaceAll("CALLER_PACKAGE", pack);

		pack = Store.getString(Paths.PACKAGE_NAME) + "." + ASActionImplGenerator.PACKAGE_NAME;
		content = content.replaceAll("CALLER_IMPLEMENT_PACKAGE", pack);

		content = content.replaceAll("HANDLER_FILEDS", buildFileds());
		content = content.replaceAll("SET_SOCKETS", buildSetSockets());

		fu.writeToFile(content);
	}

	private String buildSetSockets() {
		StringBuilder sb = new StringBuilder();
		List<IClass> all = config.getClazzs();
		StringBuilder fs = new StringBuilder();
		for (IClass c : all) {

			fs.append("			_" + c.getBeanName() + ".socket = value;\r");
		}
		return fs + "";
	}

	private String buildFileds() {

		List<IClass> all = config.getClazzs();
		StringBuilder fs = new StringBuilder();
		for (IClass c : all) {
			String className = c.getInterface().getName() + ASActionInterfaceGenerator.TAIL + "Impl";

			fs.append("		private static var _" + c.getBeanName() + ":" + className + " = new " + className + "();\r");
		}
		return fs + "";
	}

	private String buildGetters(GeneratorConfig config) {
		StringBuilder sb = new StringBuilder();
		List<IClass> all = config.getClazzs();
		for (IClass c : all) {
			String name = c.getInterface().getName();
			sb.append("		public static function get " + c.getBeanName() + "():" + name + ASActionInterfaceGenerator.TAIL + "\r");
			sb.append("		{\r");

			sb.append("			return _" + c.getBeanName() + "\r");

			sb.append("		}\r");
			sb.append("\r");
		}
		return sb.toString();
	}

}
