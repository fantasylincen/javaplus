package cn.javaplus.generator.dao.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.javaplus.generator.dao.dto.Column;
import cn.javaplus.generator.dao.dto.Property;
import cn.javaplus.generator.dao.dto.Table;
import cn.javaplus.random.Fetcher;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public abstract class AbstractDaoImplGenerator extends AbstractGenerator {

	protected AbstractDaoImplGenerator(String temp) {
		super(temp);
	}

	public String generate(Table table) {

		String s = replaceBefore(table);

		String rg = generateRsGets(table);

		s = s.replace("PACKAGE_NAME", getDBProperty("package"))
				.replaceAll("DAO2_FINDERS",
						generateFinders(table, "Dao2FindByTemp.txt"))
				.replaceAll("INTERFAC_FINDS",
						generateFinders(table, "DaoFindByTempForInterface.txt"))
				.replaceAll("FINDERS",
						generateFinders(table, "DaoFindByTemp.txt"))
				.replace("CLASS_NAME", getTypeName(table.getName()))
				.replace("CLASS_PARAMETER",
						generateParameterName(table.getName()))
				.replace("TABLE_NAME", table.getName())
				.replace("COLUMN_NAMES",
						generateColumnNames(table.getColumns()))
				.replace("COLUMN_?S", generateColumn_s(table.getColumns()))
				.replace("PK_PARAMETER", generateKeysParameters(table))
				.replace("PKCOLUMNS_S", generatePkColumns(table))
				.replace("PK_PS_SETS", generatePkPsSets(table))
				.replace("PS_SETS_ADD", generatePsSets_add(table))
				.replace("PS_SETS_UPDATE", generatePsSets_update(table))
				.replace("PK_COLUMNS", generatePkColumns(table))
				.replace("PAGE_SETS", generatePageSets())
				.replace("RS_GETS", rg)
				.replace("COLUMN_PARAMETERS", generateColumnParameters(table))
				.replace("TAIL", table.getKeys().size() > 1 ? "2" : "")
				.replace("KEYS", generatePks(table))
				.replace("KS_GETTER", generateKeysGetter(table))
				.replaceAll("KEYNAMES_LINK_BY_COMMA",
						generateKeysNameLinkByComma(table))
				.replace("DELETE_SETS", generateDeleteSets(table));

		if (table.haveKey()) {
			s = s.replace("KEY_COLUMN_NAME", table.getKeys().get(0).getName());
		} else {
			s = s.replace("KEY_COLUMN_NAME", "");
		}

		return s;
	}

	private String generateFinders(Table table, String fileName) {

		StringPrinter out = new StringPrinter();
		List<Column> columns = table.getSingleColumns();
		for (Column column : columns) {
			String name = column.getName();
			String temp = Templets.get(fileName);
			temp = temp.replaceAll("UPPER_FNAME",
					Util.Str.firstToUpperCase(Util.Str.hump(name)));
			temp = temp.replaceAll("FILED_NAME_IN_TABLE", name);

			String PACKAGE_FTYPE = getTypeName(updateTypeFromDb(
					column.getType(), true));
			String UPPER_FTYPE = getTypeName(updateTypeFromDb(column.getType(),
					false));

			if (PACKAGE_FTYPE.equals("Byte []")) {
				PACKAGE_FTYPE = "byte []";
			}

			if (UPPER_FTYPE.equals("Byte []")) {
				UPPER_FTYPE = "Bytes";
			}

			temp = temp.replaceAll("PACKAGE_FTYPE", PACKAGE_FTYPE);
			temp = temp.replaceAll("UPPER_FTYPE", UPPER_FTYPE);
			out.println(temp);
		}

		return out.toString();
	}

	private String generateKeysGetter(Table table) {
		List<Column> keys = table.getKeys();
		final String parameterName = generateParameterName(table.getName());

		Fetcher<Column, String> f = new Fetcher<Column, String>() {

			public String get(Column c) {
				Property p = generatePropertyFromColumn(c);
				String name = Util.Str.firstToUpperCase(p.getName());
				return parameterName + ".get" + name + "()";
			}
		};
		return Util.Collection.linkWith(",", keys, f);
	}

	/**
	 * 将字段转换为属性 其中包括将字段名转换为属性名，将整型的字段类型转换为字符型的属性类型 如将Column(name = user_name;
	 * type = -1)转换为Property(name = userName; type = String)
	 * 
	 * @param column
	 *            字段
	 * @return Property
	 */
	public static Property generatePropertyFromColumn(Column column) {
		Property p = new Property();
		p.setName(Util.Str.hump(column.getName())); // 成员名
		p.setType(AbstractDaoImplGenerator.updateTypeFromDb(column.getType(),
				false)); // 成员类型
		return p;
	}

	private String generateDeleteSets(Table table) {
		List<Column> keys = table.getKeys();
		StringPrinter sp = new StringPrinter();
		String parameterName = generateParameterName(table.getName());
		for (Column c : keys) {

			Property p = generatePropertyFromColumn(c);

			String cn = getTypeName(p.getName());
			String prop = parameterName + ".get" + cn + "()";
			sp.println("			ss.setNext(" + prop + ");");
		}
		return sp.toString();
	}

	protected String replaceBefore(Table table) {
		return this.temp;
	}

	public String generateFileName(Table table) {

		return getDBProperty("dir")
				+ getDBProperty("package").replace(".", "/") + "/dao/impl/"
				+ getTypeName(table.getName()) + getFileName();
	}

	/**
	 * 将字符串的首字母大写，一般用于将表名转换为类名
	 * 
	 * @param tableName
	 *            用下划线连接的全小写字段名, 例如"aaa_bbb_ccc" 表名
	 * @return 类名 "AaaBbbCcc"
	 */
	public static String getTypeName(String tableName) {
		return xxx(tableName).replaceFirst(tableName.substring(0, 1),
				tableName.substring(0, 1).toUpperCase());
	}

	private static String xxx(String columnName) {
		String[] labels = columnName.split("_");
		String r = labels[0];
		for (int i = 1; i < labels.length; i++)
			r = r + getTypeName(labels[i]);
		return r;
	}

	/**
	 * 将类名转换为对象 参数名，即第一个字母小写，如：User - user
	 * 
	 * @param tableName
	 *            表名
	 * @return 参数名
	 */
	public static String generateParameterName(String tableName) {

		String name = Util.Str.hump(tableName);

		if (name.length() > 7) {
			StringBuilder sb = new StringBuilder();

			char[] chars = name.toCharArray();

			for (int i = 0; i < chars.length; i++) {
				char c = chars[i];
				if (i == 0 || Character.isUpperCase(c)) {
					sb.append(Character.toLowerCase(c));
				}
			}
			return sb.toString() + "o";
		} else {
			return name + "o";
		}
	}

	/**
	 * 把字段的整型数据类型更新为属性的字符串型数据类型
	 * 
	 * @param type
	 *            字段数据类型
	 * @param isPackage
	 *            是否为包装类型
	 * @return 属性数据类型
	 * @return
	 */
	public static String updateTypeFromDb(int type, boolean isPackage) {

		boolean openWar = false;
		String t = "";
		switch (type) {
		case -7:// bit
			t = isPackage ? "Boolean" : "boolean";
			break;
		case -6:
			if (openWar)
				System.out.println("警告: TINYINT 转换为 short");
			t = isPackage ? "Short" : "short";
			break;
		case -5:// BIGINT
			if (openWar)
				System.out.println("警告: BIGINT 转换为 long");
			t = isPackage ? "Long" : "long";
			break;
		case -3:
			t = "byte []";
			break;
		case -1:
			t = "String";
			break;
		case 1:
			// t = "char";
			t = isPackage ? "Byte" : "byte";
			break;
		case 3:// decimal
			if (openWar)
				System.out.println("警告: decimal 转换为 int");
			t = isPackage ? "Integer" : "int";
			break;
		case 4:
			t = isPackage ? "Integer" : "int";
			break;
		case 5:
			t = isPackage ? "Short" : "short";
			break;
		case 6:
			t = isPackage ? "Float" : "float";
			break;
		case 7:
			t = isPackage ? "Float" : "float";
			break;
		case 8:
			t = isPackage ? "Double" : "double";
			break;
		case 12:
			t = "String";
			break;
		case 16:
			t = isPackage ? "Boolean" : "boolean";
			break;
		case 91:
			t = "Date";
			break;
		case 93:
			t = "Date";
			break;
		default:
			System.out.println("类型:" + type);
			break;
		}

		return t;
	}

	/**
	 * 根据该表的所有属性生成ADD_METHOD里面的COLUMN_NAMES字符串
	 * 
	 * @param cs
	 *            字段List
	 * @return COLUMN_NAMES字符串
	 */
	public static String generateColumnNames(List<Column> cs) {
		String s = cs.get(0).getName();
		for (int i = 1; i < cs.size(); i++)
			s += ", " + cs.get(i).getName();
		return s;
	}

	/**
	 * 根据表的属性数量生成ADD_METHOD里面的COLUMN_?S字符串
	 * 
	 * @param cs
	 *            字段List
	 * @return COLUMN_?S字符串
	 */
	public static String generateColumn_s(List<Column> cs) {
		String s = "?";
		for (int i = 1; i < cs.size(); i++)
			s += ", ?";
		return s;
	}

	/**
	 * add方法 insert into ***
	 * 
	 * .....
	 * 
	 * 省略号部分即返回值
	 * 
	 * @param table
	 * @return
	 */
	public static CharSequence generatePsSets_add(Table table) {
		List<Column> cs = table.getColumns();

		String pn = generateParameterName(table.getName());
		cs = new ArrayList<Column>(cs);
		StringPrinter out = new StringPrinter();

		Map<String, List<Column>> s = table.getSameColumns();

		Set<String> fund = new HashSet<String>();

		for (int i = 0; i < cs.size(); i++) {
			Property p = generatePropertyFromColumn(cs.get(i));

			String type = p.getType();
			if (type.equals("byte []")) {
				type = "bytes";
			}

			String key = cs.get(i).getName();

			while (Character.isDigit(key.charAt(key.length() - 1))) {
				key = key.substring(0, key.length() - 1);
			}

			if (s.containsKey(key)) {

				if (!fund.contains(key)) {
					int size = s.get(key).size();
					String tn = getTypeName(key);

					StringPrinter sp = new StringPrinter();
					sp.println("				for(int i = 0; i < " + size + "; i++) {");
					sp.println("					ss.setNext(" + pn + ".get" + tn + "(i));");
					sp.println("				}");

					out.print(sp);
					fund.add(key);
				}
				continue;
			}

			String prop = pn + ".get" + getTypeName(p.getName()) + "()";
			if ("Date".equals(type))
				prop = "new java.sql.Date(" + prop + ".getTime())";

			out.println("				ss.setNext(" + prop + ");");
		}

		return out.toString();
	}

	/**
	 * 根据表列的集合和类名生成PS_SETS字符串，如ps.setInt(1, user.getId());
	 */
	public static String generatePsSets_update(Table table) {
		String pn = generateParameterName(table.getName());

		List<Column> cs = getUpdateColumns(table);

		StringPrinter out = new StringPrinter();

		Map<String, List<Column>> s = table.getSameColumns();

		Set<String> fund = new HashSet<String>();

		for (int i = 0; i < cs.size(); i++) {
			Property p = generatePropertyFromColumn(cs.get(i));

			String type = p.getType();

			String key = cs.get(i).getName();

			while (Character.isDigit(key.charAt(key.length() - 1))) {
				key = key.substring(0, key.length() - 1);
			}
			if (s.containsKey(key)) {

				if (!fund.contains(key)) {
					int size = s.get(key).size();
					String cn = getTypeName(key);

					StringPrinter sp = new StringPrinter();
					sp.println("			for(int i = 0; i < " + size + "; i++) {");
					sp.println("				ss.setNext(" + pn + ".get" + cn + "(i));");
					sp.println("			}");
					out.println(sp);
					fund.add(key);
				}
				continue;
			}

			String prop = pn + ".get" + getTypeName(p.getName()) + "()";
			if ("Date".equals(type)) {
				prop = "new java.sql.Date(" + prop + ".getTime())";
			}

			out.println("			ss.setNext(" + prop + ");");

		}

		return out.toString();
	}

	/**
	 * 重新调整
	 * 
	 * @param table
	 * @return
	 */
	private static List<Column> getUpdateColumns(Table table) {
		List<Column> c = table.getWithOutKeys();
		c.addAll(table.getKeys());
		return c;
	}

	/**
	 * 生成设置分页参数的PsSets
	 * 
	 * @return PAGE_SETS字符串
	 */
	public static String generatePageSets() {
		StringPrinter sp = new StringPrinter();
		sp.println("			ps.setInt(1, (pageNo - 1) * pageSize);");
		sp.println("			ps.setInt(2, pageSize);");
		return sp.toString();
	}

	/**
	 * 根据Table对象得到该对象的delete方法的ps.set部分，如ps.setInt(1, id);
	 * 
	 * @param table
	 *            表格对象
	 * @return delete方法的ps.set部分
	 */
	public static String generatePkPsSets(Table table) {
		List<Column> keys = table.getKeys();
		StringPrinter sb = new StringPrinter();

		for (Column key : keys) {

			sb.println("			ss.setNext(" + key.getName() + ");");
		}
		return sb.toString();
	}

	/**
	 * 根据Table对象得到该表格的主键参数，如：int sno, int cno
	 * 
	 * @param table
	 *            表格对象
	 * @return 主键参数
	 */
	public static String generateKeysParameters(Table table) {
		StringBuilder sb = new StringBuilder();

		List<Column> keys = table.getKeys();
		Iterator<Column> it = keys.iterator();
		while (it.hasNext()) {
			Column c = it.next();
			sb.append(updateTypeFromDb(c.getType(), true) + " " + c.getName());
			if (it.hasNext())
				sb.append(", ");
		}
		return sb.toString();
	}

	/**
	 * 逗号连接的主键名列表
	 * 
	 * @param table
	 * @return
	 */
	public static String generateKeysNameLinkByComma(Table table) {
		StringBuilder sb = new StringBuilder();

		List<Column> keys = table.getKeys();
		Iterator<Column> it = keys.iterator();
		while (it.hasNext()) {
			Column c = it.next();
			sb.append(c.getName());
			if (it.hasNext())
				sb.append(", ");
		}
		return sb.toString();
	}

	/**
	 * 根据Table对象得到该对象的sql语句的主键部分，如：sno = ? AND cno = ?
	 * 
	 * @param table
	 *            表格对象
	 * @return sql语句的主键部分
	 */
	public static String generatePkColumns(Table table) {
		StringBuilder sb = new StringBuilder();

		List<Column> keys = table.getKeys();
		Iterator<Column> it = keys.iterator();
		while (it.hasNext()) {
			Column c = it.next();
			sb.append(c.getName() + " = ?");
			if (it.hasNext())
				sb.append(" AND ");
		}
		return sb.toString();
	}

	/**
	 * 根据Column List获得该对象的update sql语句的where子句部分
	 * 
	 * @return
	 */
	public static String generateColumnParameters(Table table) {

		StringBuilder sb = new StringBuilder();

		List<Column> cs = table.getWithOutKeys();
		Iterator<Column> it = cs.iterator();
		while (it.hasNext()) {
			Column c = it.next();
			sb.append(c.getName() + " = ?");
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	public static String generateRsGets(Table table) {
		List<Column> columns = table.getColumns();
		String classParameter = generateParameterName(table.getName());
		StringPrinter rrr = new StringPrinter();

		Map<String, List<Column>> s = table.getSameColumns();

		Set<String> fund = new HashSet<String>();

		for (int i = 0; i < columns.size(); i++) {

			Property p = generatePropertyFromColumn(columns.get(i));

			String type = p.getType();
			if (type.equals("byte []")) {
				type = "bytes";
			}

			String key = columns.get(i).getName();

			while (Character.isDigit(key.charAt(key.length() - 1))) {
				key = key.substring(0, key.length() - 1);
			}
			if (s.containsKey(key)) {

				if (!fund.contains(key)) {
					int size = s.get(key).size();

					StringPrinter temp = new StringPrinter();
					temp.println("");
					temp.println("		for(int i = 0; i < " + size + "; i++) {");
					temp.println("			" + classParameter + ".set"
							+ getTypeName(key) + "( i, rs.get"
							+ getTypeName(type) + "(\"" + key
							+ "\" + (i + 1)));");
					temp.println("		}");
					temp.println("");
					rrr.print(temp);
					fund.add(key);
				}
				continue;
			}

			Column c = columns.get(i);
			String cn = updateTypeFromDb(c.getType(), false);
			if (cn.equals("byte []")) {
				cn = "bytes";
			}
			String a = "		" + classParameter + ".set"
					+ Util.Str.firstToUpperCase(Util.Str.hump(c.getName()))
					+ "(rs.get" + getTypeName(cn) + "(\"" + c.getName()
					+ "\"));";
			rrr.print(a);

			if (i < columns.size() - 1) {
				rrr.println("		");
			}

		}

		return rrr.toString();
	}

	public static CharSequence generatePks(Table table) {
		StringBuilder sb = new StringBuilder();

		List<Column> keys = table.getKeys();
		Iterator<Column> it = keys.iterator();
		while (it.hasNext()) {
			Column c = it.next();
			sb.append(updateTypeFromDb(c.getType(), true) + ", ");
		}
		return sb.toString();
	}

	public static String generateKeysGetterss(Table table) {

		List<Column> keys = table.getKeys();

		Iterator<Column> it = keys.iterator();

		String s = "";

		String parameterName = generateParameterName(table.getName());

		while (it.hasNext()) {

			Column next = it.next();

			s += parameterName + ".get" + getTypeName(next.getName()) + "()";

			if (it.hasNext()) {

				s += ", ";
			}
		}

		return s;
	}

	protected abstract String getFileName();

}