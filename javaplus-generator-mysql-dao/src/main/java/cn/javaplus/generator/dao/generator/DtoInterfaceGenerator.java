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
import cn.javaplus.util.Util;


public class DtoInterfaceGenerator extends AbstractGenerator {

	public DtoInterfaceGenerator() {
		super("DtoInterfaceTemp.txt");
	}

	/**
	 * 根据表名生成Dto代码
	 *
	 * @param tableName
	 *            表名
	 * @return Dto代码
	 */
	public String generate(Table table) {
		String[] temps = generatorMethods(table);
		String r1 = super.temp.replace("PACKAGE_NAME", getDBProperty("package"));
		String r2 = r1.replace("CLASS_NAME", getClassName(table));
		String r3 = r2.replace("IMPORTS", temps[0]);
		String r7 = r3.replace("IMPLEMENTS", generatorImplements(table.getName()));
		String r8 = r7.replace("METHODS", temps[1]);
		return r8;
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
		String className = getClassName(table);
		return getDBProperty("dir") + getDBProperty("package").replace(".", "/") + "/domain/" + className + ".java";
	}

	private String getClassName(Table table) {
		String className = Util.Str.generateClassName(table.getName());
		return className;
	}

	private String	ADD_TEMP				=

			"void addMETHOD_NAME(TYPE_NAME PROPERTY_NAME);\r\r";


	private static final String	TEMP	= "	TYPE_NAME getMETHOD_NAME();\r\r";
	private static final String	TEMP2	= "	TYPE_NAME getMETHOD_NAME(int index);\r\r";
	private static final String	STEMP	= "	void setMETHOD_NAME(TYPE_NAME PROPERTY_NAME);\r\r";
	private static final String	STEMP2	= "	void setMETHOD_NAME(int index, TYPE_NAME PROPERTY_NAME);\r\r";

	private String[] generatorMethods(Table table) {
		String methods = "";
		String imports = "";

		boolean existDate = false;
		for (int i = 0; i < table.getColumns().size(); i++) {
			Column column = table.getColumns().get(i);

			Property p = AbstractDaoImplGenerator.generatePropertyFromColumn(column);
			if ("Date".equals(p.getType()))
				existDate = true;

			if (table.isInGroup(i)) {
				continue;
			}

			methods += TEMP.replace("TYPE_NAME", p.getType()).replace("METHOD_NAME", Util.Str.generateClassName(p.getName()));
			methods += STEMP.replace("TYPE_NAME", p.getType()).replace("METHOD_NAME", Util.Str.generateClassName(p.getName())).replace("PROPERTY_NAME", p.getName());

			if (!p.getType().equals("boolean") &&

					!p.getType().equals("Date") &&

					!p.getType().equals("byte []")

					) {

				methods += ADD_TEMP.replace("TYPE_NAME", p.getType())

						.replace("PROPERTY_NAME", p.getName())

						.replace("METHOD_NAME", Util.Str.generateClassName(p.getName()));
			}

		}


		Map<String, List<Column>> s = table.getSameColumns();
		for (Entry<String, List<Column>> e : s.entrySet()) {

			Property p = AbstractDaoImplGenerator.generatePropertyFromColumn(e.getValue().get(0));


			methods += TEMP2.replace("TYPE_NAME", p.getType())

					.replaceAll("METHOD_NAME", Util.Str.generateClassName(getFieldName(e.getKey())));
			methods += STEMP2.replace("TYPE_NAME", p.getType())

					.replaceAll("METHOD_NAME", Util.Str.generateClassName(getFieldName(e.getKey()))).replace("PROPERTY_NAME", p.getName());

		}

		if (existDate)
			imports += "\rimport java.util.Date;\r";
		return new String[] { imports,  methods };
	}


	private String getFieldName(String fieldName) {

		while (Character.isDigit(fieldName.charAt(fieldName.length() - 1))) {
			fieldName = fieldName.substring(0, fieldName.length() - 1);
		}
		return fieldName;
	}
}
