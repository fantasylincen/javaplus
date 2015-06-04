package cn.javaplus.generator.dao.generator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import cn.javaplus.generator.dao.dto.Column;
import cn.javaplus.generator.dao.dto.Table;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;

public abstract class AbstractFactoryGenerator extends AbstractGenerator {

	private Set<String>	needGenerateToFactory;

	AbstractFactoryGenerator(String temp) {
		super(temp);
	}

	public String generate(List<Table> tables, Set<String> needGenerateToFactory) {
		this.needGenerateToFactory = needGenerateToFactory;
		String temp = "";
		return 
				this.temp.replace("IMPORTS", temp).
				replace("PACKAGE_NAME", getDBProperty("package")).
				replace("GET_METHODS", generateDaoGetMethods(tables)).
				replace("COMMIT_ALL", generateCommitAll(tables)).
				replace("NEXT_IDS", generateNextIds(tables));
	}

	private String generateCommitAll(List<Table> tables) {
		tables = filter(tables);
		StringPrinter sp = new StringPrinter();
		for (Table table : tables) {
			buildCommit(sp, table);
		}
		return sp.toString();
	
	}

	private void buildCommit(StringPrinter sp, Table t) {
		if (!t.isView()) {
			sp.println("		get" + Util.Str.generateClassName(t.getName()) + "Dao().commitAllSafety();");
		}
	}

	private List<Table> filter(List<Table> tables) {
		List<Table> ls = Lists.newArrayList();
		for (Table t : tables) {
			if (needGenerateToFactory.contains(t.getName())) {
				ls.add(t);
			}
		}
		return ls;
	}

	private String generateNextIds(List<Table> tables) {
		StringBuilder sb = new StringBuilder();
		for (Table table : tables) {
			if (isIntegerKey(table)) {
				sb.append(buildNextId(table));
				sb.append("\r");
			}
		}
		return sb.toString();
	}

	private String buildNextId(Table table) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);

		String tableName = table.getName();

		String lname = table.getKeys().get(0).getName();
		String name = Util.Str.hump(lname);
		name = Util.Str.firstToUpperCase(name);

		String fieldName = Util.Str.hump(tableName) + Util.Str.firstToUpperCase(name);

		String functionName = "next" + Util.Str.firstToUpperCase(fieldName);
		out.println("	public static int " + functionName + "() {");
		out.println("		if(" + fieldName + " == null) {");
		out.println("			String sql = \"select max(" + lname + ") as ids from " + tableName + "\";");
		out.println("			" + fieldName + " = IdUtil.getInstance().initIdStart(fetcher, sql);");
		out.println("		}");
		out.println("		return (int)" + fieldName + ".addAndGet(1);");
		out.println("	}");
		out.println("	");
		out.println("	private static AtomicLong " + fieldName + ";");
		out.println("	");

		return sw.toString();
	}

	private boolean isIntegerKey(Table table) {
		List<Column> ks = table.getKeys();
		if (ks.size() != 1) {
			return false;
		}
		Column c = ks.get(0);
		String typename = c.getTypename();
		return typename.equals("INT");
	}

	public String generateFileName() {
		return getDBProperty("dir") + getDBProperty("package").replace(".", "/") + "/dao/impl/" + getFileName();
	}

	protected abstract String getFileName();

	/**
	 * 根据所有的Table，生成Factory中的getDao方法
	 *
	 * @param tables
	 * @return GET_METHODS
	 */
	private String generateDaoGetMethods(List<Table> tables) {
		tables = filter(tables);
		StringBuilder sb = new StringBuilder();
		for (Table t : tables) {
			sb.append(buildGetterTemplet(t));
		}
		return sb + "";
	}

	private String buildGetterTemplet(Table t) {

		if (t.isView()) {
			String viewDaoGetterTemplet = getViewDaoTemp();

			return viewDaoGetterTemplet.replace("CLASS_NAME", Util.Str.generateClassName(t.getName())).replace("KEYS", AbstractDaoImplGenerator.generatePks(t)).replace("CLASS_PARAMETER", Util.Str.generateParameterName(t.getName()));
		}

		return getGetterTemplet().replace("CLASS_NAME", Util.Str.generateClassName(t.getName())).replace("KEYS", AbstractDaoImplGenerator.generatePks(t)).replace("TAIL", t.getKeys().size() > 1 ? "2" : "").replace("CLASS_PARAMETER", Util.Str.generateParameterName(t.getName()));
	}

	protected abstract String getViewDaoTemp();

	protected abstract String getGetterTemplet();

}