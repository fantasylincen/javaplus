package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;
import java.net.URL;

import cn.javaplus.plugins.generator.excel.preferences.D;
import cn.javaplus.util.Util;

public class JavaLoaderGenerator extends AbstractGenerator {

	public JavaLoaderGenerator() {
//		super("loader.txt");
		this.temp = Util.File.getContent(new File("res/build/loader.txt"));
	}

	public void generate(String[] fileNames) {
		String loads = "";
		String imports = "";

		String packageName = Store.getString(D.Paths.PACKAGE_NAME + "");

		for (String fname : fileNames) {

			if (!fname.endsWith("xls")) {
				continue;
			}

			String className = getClassName(fname);

			loads += "\r\t\t\t" + Util.Str.firstToUpperCase(className) + "TempletConfig.load();";

			imports += "\rimport " + packageName + "." + Util.Str.firstToUpperCase(className) + "TempletConfig;";
		}

		temp = super.temp.replaceAll("PACKAGE_NAME", packageName).replaceAll("LOADS", loads).replaceAll("IMPORTS", imports);

		String string = Store.getString(D.Paths.JAVA_CODE + "") + File.separator;

		String configName = string + packageName.replace(".", File.separator) + File.separator + "Loader.java";

		Util.File.write(configName, temp);
	}

	private String getClassName(String fname) {
		return fname.replace(".xls", "").replaceAll(".*_", "");
	}
}
