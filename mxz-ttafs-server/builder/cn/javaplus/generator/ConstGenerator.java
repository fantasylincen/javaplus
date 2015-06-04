package cn.javaplus.generator;

import java.io.IOException;

import org.dom4j.Element;

import cn.javaplus.file.FileUtils;
import cn.javaplus.generator.loader.PropertiesLoader;
import cn.javaplus.util.Util;

/**
 * 将后端的文字提示信息转换成XML配置表
 *
 * @author 林岑
 * @time 2012年9月18日 11:27:38
 *
 */
public class ConstGenerator {

	public void parse() {

		try {

			Element root = PropertiesLoader.getRoot();

			Element mess = root.element("define");

			String javafile = mess.attributeValue("javafile");

			String asfile = mess.attributeValue("asfile");

			generateJavaFile(javafile);

			generateAsFile(asfile);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void generateAsFile(String asfile) {

		DefineTempletConfig.load();

		StringBuilder sb = new StringBuilder();

		for (DefineTemplet d : DefineTempletConfig.getAll()) {

			String type = d.getType();

			if (type.equals("float") || type.equals("double")) {

				type = "Number";
			}

			String s = asValueTemplet.replaceAll("DOC", d.getExplain()).replaceAll("VALUE", d.getValue() + "").replaceAll("KEY", d.getName() + "").replaceAll("TYPE", type);

			sb.append(s);
		}

		String fileContent = asTemplet.replaceAll("FILEDS", sb.toString());

		FileUtils.write(asfile, fileContent);
	}

	/**
	 * 生成R.Java文件
	 *
	 * @param javafile
	 * @param p
	 */

	private static String	templet			= Util.File.getContent("res/build/D.temp");

	String					valueTemplet	= "	/** DOC */\r" + "	public static TYPE KEY = VALUE;\r\r";
	String					reloadTemplet	= "		" + "	KEY = VALUE;\r\r";

	private static String	asTemplet		= "package cn.mxz.define\r" + "{" + "\r" + "	/**\r" + "	 * 常量配置\r" + "	 * @time	2012年1月21日 12:43:02\r" + "	 *\r" + "	 */\r" + "	public class D {\r" + "\r" + "FILEDS\r" + "\r" + "	\r" + "	\r" + "	}\r" + "}\r";

	String					asValueTemplet	= "			/** DOC */\r" + "			public static const KEY:TYPE=VALUE;\r\r";

	private void generateJavaFile(String javafile) throws IOException {

		DefineTempletConfig.load();

		StringBuilder sb = new StringBuilder();
		
		
		for (DefineTemplet d : DefineTempletConfig.getAll()) {

			String type = Util.Str.firstToUpperCase(d.getType());
			
			String s = reloadTemplet.replaceAll("VALUE",  "get" + type + "(\"" + d.getName() + "\")");
			s = s.replaceAll("KEY", d.getName() + "");
			s = s.replaceAll("TYPE", d.getType());
			sb.append(s);
		}
		
		String fileContent = templet.replaceAll("RELOAD_FILEDS", sb.toString());

		
		sb = new StringBuilder();
		
		for (DefineTemplet d : DefineTempletConfig.getAll()) {

			String s = valueTemplet.replaceAll("DOC", d.getExplain());
			s = s.replaceAll("VALUE",  d.getValue() + "");
			s = s.replaceAll("KEY", d.getName() + "");
			s = s.replaceAll("TYPE", d.getType());
			sb.append(s);
		}

		fileContent = fileContent.replaceAll("FILEDS", sb.toString());

		FileUtils.write(javafile, fileContent);
	}

	public static void main(String[] args) {
		new ConstGenerator().parse();
	}
}
