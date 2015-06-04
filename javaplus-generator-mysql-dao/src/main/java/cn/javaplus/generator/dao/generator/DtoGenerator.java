/**
 * dao实现类生成器
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
package cn.javaplus.generator.dao.generator;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;

import cn.javaplus.generator.dao.dto.Column;
import cn.javaplus.generator.dao.dto.Property;
import cn.javaplus.generator.dao.dto.Table;
import cn.javaplus.generator.dao.loader.PropertiesLoader;
import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

public class DtoGenerator extends AbstractGenerator {

	private String PROPERTY_TEMP = "	private TYPE_NAME PROPERTY_NAME;\r";
	private String METHOD_TEMP = Templets.get("DtoMethodTemp.txt");
	private String ADD_TEMP = Templets.get("DtoAddTemp.txt");
	private String PROPERTY_TEMP_ARRAYS = "	private TYPE_NAME [] PROPERTY_NAME = new TYPE_NAME[ARRAY_SIZE];\r";
	private String METHOD_TEMP_ARRAYS = Templets.get("DtoMethodArrayTemp.txt");

	public DtoGenerator() {
		super("DtoTemp.txt");
	}

	/**
	 * 根据表名生成Dto代码
	 * 
	 * @param tableName
	 *            表名
	 * @return Dto代码
	 */
	public String generate(Table table) {
		String[] temps = generatePropertiesAndMethods(table);
		String r1 = super.temp
				.replace("PACKAGE_NAME", getDBProperty("package"));
		String className = getInterfaceName(table);
		String r2 = r1.replace("CLASS_NAME", className);
		String r3 = r2.replace("IMPORTS", temps[0]);
		String r4 = r3.replace("PROPERTIES", temps[1]);
		String r5 = r4.replace("SETS_AND_GETS", temps[2]);
		String r6 = r5.replace("COPY_CONSTRACTOR", temps[3]);
		String r7 = r6.replace("IMPLEMENTS",
				generatorImplements(table.getName()));
		String r8 = r7.replace("TABLE_NAME", table.getName());
		return r8;
	}

	private String getClassName(Table table) {
		return Util.Str.generateClassName(table.getName()) + "Impl";
	}

	private String getInterfaceName(Table table) {
		return Util.Str.generateClassName(table.getName());
	}

	private CharSequence generatorImplements(String tableName) {
		Element es = PropertiesLoader.getRoot().element("generate_table");
		@SuppressWarnings("unchecked")
		Iterator<Element> it = es.elementIterator();
		while (it.hasNext()) {
			Element e = it.next();
			String name = e.attributeValue("name");
			if (name.trim().equals(tableName)) {
				return generatorImplements(e);
			}
		}
		return "";
	}

	private CharSequence generatorImplements(Element e) {
		String ims = "";
		@SuppressWarnings("unchecked")
		List<Element> as = e.elements();
		for (Element element : as) {
			String text = element.getText();
			ims += ", " + text;
		}
		return ims;
	}

	public String generateFileName(Table table) {
		return getDBProperty("dir")
				+ getDBProperty("package").replace(".", "/") + "/domain/"
				+ getClassName(table) + ".java";
	}

	/**
	 * 根据表的信息，生成要替换的三个String，分别为imports, properties, methods
	 * 
	 * @param table
	 * @return new String[]{imports, properties, methods}
	 */
	private String[] generatePropertiesAndMethods(Table table) {
		String imports = "";
		String properties = "";
		String methods = "";
		String copy_constractor = "";// 复制构造函数

		boolean existDate = false;
		for (int i = 0; i < table.getColumns().size(); i++) {
			Column column = table.getColumns().get(i);

			Property p = AbstractDaoImplGenerator.generatePropertyFromColumn(column);
			if ("Date".equals(p.getType()))
				existDate = true;

			if (table.isInGroup(i)) {
				continue;
			}

			if (table.getKeys().contains(column)) {
				properties += "	@Key\r\r";
			}

			properties += PROPERTY_TEMP;

			properties = properties.replace("TYPE_NAME", p.getType()). // 成员类型
					replace("PROPERTY_NAME", p.getName()); // 成员名字
			copy_constractor += "\t\tthis." + p.getName() + " = c.get"
					+ Util.Str.firstToUpperCase(p.getName()) + "();\r";

			methods += METHOD_TEMP;
			methods = methods
					.replace("TYPE_NAME", p.getType())
					.replace("PROPERTY_NAME", p.getName())
					.replace("METHOD_NAME",
							Util.Str.generateClassName(p.getName()));

			if (!p.getType().equals("boolean") &&

			!p.getType().equals("Date") &&

			!p.getType().equals("byte []")

			) {

				methods += ADD_TEMP
						.replace("TYPE_NAME", p.getType())

						.replace("PROPERTY_NAME", p.getName())

						.replace("METHOD_NAME",
								Util.Str.generateClassName(p.getName()));
			}

		}

		for (Entry<String, List<Column>> e : table.getSameColumns().entrySet()) {

			properties += "\tpublic static final int "
					+ e.getKey().toString().toUpperCase() + "_LEN = "
					+ e.getValue().size() + ";\r";
		}

		Map<String, List<Column>> s = table.getSameColumns();
		for (Entry<String, List<Column>> e : s.entrySet()) {

			Property p = AbstractDaoImplGenerator.generatePropertyFromColumn(e.getValue()
					.get(0));

			properties += PROPERTY_TEMP_ARRAYS;

			properties = properties.replace("TYPE_NAME", p.getType()). // 成员类型

					replace("PROPERTY_NAME", getFieldName(e.getKey())).

					replaceAll("ARRAY_SIZE", e.getValue().size() + ""); // 成员名字

			copy_constractor += generateArrayCopy(e);

			methods += METHOD_TEMP_ARRAYS;

			methods = methods
					.replace("TYPE_NAME", p.getType())

					.replaceAll("PROPERTY_NAME", getFieldName(e.getKey()))

					.replaceAll(
							"METHOD_NAME",
							Util.Str.generateClassName(getFieldName(e.getKey())));

		}

		if (existDate)
			imports += "\rimport java.util.Date;\r";
		return new String[] { imports, properties, methods, copy_constractor };
	}

	private String generateArrayCopy(Entry<String, List<Column>> e) {
		String key = e.getKey();
		String v = Util.Str.hump(key);
		v = Util.Str.firstToUpperCase(v);

		StringPrinter sb = new StringPrinter();
		sb.println("		for (int i = 0; i < " + key + ".length; i++) {");
		sb.println("			" + key + "[i] = c.get" + v + "(i);");
		sb.println("		}");
		return sb.toString();
	}

	private String getFieldName(String fieldName) {

		while (Character.isDigit(fieldName.charAt(fieldName.length() - 1))) {
			fieldName = fieldName.substring(0, fieldName.length() - 1);
		}
		return fieldName;
	}
}
