package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.List;

import _util.StringUtil;
import cn.javaplus.plugins.generator.protocol.config.FileUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.config.IReturn;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class ASHandlerInterfaceGenerator extends GeneratorImpl {

	static final String	TAIL	= "Handler";

	public ASHandlerInterfaceGenerator(GeneratorConfig config) {
		super(config, Paths.AS, "handler");
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
		String t1 = config.getTemplet("ASHandlerInterface");
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

		String t2 = config.getTemplet("ASHandlerInterfaceMethod");
		t2 = t2.replaceAll("METHOD_DOC", m.getMethodDoc());
		t2 = t2.replaceAll("ARGS_DOC", buildArgsDoc(m));
		t2 = t2.replaceAll("NAME", buildMethodName(m.getName()));
		t2 = t2.replaceAll("FILEDS", buildFields(m.getReturn()));

		return t2;
	}

	private String buildFields(IReturn r) {
		if (r.isVoid()) {
			return "";
		}
		return "data:" + r.getTypeSimple();
	}

	public static String buildMethodName(String name) {
		return "on" + StringUtil.firstToUpperCase(name) + "Back";
	}

	private String buildArgsDoc(IMethod m) {
		if (m.getReturn().isVoid()) {
			return "";
		}
		return "		 * @param data " + m.getReturn().getReturnDoc() + "\r";
	}

}
