package cn.javaplus.generator.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

import cn.javaplus.file.FileUtils;
import cn.javaplus.generator.dao.db.MetaData;
import cn.javaplus.generator.dao.dto.Table;
import cn.javaplus.generator.dao.generator.AbstractDaoImplGenerator;
import cn.javaplus.generator.dao.generator.AbstractFactoryGenerator;
import cn.javaplus.generator.dao.generator.DaoGenerator3;
import cn.javaplus.generator.dao.generator.DaoFactoryGenerator;
import cn.javaplus.generator.dao.generator.DaoGenerator1;
import cn.javaplus.generator.dao.generator.DaoGenerator2;
import cn.javaplus.generator.dao.generator.DaoGenerator;
import cn.javaplus.generator.dao.generator.DtoGenerator;
import cn.javaplus.generator.dao.generator.DtoInterfaceGenerator;
import cn.javaplus.generator.dao.generator.ViewDaoGenerator;
import cn.javaplus.generator.dao.loader.PropertiesLoader;
import cn.javaplus.generator.dao.utils.FactoryUtil;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.setProperty("file.encoding", "UTF-8");

		MetaData meta = MetaData.getInstance();
		List<Table> tables = meta.getTables();

		Map<String, Table> ts = buildTables(tables);

		Element DB = PropertiesLoader.getRoot();
		Element e = DB.element("generate_table");

		@SuppressWarnings("unchecked")
		Iterator<Element> it = e.elementIterator();

		if (e.attributeValue("is_generate_factory").trim().equals("true")) {
			Set<String> needGenerateToFactory = FactoryUtil
					.getAllNeedToFactory();
			FileUtils.write(factory.generateFileName(),
					factory.generate(tables, needGenerateToFactory));
		}

		while (it.hasNext()) {

			Element el = it.next();
			String tableName = el.attributeValue("name");
			Table t = ts.get(tableName);

			if (t == null) {

				throw new NullPointerException("数据表不存在:"
						+ el.attributeValue("name"));
			}

			if (t.isView()) {
				FileUtils.write(viewDaoImpl.generateFileName(t),
						viewDaoImpl.generate(t));
				// 视图表无需生成缓存DAO
			} else {
				FileUtils.write(d1.generateFileName(t), d1.generate(t));

				FileUtils.write(d2.generateFileName(t), d2.generate(t));

				FileUtils.write(d3.generateFileName(t), d3.generate(t));

				FileUtils.write(interfaceGenerator.generateFileName(t),
						interfaceGenerator.generate(t));

			}

			FileUtils.write(dto.generateFileName(t), dto.generate(t));
			FileUtils.write(dtoInterface.generateFileName(t),
					dtoInterface.generate(t));

			System.out.println("转换数据表:" + tableName);
		}
	}

	private static Map<String, Table> buildTables(List<Table> tables) {
		Map<String, Table> ts = new HashMap<String, Table>();
		for (Table table : tables) {
			ts.put(table.getName(), table);
		}
		return ts;
	}

	private static AbstractDaoImplGenerator d1 = new DaoGenerator1();
	private static AbstractDaoImplGenerator d2 = new DaoGenerator2();
	private static AbstractDaoImplGenerator d3 = new DaoGenerator3();
	private static AbstractDaoImplGenerator interfaceGenerator = new DaoGenerator();

	private static AbstractDaoImplGenerator viewDaoImpl = new ViewDaoGenerator();

	private static DtoGenerator dto = new DtoGenerator();
	private static DtoInterfaceGenerator dtoInterface = new DtoInterfaceGenerator();
	private static AbstractFactoryGenerator factory = new DaoFactoryGenerator();
}
