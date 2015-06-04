package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;

import org.eclipse.jface.preference.IPreferenceStore;

import _util.FileUtils;
import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.preferences.D;
import cn.javaplus.plugins.util.StringUtils;

public class JavaLoaderGenerator extends AbstractGenerator {

	private static FileUtils	fu	= new FileUtils();

	public JavaLoaderGenerator() {
		super("loaderTemplet.txt");
	}

	public void generate(String[] fileNames) throws Exception {
		String loads = "";
		String imports = "";

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String packageName = store.getString(D.Paths.PACKAGE_NAME + "");

		for (String fname : fileNames) {

			if (!fname.endsWith("xls")) {
				continue;
			}

			String className = getClassName(fname);

			try {
				loads += "\r\t\t\t" + StringUtils.firstToUpperCase(className) + "TempletConfig.load();";
			} catch (Exception e) {
				System.err.println("Error:" + className);
				throw e;
			}
			imports += "\rimport " + packageName + "." + StringUtils.firstToUpperCase(className) + "TempletConfig;";
		}

		temp = super.temp.replaceAll("PACKAGE_NAME", packageName).replaceAll("LOADS", loads).replaceAll("IMPORTS", imports);

		String string = store.getString(D.Paths.JAVA_CODE + "") + File.separator;

		String configName = string + packageName.replace(".", File.separator) + File.separator + "Loader.java";

		fu.write(configName, temp);
	}

	private String getClassName(String fname) {
		return fname.replace(".xls", "").replaceAll(".*_", "");
	}
}
