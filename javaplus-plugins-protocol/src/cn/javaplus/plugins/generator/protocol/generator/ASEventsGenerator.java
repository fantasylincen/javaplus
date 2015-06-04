package cn.javaplus.plugins.generator.protocol.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import _util.StringUtil;
import cn.javaplus.plugins.generator.protocol.config.FileUtil;
import cn.javaplus.plugins.generator.protocol.config.GeneratorConfig;
import cn.javaplus.plugins.generator.protocol.config.IClass;
import cn.javaplus.plugins.generator.protocol.config.IField;
import cn.javaplus.plugins.generator.protocol.config.IMethod;
import cn.javaplus.plugins.generator.protocol.config.IReturn;
import cn.javaplus.plugins.generator.protocol.preferences.D.Paths;

public class ASEventsGenerator extends GeneratorImpl {

	public class ASServerToClientEventGenerator extends AbstractASEventContentGenerator {

		@Override
		String buildSetValue(IMethod m) {

			StringBuilder sb = new StringBuilder();

			for (IField f : m.getArgs()) {

				sb.append("			this._" + f.getName() + " = " + f.getName() + ";\r\r");
			}

			return sb + "";
		}

		@Override
		String buildConstentArgs(IMethod m) {

			StringBuilder sb = new StringBuilder();

			List<IField> args = m.getArgs();

			Iterator<IField> it = args.iterator();

			while (it.hasNext()) {

				IField f = it.next();

				sb.append("" + f.getName() + ":" + f.getASPackagingType());

				if (it.hasNext()) {

					sb.append(", ");
				}
			}

			return sb + "";
		}

		@Override
		String buildGetterSetter(IMethod m) {

			StringBuilder sb = new StringBuilder();

			for (IField f : m.getArgs()) {

				sb.append("		/**\r");

				sb.append("		 * " + f.getDoc() + "\r");

				sb.append("		 */\r");

				sb.append("		public function get " + f.getName() + " ():" + f.getASPackagingType() + "\r");

				sb.append("		{\r");

				sb.append("			return this._" + f.getName() + ";\r");

				sb.append("		}\r");




				sb.append("		/**\r");

				sb.append("		 * " + f.getDoc() + "\r");

				sb.append("		 */\r");

				sb.append("		public function set " + f.getName() + " (" + f.getName() + ":" + f.getType() + "):void\r");

				sb.append("		{\r");

				sb.append("			this._" + f.getName() + " = " + f.getName() + ";\r");

				sb.append("		}\r");


			}

			return sb + "";
		}

		@Override
		String buildFields(IMethod m) {

			StringBuilder sb = new StringBuilder();

			for (IField f : m.getArgs()) {

				sb.append("		private var _" + f.getName() + ":" + f.getType() + ";\r");
			}

			return sb + "";

		}
	}

	public class ASNormalEventGenerator extends AbstractASEventContentGenerator {

		@Override
		String buildSetValue(IMethod m) {

			StringBuilder sb = new StringBuilder();

			IReturn rt = m.getReturn();

			if (!rt.isVoid()) {

				sb.append("			this._back = back;\r");
			}
			sb.append("			this._error = error;\r");

			return sb + "";
		}

		@Override
		String buildConstentArgs(IMethod m) {

			StringBuilder sb = new StringBuilder();

			IReturn rt = m.getReturn();

			if (!rt.isVoid()) {
				sb.append("back:" + rt.getTypeSimple() + ", error:String");
			} else {
				sb.append("error:String");
			}

			return sb + "";
		}

		@Override
		String buildGetterSetter(IMethod m) {

			StringBuilder sb = new StringBuilder();

			IReturn rt = m.getReturn();

			if (!rt.isVoid()) {

				String doc = rt.getReturnDoc();

				sb.append("		/**\r");

				sb.append("		 * " + doc + "\r");

				sb.append("		 */\r");

				sb.append("		public function get back ():" + rt.getTypeSimple() + "\r");

				sb.append("		{\r");

				sb.append("			return this._back;\r");

				sb.append("		}\r\r");





				sb.append("		/**\r");

				sb.append("		 * " + doc + "\r");

				sb.append("		 */\r");

				sb.append("		public function set back (back:" + rt.getTypeSimple() + "):void\r");

				sb.append("		{\r");

				sb.append("			this._back = back;\r");

				sb.append("		}\r");

			}

			return sb + "";
		}

		@Override
		String buildFields(IMethod m) {

			StringBuilder sb = new StringBuilder();

			IReturn rt = m.getReturn();

			if (!rt.isVoid()) {

				sb.append("		private var _back:" + rt.getTypeSimple() + ";\r");
			}

			return sb + "";

		}
	}

	abstract class AbstractASEventContentGenerator {

		void generateEvents(IClass c, IMethod m, GeneratorConfig config) {

			FileUtil fu = config.getFileUtil(getSrcPath() + File.separator + buildClassName(c, m) + ".as");

			String content = buildContent(c, m, config);

			fu.writeToFile(content);
		}

		String buildContent(IClass c, IMethod m, GeneratorConfig config) {

			String content = config.getTemplet("ASEvent");

			content = content.replaceAll("PACKAGE_NAME", getPackageName());

			content = content.replace("CLASS_DOC", m.getMethodDoc());

			content = content.replace("CLASS_NAME", buildClassName(c, m));

			content = content.replace("EVENT_VALUE", StringUtil.firstToUpperCase(c.getInterface().getName() + "." + m.getName()));

			content = content.replaceAll("IMPORTS", buildImports(c));

			content = content.replace("FILEDS", buildFields(m));

			content = content.replace("GETTER_SETTER", buildGetterSetter(m));

			content = content.replace("CON_ARGS", buildConstentArgs(m));

			content = content.replace("SET_VALUES", buildSetValue(m));

			return content;
		}

		abstract String buildSetValue(IMethod m);

		abstract String buildConstentArgs(IMethod m);

		abstract String buildGetterSetter(IMethod m);

		abstract String buildFields(IMethod m);

	}

	private static final String	TAIL	= "Event";

	public ASEventsGenerator(GeneratorConfig config) {
		super(config, Paths.AS, "events");
	}

	@Override
	public void generator() {

		List<IClass> all = config.getClazzs();

		for (IClass c : all) {

			List<IMethod> ms = new ArrayList<IMethod>(c.getMethods());

			ms.addAll(c.getServerToClientMethods());

			for (IMethod m : ms) {

				boolean isServerToClient = c.getServerToClientMethods().contains(m);

				AbstractASEventContentGenerator g;

				if (isServerToClient) {

					g = new ASServerToClientEventGenerator();
				} else {

					g = new ASNormalEventGenerator();
				}

				g.generateEvents(c, m, config);
			}
		}
	}

	public static final String buildClassName(IClass c, IMethod m) {

		return c.getInterface().getName() + StringUtil.firstToUpperCase(m.getName()) + TAIL;
	}
}
