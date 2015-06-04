package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.plugins.generator.protocol.config.FileUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IField;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;
import cn.javaplus.plugins.generator.protocol.util.Store;

public class ASActionImplGenerator extends GeneratorImpl {

	public static final String	TAIL			= ASActionInterfaceGenerator.TAIL + "Impl";
	public static final String	PACKAGE_NAME	= "action.impl";

	public ASActionImplGenerator(GeneratorConfig config) {
		super(config, Paths.AS, PACKAGE_NAME);
	}

	@Override
	public void generator() {
		List<IClass> all = config.getClazzs();
		for (IClass c : all) {
			writeToFile(c, config);
		}
	}

	private void writeToFile(IClass c, GeneratorConfig config) {
		FileUtil fu = config.getFileUtil(getSrcPath() + File.separator + c.getInterface().getName() + TAIL + ".as");
		String content = buildContent(c, config);
		fu.writeToFile(content);
	}

	private String buildContent(IClass c, GeneratorConfig config) {
		String t1 = config.getTemplet("AS" + ASActionInterfaceGenerator.TAIL + "Impl");
		t1 = t1.replaceAll("INTERFACE_NAME", c.getInterface().getName() + ASActionInterfaceGenerator.TAIL);
		t1 = t1.replaceAll("METHODS", buildMethods(c, config));
		t1 = t1.replaceAll("PACKAGE_NAME", getPackageName());
		t1 = t1.replaceAll("CLASS_NAME", c.getInterface().getName() + TAIL);

		String pack = Store.getString(Paths.PACKAGE_NAME) + "." + ASActionInterfaceGenerator.PACKAGE_NAME;

		t1 = t1.replaceAll("CALLER_INTERFACE_PACKAGE", pack);
		return t1;
	}

	private String buildMethods(IClass c, GeneratorConfig config) {

		StringBuilder sb = new StringBuilder();

		for (IMethod m : c.getMethods()) {
			sb.append(buildMethod(m, config) + "\r");
		}

		return sb.toString();
	}

	private String buildMethod(IMethod m, GeneratorConfig config) {

		String t2 = config.getTemplet("AS" + ASActionInterfaceGenerator.TAIL + "ImplMethod");
		t2 = t2.replaceAll("NAME", m.getName());
		t2 = t2.replaceAll("FILEDS", buildFields(m.getArgs()));
		t2 = t2.replaceAll("WRITE_FS", buildWriteFields(m));

		return t2;
	}

	private String buildFields(List<IField> args) {
		StringBuilder sb = new StringBuilder();
		Iterator<IField> it = args.iterator();
		while (it.hasNext()) {
			IField f = it.next();
			sb.append("" + f.getName() + ":" + f.getType());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

}
