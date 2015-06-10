package experiment.db;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import util.db.DatabaseUtil;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.stat.JdbcStatManager;
import com.alibaba.druid.stat.JdbcTraceManager;
import com.alibaba.druid.util.JMXUtils;

/**
 * 测试stat记录功能
 * @author Administrator
 * 2013-4-19 下午3:30:43
 */

public class StatFilterDB {
	
	static void func3() throws SQLException, InterruptedException{
		
		JMXUtils.register( "com.alibaba.dragoon:type=JdbcTraceManager", JdbcTraceManager.getInstance() );
		for (int i = 0; i < 1000; ++i) {
			Connection con = DatabaseUtil.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT 1");
			rs.next();
			Thread.sleep(1000);
			System.err.println( JdbcStatManager.getInstance().getConnectionstat().getConnectCount() );
			System.err.println( JdbcStatManager.getInstance().getConnectionstat().getAliveTotal() );
			rs.close();
			stmt.close();
			con.close();
		
		}
	}
	static void func2(){
		String sql = "select count(*) from user_base";

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
	
	static void func1(){
		
		 for (int i = 0; i < 10; i++)
		 {
			 System.gc();
	     }
		 
		 long memoryStart = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
		 
		 final int COUNT = 1024 * 1024;
		 AtomicLong[] items = new AtomicLong[COUNT];
		 for (int i = 0; i < COUNT; ++i) {
			 items[i] = new AtomicLong();
			 // items[i] = Histogram.makeHistogram(20);
		 }
		 long memoryEnd = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
		 System.out.println("memory used : " + (memoryEnd - memoryStart) );
	}
	
	public static void main ( String[] args ) throws SQLException, InterruptedException {
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select count(*) from user_base";
		
		try {
			pst = con.prepareStatement( sql );
			rs = pst.executeQuery();
			
			if (rs.next()) {
				System.out.println( rs.getLong( 1 ) );
			}
		} catch (SQLException e) {
			//logger.debug( e.getLocalizedMessage(), e );
			System.out.println( e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		func1();
		func2();
		func3();
	}
}