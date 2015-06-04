package cn.javaplus.generator.dao.generator;


public class ViewDaoGenerator extends AbstractDaoImplGenerator {

	public ViewDaoGenerator() {
		super("ViewDao.txt");
	}

	@Override
	protected String getFileName() {
		return "Dao.java";
	}
}
