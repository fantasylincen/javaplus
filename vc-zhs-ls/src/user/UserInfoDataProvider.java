package user;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.CRC32;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.SystemTimer;
import util.db.DatabaseUtil;

/**
 * 和数据库打交道
 * 单体
 */
class UserInfoDataProvider {
	private final static Logger logger = LoggerFactory.getLogger(UserInfoDataProvider.class);
	private static UserInfoDataProvider instance = new UserInfoDataProvider();
	static  UserInfoDataProvider getInstance(){
		return instance;
	}
	private UserInfoDataProvider(){
	}
	
	/**
	 * 根据标识获取索引
	 * @param identifying
	 * @return
	 */
	public int get( String identifying ) {
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql = "SELECT uId from user_account where identifying=?";
			pst = con.prepareStatement( sql );
			pst.setString( 1, identifying );
			
			rs = pst.executeQuery();

			if( rs.next() ) {
				return rs.getInt("uId");
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
			return -2;
		}
		finally{
			DatabaseUtil.close( rs, pst, con );
		}
		
		return -1;
	}
	/**
	 * 根据账号
	 * @param account
	 * @param password
	 * @return -1账号不存在  -2密码不正确
	 */
	public int get(String account, String password) {
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql = "SELECT * from user_account where bind_account=?";
			pst = con.prepareStatement( sql );
			pst.setString( 1, account );
			
			rs = pst.executeQuery();

			if( rs.next() ) {
				String pass = rs.getString( "password" );
				// 如果密码相同
				if( pass.equals( password ) )
					return rs.getInt("uId");
				
				return -2;
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return -1;
	}
	
	/**
	 * 申请密码时候 检查 是否有这个邮件和 账号
	 * @param account
	 * @param mail_address
	 * @return
	 */
	public String get_toapplyPassword(String account, String mail_address) {
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql = "SELECT mail_address,password from user_account where bind_account=?";
			pst = con.prepareStatement( sql );
			pst.setString( 1, account );
			
			rs = pst.executeQuery();

			if( rs.next() ) {
				String address = rs.getString( "mail_address" );
				// 如果邮件相同 就返回密码
				if( address.equals( mail_address ) )
					return rs.getString( "password" );
				return "2";
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		return "1";
	}
	
	public String getUID( int index )  {
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql = "SELECT identifying from user_account where uId=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, index );
			
			rs = pst.executeQuery();

			if( rs.next() ) {
				
				return rs.getString( "identifying" );
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return null;
	}
	/**
	 * 根据终端标识在数据库创建一条账号
	 * @param identifying
	 * @return
	 */
	public int create( String identifying ) {
		
		CRC32 crc				= new CRC32();
		
		// 利用当前毫秒数 保证唯一性
		String str 				= identifying.concat( String.valueOf( SystemTimer.currentTimeMillis() ) );
		// 先根据终端标识  用crc生成一个32位唯一码
		crc.update( str.getBytes() );
		// 强制改成一个9长度的数
		int value				= toNew( crc.getValue() );
		
		while( getUID( value ) != null ){
			if( ++value >= 1000000000 )
				value = 100000000;
		}
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "insert into user_account (uId,identifying) values (?,?)";
		
		int	i 					= 1;
		try {
			pst 				= con.prepareStatement( sql );
			
			pst.setInt( i++, value );
			pst.setString( i++, identifying );
			
			pst.executeUpdate();
			
			return value;
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return -1;
	}
	private int toNew( long value ){
		
		int newVlaue 	= 0;
	    int length		= String.valueOf( value ).length();
	    
	    int l 			= Math.abs( length - 9 );
    	int xx			= (int) Math.pow( 10, l );
	    if( length > 9 ) {
	    	newVlaue	= (int) (value / xx);
	    }else if( length < 9 ){
	    	newVlaue	= (int) (value * xx);
	    }else{
	    	newVlaue	= (int) value;
	    }
	    
		return newVlaue;
	}
	
	/**
	 * 绑定账号
	 * @param uid
	 * @param account
	 * @param pass
	 * @param mail_address 
	 * @return
	 */
	public byte bind( int uid, String account, String pass, String mail_address) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "update user_account set " +
				"identifying=''," +
				"bind_account=?," +
				"password=?," +
				"mail_address=? " +
				"where uId=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, account );
			pst.setString( i++, pass );
			pst.setString( i++, mail_address );
			
			pst.setInt( i++, uid );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
			return -1;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return 0;
	}
	
	/**
	 * 获取账号 是否激活
	 * @param index
	 * @return
	 */
	public byte getIsAC( int index ) {
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql 	= "SELECT is_ac from user_account where uId=?";
			pst 		= con.prepareStatement( sql );
			pst.setInt( 1, index );
			
			rs 			= pst.executeQuery();

			byte ac		= rs.getByte( "is_ac" );
			if( ac == 0 ) // 表示还没有激活
				return -3;
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
			return -1;
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return 0;
	}
	
	/**
	 * 根据玩家UID 获取上次登陆区ID
	 * @param uid
	 * @return
	 */
	public short getToDistrictID( int uid ) {
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql 	= "SELECT last_district_id from user_account where uId=?";
			pst 		= con.prepareStatement( sql );
			pst.setInt( 1, uid );
			
			rs 			= pst.executeQuery();

			if( rs.next() ) {
				return rs.getShort( "last_district_id" );
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return -1;
	}
	public void updateToDistrictID( int index, short serverid ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "update user_account set " +
				"last_district_id=? " +
				"where uId=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setShort( i++, serverid );
			pst.setInt( i++, index );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	
	/**
	 * 91 获取UID
	 * @param accountID
	 * @param sessionID
	 * @return
	 */
	public int get_91( String accountID, String sessionID ) {
		
		
		
		return 0;
	}
	
	/**
	 * 注册
	 * @param account
	 * @param pass
	 * @param mail_address 
	 * @return
	 */
	public int register( String account, String pass, String mail_address ) {
		CRC32 crc				= new CRC32();
		
		// 利用当前毫秒数 保证唯一性
		String str 				= account.concat( String.valueOf( SystemTimer.currentTimeMillis() ) );
		// 先根据终端标识  用crc生成一个32位唯一码
		crc.update( str.getBytes() );
		// 强制改成一个9长度的数
		int value				= toNew( crc.getValue() );
		
		while( getUID( value ) != null ){
			if( ++value >= 1000000000 )
				value = 100000000;
		}
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "insert into user_account (uId,bind_account,password,mail_address) values (?,?,?,?)";
		
		int	i 					= 1;
		try {
			pst 				= con.prepareStatement( sql );
			
			pst.setInt( i++, value );
			pst.setString( i++, account );
			pst.setString( i++, pass );
			pst.setString( i++, mail_address );
			
			pst.executeUpdate();
			
			return value;
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return -1;
	}

	public boolean isPsytopic( int uid ) {
		
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		
		try {
			String sql 	= "SELECT jurisdiction from psytopic_account where uId=?";
			pst 		= con.prepareStatement( sql );
			pst.setInt( 1, uid );
			
			rs 			= pst.executeQuery();

			if( rs.next() ) {
				return rs.getByte( "jurisdiction" ) == 1;
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		return false;
	}
	
	
	public static void main( String[] args ){
		CRC32 crc				= new CRC32();
		// 利用当前毫秒数 保证唯一性
		String str 				= "dz_wolaiwan".concat( String.valueOf( SystemTimer.currentTimeMillis() ) );
		// 先根据终端标识  用crc生成一个32位唯一码
		crc.update( str.getBytes() );
		
		System.out.println( crc.getValue() );
	}
	
	public int getUIDtoAid( String aid ) {
		Connection 			con = DatabaseUtil.getConnection();
		PreparedStatement 	pst = null;
		ResultSet 			rs 	= null;
		try {
			String sql 	= "SELECT uId from user_account where bind_account=?";
			pst 		= con.prepareStatement( sql );
			pst.setString( 1, aid );
			
			rs 			= pst.executeQuery();

			if( rs.next() ) {
				return rs.getInt( "uId" );
			}
			
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		return -1;
	}
	public int create_dz( String account ) {
		
		CRC32 crc				= new CRC32();
		
		// 利用当前毫秒数 保证唯一性
		String str 				= account.concat( String.valueOf( SystemTimer.currentTimeMillis() ) );
		// 先根据终端标识  用crc生成一个32位唯一码
		crc.update( str.getBytes() );
		// 强制改成一个9长度的数
		int value				= toNew( crc.getValue() );
		
		while( getUID( value ) != null ){
			if( ++value >= 1000000000 )
				value = 100000000;
		}
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "insert into user_account (uId,bind_account) values (?,?)";
		
		int	i 					= 1;
		try {
			pst 				= con.prepareStatement( sql );
			
			pst.setInt( i++, value );
			pst.setString( i++, account );
			
			pst.executeUpdate();
			
			return value;
		} catch (SQLException e) {
			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return -1;
	}


	
}
