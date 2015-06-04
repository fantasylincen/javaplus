package cn.javaplus.plugins.generator.excel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.javaplus.file.FileUtil;
import cn.javaplus.plugins.generator.excel.generator.Constent;
import cn.javaplus.plugins.generator.excel.generator.Debuger;
import cn.javaplus.plugins.generator.excel.generator.JavaClassGenerator;
import cn.javaplus.plugins.generator.excel.generator.JavaConfigClassGenerator;
import cn.javaplus.plugins.generator.excel.generator.JavaLoaderGenerator;
import cn.javaplus.plugins.generator.excel.generator.JavaXmlGenerator;
import cn.javaplus.plugins.generator.excel.generator.Store;
import cn.javaplus.plugins.generator.excel.generator.XlsReader;
import cn.javaplus.plugins.generator.excel.preferences.D;

public class App {

	public static void main(String[] args)  {


        generate("Z:/台湾版本数值");

	}

	public static void generate(String path) {
		//test lc
        System.out.println( 2 );

		generateJavaLoader(path);

		if (IS_GENERATE_AS) {
			generateAsConfigId(path);
		}

		File dir = new File(path);

		String[] list = dir.list();
		for (String f : list) {
			File file = FileUtil.getFile(dir, f);
			if (file.getName().endsWith(".xls")) {
				generate(file);
			}
		}
	}

	private static void generateJavaLoader(String path) {

		File f = new File(path);

		String[] list = f.list();

		JavaLoaderGenerator glg = new JavaLoaderGenerator();

		glg.generate(list);
	}

	private static final String	DIR_TEST	= "测试数值";

	public static void generate(File file) {

		String fileName = file.getName();
		String className = getClassName(fileName);

		Debuger.debug("生成:" + className + " | " + file);
		String explain = fileName.replaceAll("_.*", "");

		String PATH = file.getParent();

		if (new File(PATH + DIR_TEST + File.separator + fileName).exists()) {
			fileName = PATH + DIR_TEST + File.separator + fileName;
			Debuger.debug("Be careful! Generate Test Data:" + fileName);
		} else {
			fileName = parse(fileName, PATH);
		}

		XlsReader sr = new XlsReader();

		Constent constent = getContent(fileName, className, sr);

		constent.setExplain(explain);

		generateAll(constent);
	}

	private static String parse(String fileName, String PATH) {
		if (PATH.endsWith(File.separator)) {
			fileName = PATH + fileName;
		} else {
			fileName = PATH + File.separator + fileName;
		}
		return fileName;
	}

	private static void generateAsConfigId(String path) {

		File f = new File(path);

		String[] list = f.list();

		List<File> files = new ArrayList<File>();

		for (String fname : list) {
			File file = FileUtil.getFile(f, fname);
			if (file.getName().endsWith(".xls")) {
				files.add(file);
			}
		}

//		new ASConfigIDGenerator().generate(files);
	}

	/**
	 * 是否生成AS相关文件
	 */
	private static boolean	IS_GENERATE_AS	= false;

	private static void generateAll(Constent constent) {

		new JavaClassGenerator().generate(constent);
		new JavaConfigClassGenerator().generate(constent);
		new JavaXmlGenerator().generate(constent);

//		if (IS_GENERATE_AS) {
//			new ASClassGenerator().generate(constent);
//			if (constent.explain.contains("SpecialXMLMark")) {
//				new ASSpecialXMLGenerator().generate(constent);
//				Debuger.debug("Generate Special XML");
//				return;
//			}
//
//			new ASXmlGenerator().generate(constent);
//		}

	}

	private static Constent getContent(String fileName, String className, XlsReader sr) {
		Constent constent;
		try {
			constent = sr.getConstent(fileName, 0, className);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return constent;
	}

	private static String getClassName(String fileName) {
		return fileName.replace(".xls", "").replaceAll(".*_", "");
	}

}
