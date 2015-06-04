package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import _util.StringUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IField;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.config.IReturn;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;
import cn.javaplus.plugins.util.StringUtils;

public class ASDataHandlerGenerator extends GeneratorImpl {

	abstract class AbstractCaseBuilder {

		String buildServerToClientCase(IClass c, IMethod m, GeneratorConfig config) {

			return "";
		}

		abstract String buildMergeFrom(IMethod m);

		abstract String buildEventFiled(IMethod m);

		String buildCase(IClass c, IMethod m, GeneratorConfig config) {

			String content = config.getTemplet("ASHandlerCase");

			content = content.replace("PACKET_NUMBER", m.getId() + "");

			content = content.replace("HANDLER_NAME", c.getInterface().getName() + TAIL);

			String mn = ASHandlerInterfaceGenerator.buildMethodName(m.getName());

			content = content.replace("METHOD_NAME", mn);

			content = content.replaceAll("MERGE_FROM", buildMergeFrom(m));

			content = content.replaceAll("INSERT_FIELDS", buildFileds(m));

			content = content.replaceAll("ACTION_NAME", c.getBeanName());

			content = content.replaceAll("EVENT_NAME", getEventName(m));

			content = content.replaceAll("EVENT_CLASS_NAME", ASEventsGenerator.buildClassName(c, m));

			content = content.replaceAll("EVENT_FILED", buildEventFiled(m));

			return content;
		}

		protected String buildReads(IReturn rt, String typeSimple, IMethod m) {

			String type = StringUtils.firstToUpperCase(rt.getType());

			if (type.equals("String")) {

				type = "UTF";
			}

			String string = "					" + getEventName(m) + ".back = " + "data.read" + type + "();\r";

			return string;
		}

	}

	public class CaseServerToClientBuilder extends AbstractCaseBuilder {

		// case 270003:
		// {
		// var e270003:ChatOnMessageWorldEvent = new ChatOnMessageWorldEvent();
		//
		// if(!hasError) {
		// var message:String = data.readUTF();
		//
		// } else {
		// e270003.error = error;
		// }
		//
		// ActionFactory.chat.dispatchEvent(e270003);
		//
		// break;
		// }
		@Override
		String buildMergeFrom(IMethod m) {

			StringBuilder sb = new StringBuilder();

			List<IField> args = m.getArgs();

			String name = getEventName(m);

			sb.append("					" + name + ".error = \"\";\r\r");

			for (IField f : args) {

				if (isProtocolType(f)) {
					// e60015.back = new FightersPro();
					// e60015.back.mergeFrom(data);
					sb.append("					" + name + "." + f.getName() + " = new " + f.getTypeSimple() + "();\r\r");
					sb.append("					" + name + "." + f.getName() + ".mergeFrom(data);\r\r");
				} else {
					sb.append("					" + name + "." + f.getName() + " = data.read" + parse(f) + "();\r\r");
				}
			}

			return sb + "";
		}

		private boolean isProtocolType(IField f) {
			return config.contains(f.getTypeSimple());
		}

		private String parse(IField f) {

			if (f.getType().equals("String")) {

				return "UTF";

			} else {

				return StringUtil.firstToUpperCase(f.getType());
			}
		}

		@Override
		String buildEventFiled(IMethod m) {

			StringBuilder sb = new StringBuilder();

			List<IField> args = m.getArgs();

			Iterator<IField> it = args.iterator();

			while (it.hasNext()) {

				IField f = it.next();

				sb.append(f.getName());

				if (it.hasNext()) {

					sb.append(",");
				}
			}

			return sb + "";
		}

	}

	public class CaseBuilder extends AbstractCaseBuilder {

		@Override
		String buildEventFiled(IMethod m) {

			if (!m.getReturn().isVoid()) {

				return getVarName(m) + ", error";

			} else {

				return "error";
			}
		}

		@Override
		String buildMergeFrom(IMethod m) {

			IReturn rt = m.getReturn();

			if (rt.isVoid()) {

				return "";

			} else {

				String typeSimple = rt.getTypeSimple();

				if (rt.isBaseType()) {

					return buildReads(rt, typeSimple, m);
				} else {
					return buildMerge(typeSimple, m);
				}
			}
		}

		private String buildMerge(String typeSimple, IMethod m) {
			return "						" + getEventName(m) + ".back = new " + typeSimple + "();\r" + "						" + getEventName(m) + ".back.mergeFrom(data);";
		}

	}

	private static final String	TAIL	= "Handler";

	public ASDataHandlerGenerator(GeneratorConfig config) {
		super(config, Paths.AS, "manager");
	}

	@Override
	public void generator() {

		List<IClass> all = config.getClazzs();

		String content = config.getTemplet("ASDataHandler");

		StringBuilder cases = new StringBuilder();

		for (IClass c : all) {

			for (IMethod m : c.getMethods()) {

				cases.append(new CaseBuilder().buildCase(c, m, config));
			}

			for (IMethod m : c.getServerToClientMethods()) {

				cases.append(new CaseServerToClientBuilder().buildCase(c, m, config));
			}
		}

		content = content.replaceAll("HANDLER_CASES", cases.toString());

		content = content.replaceAll("PACKAGE_NAME", getPackageName());

		content = content.replaceAll("IMPORTS", buildImports(all));

		String pack = getPackageName();

		content = content.replaceAll("HANDLERS_PACKAGE", pack);

		config.getFileUtil(getSrcPath() + File.separator + "DataHandler.as").writeToFile(content);
	}

	private String buildFileds(IMethod m) {

		IReturn rt = m.getReturn();

		return rt.isVoid() ? "" : getVarName(m);
	}

	private String getEventName(IMethod m) {
		return "e" + m.getId();
	}

	private String getVarName(IMethod m) {
		return "m" + m.getId();
	}
}
