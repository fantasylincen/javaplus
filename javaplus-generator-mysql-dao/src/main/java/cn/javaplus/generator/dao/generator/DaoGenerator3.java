package cn.javaplus.generator.dao.generator;

import java.util.List;

import cn.javaplus.generator.dao.dto.Column;
import cn.javaplus.generator.dao.dto.Property;
import cn.javaplus.generator.dao.dto.Table;


/**
 * dao实现类生成器
 *
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
public class DaoGenerator3 extends AbstractDaoImplGenerator {

	public DaoGenerator3() {
		super("DaoTemp3.txt");
	}

	@Override
	protected String getFileName() {
		return "Dao3.java";
	}

	@Override
	protected String replaceBefore(Table table) {

		String generate = super.replaceBefore(table);

		generate = generate.replaceAll("CACHE_DEFINE", generateCacheDefine(table));

		generate = generate.replaceAll("KEYS_GETTERS", generateKeysGetterss(table));

		generate = generate.replaceAll("KEYNAMES_LINK_BY_COMMA", generateKeysNameLinkByComma(table));

		return generate;
	}


	/**
	 * 缓存定义
	 *
	 * @param table
	 * @return
	 */
	private static String generateCacheDefine(Table table) {

		List<Column> keys = table.getKeys();


		String generate = "";

		if (keys.size() == 1) {

			generate =  "	private static Cache1<KEY_TYPE_1, CLASS_NAME> cache = new Cache1Impl<KEY_TYPE_1, CLASS_NAME>();";

			generate += "\r";

			generate += "	public static Cache1<KEY_TYPE_1, CLASS_NAME> getCache() { return cache; }";

		} else if (keys.size() == 2) {

			generate = "	private static Cache2<KEY_TYPE_1, KEY_TYPE_2, CLASS_NAME> cache = new Cache2Impl<KEY_TYPE_1, KEY_TYPE_2, CLASS_NAME>();";

			generate += "\r";

			generate += "	public static Cache2<KEY_TYPE_1, KEY_TYPE_2, CLASS_NAME> getCache() { return cache; }";
		}


		if (keys.size() > 0) {

			Property p = AbstractDaoImplGenerator.generatePropertyFromColumn(keys.get(0));

			generate = generate.replaceAll("KEY_TYPE_1", p.getPackagingType()); // KEYS_GETTERS
		}

		if (keys.size() > 1) {

			Property p = AbstractDaoImplGenerator.generatePropertyFromColumn(keys.get(1));

			generate = generate.replaceAll("KEY_TYPE_2", p.getPackagingType());
		}
		return generate;
	}
}
