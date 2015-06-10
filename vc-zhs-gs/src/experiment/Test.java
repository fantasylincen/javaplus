package experiment;

import game.award.AwardInfo;
import game.award.AwardType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.management.JMException;

import user.UserInfo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.parser.Lexer;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.parser.Token;
import com.alibaba.druid.stat.JdbcStatManager;

public class Test {

	static void test5() throws SQLException {
		JdbcStatManager.getInstance().reset(); // 重置计数器
		System.out.println(JdbcStatManager.getInstance().getConnectionstat()
				.getConnectCount());
		System.out.println(JdbcStatManager.getInstance().getConnectionstat()
				.getCloseCount());
		String url = "jdbc:wrap-jdbc:filters=default:name=preCallTest:jdbc:derby:memory:Demo1;create=true";
		Connection conn = DriverManager.getConnection(url);
		System.out.println(JdbcStatManager.getInstance().getConnectionstat()
				.getConnectCount());
		System.out.println(JdbcStatManager.getInstance().getConnectionstat()
				.getCloseCount());
		conn.close();
		System.out.println(JdbcStatManager.getInstance().getConnectionstat()
				.getConnectCount());

	}

	static void test4() {
		String sql = "SELECT UUID()";
		// parser得到AST
		SQLStatementParser parser = new MySqlStatementParser(sql);
		List<SQLStatement> stmtList = parser.parseStatementList(); //
		// 将AST通过visitor输出
		StringBuilder out = new StringBuilder();
		MySqlOutputVisitor visitor = new MySqlOutputVisitor(out);
		for (SQLStatement stmt : stmtList) {
			stmt.accept(visitor);
			out.append(";");
		}
		System.out.println(out.toString());

	}

	static void test3() {
		// String sql = "SELECT * FROM T WHERE F1 = ? ORDER BY F2";
		String sql = "SELECT rank,uname from city_elite order by rank";
		Lexer lexer = new Lexer(sql);
		for (;;) {
			lexer.nextToken();
			Token tok = lexer.token();
			if (tok == Token.IDENTIFIER) {
				System.out.println(tok.name() + "\t\t" + lexer.stringVal());
			} else {
				System.out.println(tok.name() + "\t\t\t" + tok.name);
			}
			if (tok == Token.EOF) {
				break;
			}
		}

	}

	static void test1() {
		DataFactory db = new DataFactory("com.mysql.jdbc.Driver",
				"jdbc:mysql://192.168.1.56:3306/game", "root", "", 10, 5 * 1000);
		PreparedStatement pst = null;
		ResultSet rs = null;

		long begin = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			try {

				pst = db.getPst("SELECT rank,uname from city_elite order by rank");

				rs = pst.executeQuery();

				while (rs != null && rs.next()) {

					int rank = rs.getInt("rank");
					String uname = rs.getString("uname");

					System.out.println("rank:" + rank + "\t\t uname:" + uname);

				}
			} catch (Exception e) {

			} finally {
				try {
					rs.close();
					db.closePst(pst);

				} catch (SQLException e) {
					// e.printStackTrace();
				}

			}
		}

		System.out.println("耗时：" + (System.nanoTime() - begin) / 1000000000f	+ "秒" );
	}

	static void test6() throws SQLException, JMException {
		JdbcStatManager.getInstance().reset();
		String url = "jdbc:wrap-jdbc:filters=default:jdbc:mysql://192.168.1.246:3306/game?user=root&password=shangjie";

		Connection conn = DriverManager.getConnection(url);
		conn.close();
		System.out.println(JdbcStatManager.getInstance().getConnectionstat()
				.getConnectCount());
		System.out.println(JdbcStatManager.getInstance().getDataSourceList());

	}

	@SuppressWarnings("unused")
	static void test2() throws SQLException {
		// String jdbcUrl = "jdbc:mysql://192.168.1.246:3306/game";
		String jdbcUrl = "jdbc:wrap-jdbc:filters=default:jdbc:mysql://192.168.1.246:3306/game";
		String user = "root";
		String password = "shangjie";

		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setFilters("stat");

		// JdbcStatManager.getInstance().reset(); // 重置计数器

		long begin = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT rank,uname from city_elite order by rank");

			while (rs != null && rs.next()) {

				int rank = rs.getInt("rank");
				String uname = rs.getString("uname");

				// System.out.println( "rank:" + rank + "\t\t uname:" + uname );

			}
			// System.out.println( i );
			rs.close();
			stmt.close();
			conn.close();
		}

		System.out.println("耗时：" + (System.nanoTime() - begin) / 1000000000f
				+ "秒");
		System.out.println("获取连接"
				+ JdbcStatManager.getInstance().getConnectionstat()
						.getConnectCount() + "次！");
		System.out.println("关闭连接"
				+ JdbcStatManager.getInstance().getConnectionstat()
						.getCloseCount() + "次！");
		dataSource.close();

	}

	static void test7() {
		int count = 1000000;
		UserInfo user = new UserInfo( null, 1 );
		long begin = System.nanoTime();
		for (int i = 0; i < count; i++) {
			AwardInfo award = new AwardInfo( AwardType.CASH, (short)500 );
			user.getAward( award, "test7" );
//			user.changeCash( 500, "test7" );
		}
		System.out.println("执行" + count + "次，耗时：" + (System.nanoTime() - begin)
				/ 1000000000f + "秒");
	}

	public static void main(String[] args) throws SQLException, JMException {

		test7();
	}

}
