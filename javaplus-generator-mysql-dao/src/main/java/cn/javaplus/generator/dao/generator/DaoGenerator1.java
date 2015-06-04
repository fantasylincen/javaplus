
package cn.javaplus.generator.dao.generator;


/**
 * dao实现类生成器
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
public class DaoGenerator1 extends AbstractDaoImplGenerator {

	public DaoGenerator1() {
		super("DaoTemp1.txt");
	}


	@Override
	protected String getFileName() {
		return "Dao1.java";
	}
}
