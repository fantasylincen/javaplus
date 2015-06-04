
package cn.javaplus.generator.dao.generator;


/**
 * dao实现类生成器
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
public class DaoGenerator extends AbstractDaoImplGenerator {

	public DaoGenerator() {
		super("DaoTemp.txt");
	}


	@Override
	protected String getFileName() {
		return "Dao.java";
	}
}
