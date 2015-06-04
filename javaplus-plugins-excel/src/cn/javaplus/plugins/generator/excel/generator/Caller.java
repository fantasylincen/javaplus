package cn.javaplus.plugins.generator.excel.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import _util.FileUtil;
import cn.javaplus.plugins.console.ConsoleFactory;
import cn.javaplus.plugins.console.Debuger;
import cn.javaplus.plugins.generator.excel.Activator;
import cn.javaplus.plugins.generator.excel.preferences.D;

public class Caller {

	private static final String	DIR_TEST	= "测试数值";

	public void generate(File file) throws Exception {

		if(!file.getName().endsWith(".xls")) {
			ConsoleFactory.getErr().println("文件不是.xls文件!" + file);
			return ;
		}

		generateBase();

		String fileName = file.getName();
		String className = getClassName(fileName);

		Debuger.debug("On Parsing:" + className + " | " + file);
		String explain = fileName.replaceAll("_.*", "");

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();

		String PATH = store.getString(D.Paths.EXCEL + "");

		if (new File(PATH + DIR_TEST + File.separator + fileName).exists()) {
			fileName = PATH + DIR_TEST + File.separator + fileName;
			Debuger.debug("Be careful! Generate Test Data:" + fileName);
		} else {
			if (PATH.endsWith(File.separator)) {
				fileName = PATH + fileName;
			} else {
				fileName = PATH + File.separator + fileName;
			}
		}

		XlsReader sr = new XlsReader();

		Constent constent = getContent(fileName, className, sr);

		constent.setExplain(explain);

		generateAll(constent);
	}

	private void generateBase() throws Exception {

		Activator default1 = Activator.getDefault();

		IPreferenceStore preferenceStore = default1.getPreferenceStore();

		String string = preferenceStore.getString(D.Paths.EXCEL + "");

		File f = new File(string);

		String[] list = f.list();

		JavaLoaderGenerator glg = new JavaLoaderGenerator();

		glg.generate(list);

		List<File> files = new ArrayList<File>();

		for (String fname : list) {
			File file = FileUtil.getFile(f, fname);
			if (file.getName().endsWith(".xls")) {
				files.add(file);
			}
		}

		new ASConfigIDGenerator().generate(files);
	}

	private void generateAll(Constent constent) {
		new JavaClassGenerator().generate(constent);
		new JavaConfigClassGenerator().generate(constent);
		new ASClassGenerator().generate(constent);
		new JavaXmlGenerator().generate(constent);

		if (constent.explain.contains("SpecialXMLMark")) {
			new ASSpecialXMLGenerator().generate(constent);
			Debuger.debug("Generate Special XML");
			return;
		}

		new ASXmlGenerator().generate(constent);
	}

	private Constent getContent(String fileName, String className, XlsReader sr) {
		Constent constent;
		try {
			constent = sr.getConstent(fileName, 0, className);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return constent;
	}

	private String getClassName(String fileName) {
		return fileName.replace(".xls", "").replaceAll(".*_", "");
	}
}
