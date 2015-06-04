package experiment.db;

import java.util.List;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;

public class Test1 {
	private String sql  = "SELECT * FROM T";

	protected void setUp() throws Exception {
		sql = "SELECT * FROM T";
	}

	public void test_pert() throws Exception {
		for (int i = 0; i < 10; ++i) {
			perfMySql(sql);
		}
	}

	long perfMySql(String sql) {
		long startMillis = System.currentTimeMillis();
		for (int i = 0; i < 1000 * 1000; ++i) {
			execMySql(sql);
		}
		long millis = System.currentTimeMillis() - startMillis;
		System.out.println("MySql\t" + millis);
		return millis;
	}

	private String execMySql(String sql) {
		StringBuilder out = new StringBuilder();
		MySqlOutputVisitor visitor = new MySqlOutputVisitor(out);
		MySqlStatementParser parser = new MySqlStatementParser(sql);
		List<SQLStatement> statementList = parser.parseStatementList();
		for (SQLStatement statement : statementList) {
			statement.accept(visitor);
			visitor.println();
		}
		return out.toString();
	}
	public static void main(String[] args) throws Exception {
		new Test1().test_pert();
	}

}
