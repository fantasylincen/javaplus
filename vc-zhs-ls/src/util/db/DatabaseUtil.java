package util.db;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import util.SystemTimer;
import util.UtilBase;



public class DatabaseUtil {

	/**
	 * 配置文件的路径
	 */
	//private static final String			FILE	= "config/db.xml";

	
	private final static Logger 		logger = LoggerFactory.getLogger( DatabaseUtil.class );
	private static DataSource	 		dataSource;
	
	static{
		init();
	}
	/**
	 *	从连接池中获取一个有效连接 
	 * @return
	 * 		返回一个可用数据库连接
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	
	/**
	 * 从配置表中读取相应的连接参数，初始化数据库连接池
	 */
	public static void init () {
		try {
			dataSource = DruidDataSourceFactory.createDataSource( MySqlConfigProperty.getInstance().getProperties() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 关闭本次查询所有的打开的资源，除了rs，其余两个资源应该不可能为null，有待考证
	 * @param rs
	 * @param st
	 * @param con
	 */
	public static void close( ResultSet rs, Statement st, Connection con ){
		try {
			if( rs != null ){
				rs.close();
			}
			if( st != null ){
				st.close();
			}
			con.close();
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
	public static long getMaxId( String tableName, String col ){
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sql = "select max(" + col + ") from " + tableName;
		
		try {
			pst = con.prepareStatement( sql );
			rs = pst.executeQuery();
			
			if (rs.next()) {
				return rs.getLong( 1 );
			}
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		return 0;
	}

	public static void main ( String[] args ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								
		ResultSet res 			= null;
		int uid					= 0;
		
		ByteBuffer data =  ByteBuffer.allocate(1000);
		data.putInt( 100 );
		data.putInt( 101 );
		data.putInt( 102 );
		data.putInt( 103 );
		data.putInt( 104 );
		data.putInt( 105 );
		UtilBase.encodeString(data, "阿三大神大神大神");
		UtilBase.encodeString(data, "asdas");
		data.put( (byte)105 );
		data.put( (byte)12 );
		data.putShort( (short)12332 );
		
		
		String sql = "insert into battle_info_base (uname,server_id,points_id,the_lv,data,timer) "
		+ "values (?,?,?,?,?,?)";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, "asdass1" );
			
			pst.setInt( i++, 12312 );
			
			pst.setShort( i++, (short)1 );
			
			pst.setByte( i++, (byte)1 );
			
			pst.setBytes( i++, data.array() );
			
			pst.setInt( i++, SystemTimer.currentTimeSecond() );
			
			pst.executeUpdate();
			
			sql = "SELECT u_id from battle_info_base ORDER BY u_id DESC LIMIT 1";
			pst.close();
			pst = con.prepareStatement( sql );
			res = pst.executeQuery();
			if ( res.next() ){
				uid = res.getInt(1);
				System.out.println( "uid:" + uid );
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		con 			= DatabaseUtil.getConnection();
		pst				= null;
		sql 			= "SELECT data from battle_info_base where uname=? and server_id=? and u_id = ?";
		i				= 1;
		try {
			
			pst = con.prepareStatement( sql );
			pst.setString( i++, "asdass1" );
			pst.setInt( i++, 12312 );
			pst.setInt( i++, uid );
			
			res = pst.executeQuery();
			if ( res.next() ){
				
//				ByteBuffer buff 	= ByteBuffer.wrap( InputStreamTOByte( res.getBlob( "data" ).getBinaryStream() ) );
				ByteBuffer buff = ByteBuffer.wrap( res.getBytes("data") );
				
				System.out.println( buff.getInt() );
				System.out.println( buff.getInt() );
				System.out.println( buff.getInt() );
				System.out.println( buff.getInt() );
				System.out.println( buff.getInt() );
				System.out.println( buff.getInt() );
				System.out.println( UtilBase.decodeString(buff) );
				System.out.println( UtilBase.decodeString(buff) );
				System.out.println( buff.get() );
				System.out.println( buff.get() );
				System.out.println( buff.getShort() );
			}
			
		} catch (Exception e) {
	
		} finally{
			DatabaseUtil.close( null, pst, con );
		}
		
	}

	
}
