package experiment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import user.UserInfo;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;

import deng.xxoo.utils.XOTime;

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
		
		
		ConcurrentHashMap<Integer,UserInfo> onlineUsers 	= new ConcurrentHashMap<Integer, UserInfo>();
		List<UserInfo> ls 	= new ArrayList<UserInfo>();
		
		XOTime.beginTimer( );
		for( int i = 0; i < 1000; i++ ){
			onlineUsers.putIfAbsent( i, new UserInfo(null, i) );
		}
		XOTime.endTimerToPrint( );
		
		XOTime.beginTimer( );
		for( int i = 0; i < 1000; i++ ){
			ls.add( new UserInfo(null, i) );
		}
		XOTime.endTimerToPrint(  );
		
		
		
		XOTime.beginTimer(  );
//		for( UserInfo u : ls ) {
//			if( u.getUID() == 10 )
//				System.out.println( u );
//		}
		Iterator<UserInfo> it = ls.iterator();
        while (it.hasNext()) {
        	UserInfo u = it.next();
        	if( u.getUID() == 10 )
        		System.out.println( u );
        }
		XOTime.endTimerToPrint(  );
		
		XOTime.beginTimer(  );
		System.out.println( onlineUsers.get( 10 ) );
		XOTime.endTimerToPrint(  );
		
		XOTime.beginTimer(  );
		Iterator<UserInfo> it1 = ls.iterator();
        while (it1.hasNext()) {
        	UserInfo u = it1.next();
        	if( u.getUID() == 10 )
        		System.out.println( u );
        }
		XOTime.endTimerToPrint(  );
		
		XOTime.beginTimer(  );
		for ( UserInfo u : ls ) {
			if( u.getUID() == 10 )
				System.out.println( u );
		}
		XOTime.endTimerToPrint(  );
		
		
		list1( ls );
		list2( ls );
	}

	  public static void list1(List<UserInfo> list)
      {
              long l1 = System.currentTimeMillis();
              for (UserInfo string : list)
              {
            	  if( string.getUID() == 10 )
      				System.out.println( string );
//                      System.out.println(string);
              }
              System.out.println(System.currentTimeMillis() - l1);
      }

      public static void list2(List<UserInfo> list)
      {
              long l1 = System.currentTimeMillis();
              Iterator<UserInfo> it = list.iterator();
              while (it.hasNext())
              {
            	  	UserInfo str = it.next();
//                    System.out.println(str);
            	  	if( str.getUID() == 10 )
                		System.out.println( str );
              }
              System.out.println(System.currentTimeMillis() - l1);
      }
}
