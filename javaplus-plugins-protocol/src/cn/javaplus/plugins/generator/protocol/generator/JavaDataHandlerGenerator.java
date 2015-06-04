package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.plugins.console.Debuger;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IBeanAble;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IField;
import cn.javaplus.plugins.generator.protocol.config.IInterface;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.config.MethodBack;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;
import cn.javaplus.plugins.util.StringUtils;

public class JavaDataHandlerGenerator extends GeneratorImpl {

	public JavaDataHandlerGenerator(GeneratorConfig config) {
		super(config, Paths.JAVA, "handler");
	}

	@Override
	public void generator() {

		String content = config.getTemplet("JavaHandler");

		Debuger.debug("JavaDataHandlerGenerator.generator()");

		content = content.replaceAll("PACKET_CASES", buildCases());

		// content = content.replaceAll("OTHER_RESPONSE_CASES",
		// buildResponses());

		content = content.replaceAll("PACKAGE_NAME", getPackageName());

		String path = getSrcPath();

		config.getFileUtil(path + File.separator + "DataHandlerImpl.java").writeToFile(content);
	}

	private String buildCases() {

		List<IClass> all = config.getClazzs();

		StringBuilder cases = new StringBuilder();

		for (IClass c : all) {

			for (IMethod m : c.getMethods()) {

				cases.append(buildCase(c, m, config));
			}
		}

		return cases + "";
	}

	/**
	 * 鏋勫缓鍗曚釜鏂规硶鐨勪竴涓狢ASE, 杩欎釜CASE閲岄潰鍖呭惈浜嗗涓繑鍥�
	 *
	 * @param m
	 * @return
	 */
	private String buildResponseCase(IMethod m) {

		StringBuilder cases = new StringBuilder();

		List<MethodBack> mbs = m.getBacks();

		for (MethodBack mb : mbs) {

			cases.append(buildResponse(mb) + "\r");
		}

		return cases + "";
	}

	private Object buildResponse(MethodBack mb) {

		String content = config.getTemplet("JavaHandlerResponseCase");

		IMethod m = mb.getMethod();

		IInterface c = mb.getClazz();

		content = buildCase(m, c, content);

		content = content.replace("FIELDS", buildFileds(mb.getArgs()));

		return content;
	}

	private CharSequence buildFileds(List<String> args) {

		Iterator<String> it = args.iterator();

		String s = "";

		while (it.hasNext()) {

			String arg = it.next();

			s += arg;

			if (it.hasNext()) {

				s += ",";
			}
		}

		return s;
	}

	private String buildCase(IMethod m, IBeanAble c, String content) {

		content = content.replace("METHOD_NAME", m.getName());

		content = content.replace("INTERFACE_NAME", c.getInterfaceName());

		content = content.replace("BEAN_NAME", c.getBeanName() + "Service");

		content = content.replaceAll("METHOD_ID", m.getId() + "");

		if (m.getReturn().isVoid()) {

			content = content.replaceAll("RETURN_TYPE data = ", "");

			content = content.replaceAll("PUT_DATA", "");

		} else {

			content = buildReturnToClient(m, content);
		}

		return content;
	}

	private String buildReturnToClient(IMethod m, String content) {

		content = content.replaceAll("RETURN_TYPE", config.getJavaPackage(m.getReturn().getTypeSimple()));

		if (m.getReturn().isBaseType()) {

			String put = StringUtils.firstToUpperCase(m.getReturn().getType());

			content = content.replaceAll("PUT_DATA", "response.put" + put + "(data);");

		} else {

			content = buildAbstractMessageReturn(m, content);
		}

		return content;
	}

	private String buildAbstractMessageReturn(IMethod m, String content) {


		content = content.replaceAll("PUT_DATA", "response.put(data);");

		return content;
	}


	private String buildCase(IClass c, IMethod m, GeneratorConfig config) {

		String content = config.getTemplet("JavaHandlerCase");

		content = buildCase(m, c, content);

		content = content.replace("PACKET_NUMBER", m.getId() + "");

		content = content.replace("READ_FILEDS", buildReadFileds(m.getArgs()));

		content = content.replace("INSERT_FIELDS", buildFiledsLinkBy(", ", m.getArgs()));

		content = content.replace("RESPONSES", buildResponseCase(m));

		return content;
	}

	/**
	 * 灏哸rgs涓殑鎵�湁鎴愬憳鐨勫悕瀛楃敤sp鍒嗛殧锛�杩炴垚涓�釜瀛楃涓�
	 *
	 * @param sp
	 *            鍒嗛殧绗�
	 * @param args
	 * @return
	 */
	private String buildFiledsLinkBy(String sp, List<IField> args) {

		StringBuilder sb = new StringBuilder();

		Iterator<IField> it = args.iterator();

		while (it.hasNext()) {

			IField f = it.next();

			sb.append(f.getName());

			if (it.hasNext()) {

				sb.append(sp);
			}
		}

		return sb.toString();
	}

	private String buildReadFileds(List<IField> args) {

		StringBuilder sb = new StringBuilder();

		for (IField f : args) {

			sb.append("\t\t\t" + f.getType() + " " + f.getName() + " = " + "in.get" + StringUtils.firstToUpperCase(f.getType()) + "();\r");
		}

		return sb.toString();
	}

}
