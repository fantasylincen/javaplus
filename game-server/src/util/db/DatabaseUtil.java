package util.db;


import game.log.Logs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import util.FileHandle;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;


public class DatabaseUtil {

	// 数据源
	private static DruidDataSource dataSource = null;

	// 初始化
//	static {
//		initialize();
//	}
	public static void initialize() {
		try {
			Properties properties = loadPropertyFile( "config/dbconfig.properties" );
//			String url = properties.getProperty( "url" );
//			properties.setProperty( "url", url + "_" + SystemCfg.GAME_DISTRICT );
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Properties loadPropertyFile( String configName ) {
		if (null == configName || configName.isEmpty())
			throw new IllegalArgumentException(
					"Properties file path can not be null : " + configName );

		Properties properties = new Properties();
		try {
			
			InputStream input = new FileInputStream( configName );
			properties.load( input );
			input.close();
		
		} catch (FileNotFoundException e) { e.printStackTrace();
		} catch (IOException e) { e.printStackTrace(); }

		return properties;
	}
	
//	private static boolean isHave(){
//		ResultSet rsTables = meta.getTables("michaeldemo", null, tableName, new String[] { "TABLE" }); 
		
//	}

	/**
	 *	从连接池中获取一个有效连接 
	 * @return
	 * 		返回一个可用数据库连接
	 */
	public static DruidPooledConnection getConnection(){
		DruidPooledConnection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			if( e.getMessage().startsWith( "Unknown database" ) ){
				
				
			}
		}
//		Logs.debug( "返回数据库Connection成功,当前连接数：" + dataSource.getActiveCount() + "/" + dataSource.getMaxActive() );
		return conn;
	}

	/**
	 * 关闭本次查询所有的打开的资源，除了rs，其余两个资源应该不可能为null，有待考证
	 * @param rs
	 * @param st
	 * @param con
	 */
	public static void close( ResultSet rs, Statement st, Connection con ){
		try {
			if( rs != null )
				rs.close();
			if( st != null )
				st.close();
			if( con != null )
				con.close();
//			Logs.debug( "释放Connection成功，当前连接数：" + dataSource.getActiveCount() + "/" + dataSource.getMaxActive() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库某张表的最大id
	 * @param tableName
	 * @param col
	 * @return
	 */
	public static int getMaxId( String tableName, String col, String criteria ){
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select max(" + col + ") from " + tableName + " where " + criteria;
		
		try {
			pst = con.prepareStatement( sql );
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getInt( 1 );
			}
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		return 0;
	}
	
	/**
	 * 获取某张表的数据总数
	 * @param tableName
	 * @return
	 */
	public static long getCount( String tableName ){
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select COUNT(*) from " + tableName;
		
		try {
			pst = con.prepareStatement( sql );
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getLong( 1 );
			}
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		return 0;
	}
	

	/** 清空所有表数据 */
	public static void prugeAll(){
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		
	
		try {
			
			for( String name : getAllTableName() ){
				
				String sql 		= "TRUNCATE TABLE " + name;
				pst 			= con.prepareStatement( sql );
				pst.executeUpdate();
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
	}

	/**
	 * 获取所有表名
	 * @return
	 */
	public static List<String> getAllTableName(){
		
		List<String> list		= new ArrayList<String>();
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		
		String sql 				= "SELECT TABLE_SCHEMA as 'Use', TABLE_NAME as 'TableName' FROM INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='game' order by TABLE_NAME";
		try {
			pst 		= con.prepareStatement( sql );
			rs 			= pst.executeQuery();
			
			while( rs.next() ){
				
				String name = rs.getString( "TableName" );
				
				if( name.equals( "dragon_info" ) || name.equals( "log_purcharse" ) )
					continue;
				
				list.add( name );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return list;
	}

	
	public static void del( int id ){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update fight_reconnect set " +
				"is_remove = 1 where uname = " + id;
		try {
			pst = con.prepareStatement( sql );
			pst.executeUpdate();
		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	
	public static void getUserLevel() throws IOException{
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		try {
			String sql 		= "SELECT nick_name,level from user_base";
			pst 			= con.prepareStatement( sql );
			rs 				= pst.executeQuery();

			FileHandle.creatFileToTxt( "D:/玩家等级分布" );
			List<String> list = new ArrayList<String>();
			List<ulevel> xxxx = new ArrayList<ulevel>();
			while( rs.next() ) {
				
				ulevel u	= new ulevel();
				u.content	= transformToStr_6( rs.getString( "nick_name" ) ) + "->" + rs.getInt( "level" );
				u.level		= rs.getInt( "level" );
				xxxx.add( u );
			}
			
			Collections.sort( xxxx, posComparator );
			
			for( ulevel u : xxxx )
				list.add( u.content );
			
			FileHandle.writeFile( list );
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
	}
	
	private static final Comparator<ulevel> posComparator = new Comparator<ulevel>(){
        @Override
        public int compare( ulevel f1, ulevel f2 ) {
            return f2.level - f1.level;
        }
    };
	
	private static String transformToStr_6( String name ) {
		
		int l 			= name.getBytes().length - name.length();
		int cl			= name.length() + (l == 0 ? 0 : l/2);
		cl				= 14 - cl;
		
		String result 	= "";
		while( cl-- > 0 )
			result		+= " ";
		
		return name + result + " ";
	}
	
	public static void main ( String[] args ) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
//		System.out.println( getMaxId( "qualifying_battle_info", "u_id", "uname=297437113" ) );
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    String url = "jdbc:mysql://localhost/mysql";
	    Connection connection = DriverManager.getConnection(url, "root", "shangjie");
	    Statement statement = connection.createStatement();
	    String hrappSQL = "CREATE DATABASE hrapp";
	    statement.executeUpdate(hrappSQL);
		
	}
	
	
}

class ulevel{
	
	public String content;

	public int 		level;
}

