package cn.javaplus.generator.dao.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

public class Table {

	// private static final XMLParser XMLP = XMLParser.getInstance();

	private String			name;
	// private Column key = null;
	//
	private List<Column>	columns	= new ArrayList<Column>();

	private String[]		findByMethod;
	private List<Column>	keys	= new ArrayList<Column>();
	private boolean			isView;

	// private DatabaseMetaData meta;

	public Table(/* DatabaseMetaData meta */) {
		// this.meta = meta;
	}

	public String[] getFindByMethods() {
		return findByMethod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * 获得指定表的所有键, 如果XML中定义了指定的键集, 那么就返回指定的, 如果不指定, 那么就返回默认的键
	 *
	 * @param name
	 * @return
	 */
	public List<Column> getKeys() {
		// List<Column> ls = new ArrayList<Column>();
		//
		// // if(XMLP.contains(name)) {
		// //
		// // ls.add(getColumns(XMLP.getContact(name).key1));
		// // ls.add(getColumns(XMLP.getContact(name).key2));
		//
		// for (Column c : columns) {
		// if(isKey(c)){
		// ls.add(c);
		// } else {
		// Printer.println("未找到主键, 暂时用第一列代替, tableName = " + getName());
		// ls.add(columns.get(0));
		// break;
		// }
		// }
		//
		// return ls;
		return keys;
	}

	// private boolean isKey(Column c) {
	// try {
	// ResultSet rs = meta.getPrimaryKeys("", "", c.getName());
	// while (rs.next()) {
	// rs.getString("");
	// }
	// return true;
	// } catch (SQLException e) {
	// return false;
	// }
	// }

	/**
	 * 获得除主键外所有列
	 *
	 * @return
	 */
	public List<Column> getWithOutKeys() {

		List<Column> cs = new ArrayList<Column>();

		List<Column> keys = getKeys();
		for (Column c : columns)
			if (!keys.contains(c))
				cs.add(c);

		return cs;
	}

// TODO from UCDetector: Method "Table.getColumns(String)" has 0 references
	public Column getColumns(String name) { // NO_UCD (unused code)
		for (Column c : columns) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * 加入需要生成find方法的字段
	 *
	 * @param findByFields
	 */
	public void addFindByMethod(String... findByFields) {
		this.findByMethod = findByFields;
	}

	/**
	 * 获得所有相同的列
	 *
	 * @return
	 */
	public Map<String, List<Column>> getSameColumns() {
		Map<String, List<Column>> all = new HashMap<String, List<Column>>();
		for (Column c : columns) {

			String key = c.getName();

			while (Character.isDigit(key.charAt(key.length() - 1))) {
				key = key.substring(0, key.length() - 1);
			}

			List<Column> list = all.get(key);
			if (list == null) {
				list = new ArrayList<Column>();
				all.put(key, list);
			}
			list.add(c);
		}

		Iterator<Entry<String, List<Column>>> it = all.entrySet().iterator();

		while (it.hasNext()) {
			Entry<String, List<Column>> e = it.next();
			if (e.getValue().size() <= 1) {
				it.remove();
			}
		}
		return all;
	}

	/**
	 * 判断第i列是否属于一个组
	 *
	 * @param i
	 * @return
	 */
	public boolean isInGroup(int col) {
		Map<String, List<Column>> s = getSameColumns();
		Column c = this.getColumns().get(col);
		return s.containsKey(c.getName().replaceAll("[0-9]", ""));
	}

	public void addKey(Column label) {
		this.keys.add(label);
	}

	public boolean haveKey() {
		return keys.size() > 0;
	}

	public void setIsView(boolean isView) {
		this.isView = isView;
	}

	public boolean isView() {
		return isView;
	}

	/**
	 * 不属于某个组的所有列
	 * @return
	 */
	public List<Column> getSingleColumns() {
		List<Column> ls = Lists.newArrayList(getColumns());
		Map<String, List<Column>> sameColumns = getSameColumns();
		for (List<Column> c : sameColumns.values()) {
			
			ls.removeAll(c);
		}
		return ls;
		
	}
}
