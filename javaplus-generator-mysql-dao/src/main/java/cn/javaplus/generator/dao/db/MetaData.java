/**
 * 数据库元数据类，采用单例设计模式，为其他类提供数据库元数据信息
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
package cn.javaplus.generator.dao.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.dom4j.Element;

import cn.javaplus.generator.dao.dto.Column;
import cn.javaplus.generator.dao.dto.Table;
import cn.javaplus.generator.dao.loader.PropertiesLoader;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.google.common.collect.Sets;

public final class MetaData {

	private static MetaData		instance	= null;
	private static Connection	conn;
	private static String		driver;
	private static String		url;
	private static String		user;
	private static String		pass;

	private static Set<String>	allKeyWords	= Sets.newHashSet();

	static {
		allKeyWords.add("add");
		allKeyWords.add("all");
		allKeyWords.add("alter");
		allKeyWords.add("analyze");
		allKeyWords.add("and");
		allKeyWords.add("as");
		allKeyWords.add("asc");
		allKeyWords.add("asensitive");
		allKeyWords.add("before");
		allKeyWords.add("between");
		allKeyWords.add("bigint");
		allKeyWords.add("binary");
		allKeyWords.add("blob");
		allKeyWords.add("both");
		allKeyWords.add("by");
		allKeyWords.add("call");
		allKeyWords.add("cascade");
		allKeyWords.add("case");
		allKeyWords.add("change");
		allKeyWords.add("char");
		allKeyWords.add("character");
		allKeyWords.add("check");
		allKeyWords.add("collate");
		allKeyWords.add("column");
		allKeyWords.add("condition");
		allKeyWords.add("connection");
		allKeyWords.add("constraint");
		allKeyWords.add("continue");
		allKeyWords.add("convert");
		allKeyWords.add("create");
		allKeyWords.add("cross");
		allKeyWords.add("current_date");
		allKeyWords.add("current_time");
		allKeyWords.add("current_timestamp");
		allKeyWords.add("current_user");
		allKeyWords.add("cursor");
		allKeyWords.add("database");
		allKeyWords.add("databases");
		allKeyWords.add("day_hour");
		allKeyWords.add("day_microsecond");
		allKeyWords.add("day_minute");
		allKeyWords.add("day_second");
		allKeyWords.add("dec");
		allKeyWords.add("decimal");
		allKeyWords.add("declare");
		allKeyWords.add("default");
		allKeyWords.add("delayed");
		allKeyWords.add("delete");
		allKeyWords.add("desc");
		allKeyWords.add("describe");
		allKeyWords.add("deterministic");
		allKeyWords.add("distinct");
		allKeyWords.add("distinctrow");
		allKeyWords.add("div");
		allKeyWords.add("double");
		allKeyWords.add("drop");
		allKeyWords.add("dual");
		allKeyWords.add("each");
		allKeyWords.add("else");
		allKeyWords.add("elseif");
		allKeyWords.add("enclosed");
		allKeyWords.add("escaped");
		allKeyWords.add("exists");
		allKeyWords.add("exit");
		allKeyWords.add("explain");
		allKeyWords.add("false");
		allKeyWords.add("fetch");
		allKeyWords.add("float");
		allKeyWords.add("float4");
		allKeyWords.add("float8");
		allKeyWords.add("for");
		allKeyWords.add("force");
		allKeyWords.add("foreign");
		allKeyWords.add("from");
		allKeyWords.add("fulltext");
		allKeyWords.add("goto");
		allKeyWords.add("grant");
		allKeyWords.add("group");
		allKeyWords.add("having");
		allKeyWords.add("high_priority");
		allKeyWords.add("hour_microsecond");
		allKeyWords.add("hour_minute");
		allKeyWords.add("hour_second");
		allKeyWords.add("if");
		allKeyWords.add("ignore");
		allKeyWords.add("in");
		allKeyWords.add("index");
		allKeyWords.add("infile");
		allKeyWords.add("inner");
		allKeyWords.add("inout");
		allKeyWords.add("insensitive");
		allKeyWords.add("insert");
		allKeyWords.add("int");
		allKeyWords.add("int1");
		allKeyWords.add("int2");
		allKeyWords.add("int3");
		allKeyWords.add("int4");
		allKeyWords.add("int8");
		allKeyWords.add("integer");
		allKeyWords.add("interval");
		allKeyWords.add("into");
		allKeyWords.add("is");
		allKeyWords.add("iterate");
		allKeyWords.add("join");
		allKeyWords.add("key");
		allKeyWords.add("keys");
		allKeyWords.add("kill");
		allKeyWords.add("label");
		allKeyWords.add("leading");
		allKeyWords.add("leave");
		allKeyWords.add("left");
		allKeyWords.add("like");
		allKeyWords.add("limit");
		allKeyWords.add("linear");
		allKeyWords.add("lines");
		allKeyWords.add("load");
		allKeyWords.add("localtime");
		allKeyWords.add("localtimestamp");
		allKeyWords.add("lock");
		allKeyWords.add("long");
		allKeyWords.add("longblob");
		allKeyWords.add("longtext");
		allKeyWords.add("loop");
		allKeyWords.add("low_priority");
		allKeyWords.add("match");
		allKeyWords.add("mediumblob");
		allKeyWords.add("mediumint");
		allKeyWords.add("mediumtext");
		allKeyWords.add("middleint");
		allKeyWords.add("minute_microsecond");
		allKeyWords.add("minute_second");
		allKeyWords.add("mod");
		allKeyWords.add("modifies");
		allKeyWords.add("natural");
		allKeyWords.add("not");
		allKeyWords.add("no_write_to_binlog");
		allKeyWords.add("null");
		allKeyWords.add("numeric");
		allKeyWords.add("on");
		allKeyWords.add("optimize");
		allKeyWords.add("option");
		allKeyWords.add("optionally");
		allKeyWords.add("or");
		allKeyWords.add("order");
		allKeyWords.add("out");
		allKeyWords.add("outer");
		allKeyWords.add("outfile");
		allKeyWords.add("precision");
		allKeyWords.add("primary");
		allKeyWords.add("procedure");
		allKeyWords.add("purge");
		allKeyWords.add("raid0");
		allKeyWords.add("range");
		allKeyWords.add("read");
		allKeyWords.add("reads");
		allKeyWords.add("real");
		allKeyWords.add("references");
		allKeyWords.add("regexp");
		allKeyWords.add("release");
		allKeyWords.add("rename");
		allKeyWords.add("repeat");
		allKeyWords.add("replace");
		allKeyWords.add("require");
		allKeyWords.add("restrict");
		allKeyWords.add("return");
		allKeyWords.add("revoke");
		allKeyWords.add("right");
		allKeyWords.add("rlike");
		allKeyWords.add("schema");
		allKeyWords.add("schemas");
		allKeyWords.add("second_microsecond");
		allKeyWords.add("select");
		allKeyWords.add("sensitive");
		allKeyWords.add("separator");
		allKeyWords.add("set");
		allKeyWords.add("show");
		allKeyWords.add("smallint");
		allKeyWords.add("spatial");
		allKeyWords.add("specific");
		allKeyWords.add("sql");
		allKeyWords.add("sqlexception");
		allKeyWords.add("sqlstate");
		allKeyWords.add("sqlwarning");
		allKeyWords.add("sql_big_result");
		allKeyWords.add("sql_calc_found_rows");
		allKeyWords.add("sql_small_result");
		allKeyWords.add("ssl");
		allKeyWords.add("starting");
		allKeyWords.add("straight_join");
		allKeyWords.add("table");
		allKeyWords.add("terminated");
		allKeyWords.add("then");
		allKeyWords.add("tinyblob");
		allKeyWords.add("tinyint");
		allKeyWords.add("tinytext");
		allKeyWords.add("to");
		allKeyWords.add("trailing");
		allKeyWords.add("trigger");
		allKeyWords.add("true");
		allKeyWords.add("undo");
		allKeyWords.add("union");
		allKeyWords.add("unique");
		allKeyWords.add("unlock");
		allKeyWords.add("unsigned");
		allKeyWords.add("update");
		allKeyWords.add("usage");
		allKeyWords.add("use");
		allKeyWords.add("using");
		allKeyWords.add("utc_date");
		allKeyWords.add("utc_time");
		allKeyWords.add("utc_timestamp");
		allKeyWords.add("values");
		allKeyWords.add("varbinary");
		allKeyWords.add("varchar");
		allKeyWords.add("varcharacter");
		allKeyWords.add("varying");
		allKeyWords.add("when");
		allKeyWords.add("where");
		allKeyWords.add("while");
		allKeyWords.add("with");
		allKeyWords.add("write");
		allKeyWords.add("x509");
		allKeyWords.add("xor");
		allKeyWords.add("year_month");
		allKeyWords.add("zerofill");
	}

	/**
	 * 私有构造方法，保证这个类生成的对象只有一个
	 */
	private MetaData() {
		try {

			driver = getProperty("driver");
			url = getProperty("url");
			user = getProperty("uname");
			pass = getProperty("pwd");

			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getProperty(String attrbute) {
		Element root = PropertiesLoader.getRoot();
		return root.attributeValue(attrbute);
	}

	/**
	 * 获得这个类唯一的对象
	 *
	 * @return instance
	 */
	public static MetaData getInstance() {
		if (instance == null) {
			synchronized (MetaData.class) {
				if (instance == null) {
					instance = new MetaData();
				}
			}
		}
		return instance;
	}

	/**
	 * 获取该数据库的所有表名
	 *
	 * @return List
	 *         <Table>
	 */
	public List<Table> getTables() {
		List<Table> tables = null;
		ResultSet pkrs = null;
		ResultSet trs = null;
		try {

			tables = new ArrayList<Table>();
			DatabaseMetaData meta = conn.getMetaData();

			trs = getAllTable(meta);
			Set<String> views = getAllViews(meta);

			while (trs.next()) {

				// 设置表名
				final String n = trs.getString(3);

				boolean isView = views.contains(n);

				pkrs = addTable(tables, n, meta, isView);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Closer.close(pkrs);
			Closer.close(trs);
		}
		return tables;
	}

	private ResultSet addTable(List<Table> tables, String tableName, DatabaseMetaData meta, boolean isView) throws SQLException {

		ResultSet pkrs;
		ResultSet rs = null;
		Statement stat = null;

		try {

			final Table table = new Table();
			table.setIsView(isView);

			table.setName(tableName);

			// 设置表主键的集合
			pkrs = meta.getPrimaryKeys(null, null, tableName);
			while (pkrs.next()) {
				final Column label = this.getColumnByColumnName(pkrs.getString(4), tableName);
				table.addKey(label);
			}
			// 设置表所有列的集合

			stat = conn.createStatement();
			rs = stat.executeQuery("select * from " + tableName + " limit 1");

			final ResultSetMetaData columnsMeta = rs.getMetaData();

			for (int i = 1; i <= columnsMeta.getColumnCount(); i++) {
				addColumn(table, columnsMeta, i);
			}

			tables.add(table);

		} catch (RuntimeException e) {
			throw e;
		} finally {
			Closer.close(rs, stat);
		}

		return pkrs;
	}

	private ResultSet getAllTable(DatabaseMetaData meta) throws SQLException {
		ResultSet trs;
		String[] types = { "TABLE", "VIEW" };
		trs = meta.getTables(null, null, null, types);
		return trs;
	}

	private Set<String> getAllViews(DatabaseMetaData meta) {
		Set<String> set = Sets.newHashSet();
		ResultSet trs = null;
		try {
			String[] types = { "VIEW" };
			trs = meta.getTables(null, null, null, types);
			while(trs.next()) {
				final String n = trs.getString(3);
				set.add(n);
			}
		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(trs);
		}

		return set;
	}

	private void addColumn(final Table table, final ResultSetMetaData columnsMeta, int index) throws SQLException {
		final Column label = new Column();
		int columnType = columnsMeta.getColumnType(index);
		String columnTypeName = columnsMeta.getColumnTypeName(index);
		final String name = columnsMeta.getColumnLabel(index);

		check(table, name);
		label.setName(name);
		label.setType(columnType);
		label.setTypename(columnTypeName);
		table.getColumns().add(label);
	}

	private void check(Table table, String name) {
		if (allKeyWords.contains(name.toLowerCase())) {
			throw new IllegalArgumentException(name + " 是关键字! 表:" + table.getName());
		}
	}

	/**
	 * 根据表明获取该表的所有字段名称和字段类型
	 *
	 * @param tableName
	 *            表名
	 * @return 由表明和类型组成的LabelDTO的List
	 */
	public List<Column> getColumnsByTableName(String tableName) { // NO_UCD (unused code)
		List<Column> labels = null;
		ResultSet rs = null;
		Statement stat = null;
		try {
			labels = new ArrayList<Column>();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from " + tableName);
			ResultSetMetaData meta = rs.getMetaData();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				Column label = new Column();
				label.setName(meta.getColumnLabel(i));
				label.setType(meta.getColumnType(i));
				label.setTypename(meta.getColumnTypeName(i));
				labels.add(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Closer.close(rs, stat);
		}
		return labels;
	}

	/**
	 * 根据列名和表名获取该列的详细信息
	 *
	 * @param columnName
	 *            列名
	 * @param tableName
	 *            表名
	 * @return LabelDTO
	 */
	private Column getColumnByColumnName(String columnName, String tableName) {
		Column label = null;
		ResultSet rs = null;
		Statement stat = null;
		try {
			label = new Column();
			stat = conn.createStatement();
			// System.out.println("select " + columnName + " from "
			// + tableName);
			rs = stat.executeQuery("select " + columnName + " from " + tableName);
			ResultSetMetaData meta = rs.getMetaData();
			label.setName(meta.getColumnLabel(1));
			label.setType(meta.getColumnType(1));
			label.setTypename(meta.getColumnTypeName(1));
		} catch (Exception e) {
			throw cn.javaplus.util.Util.Exception.toRuntimeException(e);
		} finally {
			Closer.close(rs, stat);
		}
		return label;
	}
}
