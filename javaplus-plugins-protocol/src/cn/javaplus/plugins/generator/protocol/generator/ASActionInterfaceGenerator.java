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

public class ASActionInterfaceGenerator extends GeneratorImpl {

	static final String			TAIL			= "Action";
	public static final String	PACKAGE_NAME	= "action";

	public ASActionInterfaceGenerator(GeneratorConfig config) {
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

	private String buildContent(final IClass c, GeneratorConfig config) {
		String t1 = config.getTemplet("AS" + ASActionInterfaceGenerator.TAIL + "Interface");
		t1 = t1.replaceAll("INTERFACE_NAME", c.getInterface().getName() + TAIL);
		t1 = t1.replaceAll("METHODS", buildMethods(c, config));
		t1 = t1.replaceAll("PACKAGE_NAME", getPackageName());
		t1 = t1.replaceAll("IMPORTS", buildImports(c));
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

		String t2 = config.getTemplet("AS" + ASActionInterfaceGenerator.TAIL + "InterfaceMethod");
		t2 = t2.replaceAll("METHOD_DOC", m.getMethodDoc());
		t2 = t2.replaceAll("ARGS_DOC", buildArgsDoc(m));
		t2 = t2.replaceAll("NAME", m.getName());
		t2 = t2.replaceAll("FILEDS", buildFields(m.getArgs()));

		return t2;
	}

	private String buildArgsDoc(IMethod m) {
		String s = "		 * @param ";

		StringBuilder sb = new StringBuilder();
		Iterator<IField> it = m.getArgs().iterator();
		while (it.hasNext()) {
			IField f = it.next();
			sb.append(s + f.getName() + " " + f.getDoc());
			if (it.hasNext()) {
				sb.append("\r");
			}
		}

		return sb.toString();
	}

	private String buildFields(List<IField> args) {
		StringBuilder sb = new StringBuilder();
		Iterator<IField> it = args.iterator();
		while (it.hasNext()) {

			IField f = it.next();
			sb.append(f.getName() + ":" + f.getType());
			if (it.hasNext()) {
				sb.append(", ");
			}

		}
		return sb.toString();
	}
}
