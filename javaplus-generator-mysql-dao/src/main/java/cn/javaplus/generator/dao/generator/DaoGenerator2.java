
package cn.javaplus.generator.dao.generator;


/**
 * dao实现类生成器
 * @author dengzongrong
 * @version 1.1
 * @date 2011-10-09
 */
public class DaoGenerator2 extends AbstractDaoImplGenerator {

	public DaoGenerator2() {
		super("DaoTemp2.txt");
	}


	@Override
	protected String getFileName() {
		return "Dao2.java";
	}
}
