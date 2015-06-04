package cn.javaplus.generator.dao.generator;

import java.io.PrintWriter;
import java.io.StringWriter;

import cn.javaplus.string.StringPrinter;

public class DaoFactoryGenerator extends AbstractFactoryGenerator {

	public DaoFactoryGenerator() {
		super("DaoFactoryTemp.txt");
	}

	@Override
	protected String getFileName() {
		return "DaoFactory.java";
	}

	@Override
	protected String getGetterTemplet() {
		StringWriter sw = new StringWriter();

		PrintWriter pw = new PrintWriter(sw);
		pw.println();
		pw.println("	public static CLASS_NAMEDao2 getCLASS_NAMEDao() {");
		pw.println("		return new CLASS_NAMEDao2(fetcher);");// 第二版本, 带写缓存
		pw.println("	}");
		pw.println();
		pw.println("	//public static CLASS_NAMEDao3 getCacheCLASS_NAMEDao() {");
		pw.println("	//	return new CLASS_NAMEDao3(fetcher);");
		pw.println("	//}");

		return sw.toString();
	}

	@Override
	protected String getViewDaoTemp() {
		StringPrinter sp = new StringPrinter();
		sp.println("	public static CLASS_NAMEDao getCLASS_NAMEDao() {");
		sp.println("		return new CLASS_NAMEDao(GameDB.getInstance());");
		sp.println("}");
		return sp.toString();
	}
}
