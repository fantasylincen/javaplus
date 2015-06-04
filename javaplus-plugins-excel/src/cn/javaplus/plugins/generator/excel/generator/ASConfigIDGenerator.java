package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.jface.preference.IPreferenceStore;

import _util.FileUtils;
import _util.StringUtil;
import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.preferences.D;

public class ASConfigIDGenerator extends AbstractGenerator {

	private static FileUtils	fu	= new FileUtils();

	public ASConfigIDGenerator() {
		super("asClassConfig.txt");
	}

	private String getConfigPackage() {

		Document doc = XMLLoader.getDoc();

		Element e = doc.getRootElement();

		//
		// <classPath class="Ability" name="cn.mxz.vo.AbilityVo"/>
		//
		// <config_id_package> cn.mxz.model </config_id_package>
		e = e.element("config_id_package");

		if (e == null) {

			return "cn.mxz.model";
		} else {

			String text = e.getText();

			return text.trim();
		}

	}

	public void generate(List<File> files) {

		String cp = getConfigPackage();

		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		out.println("package " + cp);
		out.println("{");
		out.println("	public class ConfigID {");
		out.println("		public function ConfigID()");
		out.println("		{");
		out.println("		}");

		for (File f : files) {
			String text = f.getName();
			String className = text.replaceAll("\\.xls.*", "").replaceAll(".*_", "");
			String doc = text.replaceAll("_.*", "");

			out.println("		/**");
			out.println("		 * " + doc);
			out.println("		 */");
			out.println("		public static const " + StringUtil.toConst(className) + ":String=\"" + className + "Config.xml\";\r\r");
		}

		out.println("	}\r" + "}");

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		String string = store.getString(D.Paths.PACKAGE_NAME + "").replace(".", File.separator) + File.separator;

		String configName = Activator.getDefault().getPreferenceStore().getString(D.Paths.AS_CODE + "") + File.separator + string + "model" + File.separator + "ConfigID.as";
		// ######[特殊][神兽品级]前端用_DogzStep.xls:String="######[特殊][神兽品级]前端用_DogzStep.xlsConfig.xml";
		fu.write(configName, sw.toString());
	}

}
