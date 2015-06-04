package game.friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.UserInfo;
import user.UserManager;
import util.db.DatabaseUtil;


/**
 *  好友数据库
 * @author DXF
 *
 */
public class FriendInfoDataProvider {
	
	private static FriendInfoDataProvider instance 	= new FriendInfoDataProvider();
	static  FriendInfoDataProvider getInstance(){
		return instance;
	}
	private FriendInfoDataProvider(){}
	
	
	/**
	 * 获取所有好友信息
	 * @param user
	 * @return
	 */
	public FriendInfo get( UserInfo user ) {
		
		FriendInfo	friendInfo 	= null;
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql = "SELECT * from friend_info where uname=? ";
		
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				friendInfo				= new FriendInfo();
				String friendContent	= rs.getString( "friend_content" );
				String begContent		= rs.getString( "beg_content" );
				friendInfo.gvTimes		= rs.getShort("gv_times");
				friendInfo.getTimes		= rs.getShort("get_times");
				friendInfo.recordTime	= rs.getInt("record_time");
				
				if( !friendContent.isEmpty() ){
				
					String[] list	= friendContent.split("\\|");
					
					for( String s : list ){
						FriendBase fb = mapping( s );
						if( fb != null )
							friendInfo.friends.putIfAbsent( fb.getUid(), fb );
					}
					
//					friendInfo.pageControl.updata( friendInfo.friends );
				}
				
				if( !begContent.isEmpty() ){
					
					String[] list	= begContent.split("\\|");
					
					for( String s : list ){
						int uid	= Integer.parseInt( s );
						if( UserManager.getInstance().getByName(uid) == null ) 
							continue;
						friendInfo.begFriend.add( uid );
					}
					
//					friendInfo.begPageControl.updata( friendInfo.begFriend );
				}
				
			}
			
		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return friendInfo;
	}
	private FriendBase mapping( String content ) throws SQLException {
		
		if( content.isEmpty() ) return null;

		String[] list = content.split(",");
		if( list.length != 3 ) return null;
		int uid	= Integer.parseInt( list[0] );
		if( UserManager.getInstance().getByName(uid) == null ) return null;
		
		FriendBase fb 	= new FriendBase();
		fb.setUid( uid );
		fb.setGvStrength( Integer.parseInt( list[1] ) );
		fb.setGetStrength( Integer.parseInt( list[2] ) );
		
		return fb;
	}
	
	/**
	 * 添加
	 * @param user
	 * @param friendInfo
	 */
	public void add(UserInfo user, FriendInfo friendInfo) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "insert into friend_info (uname,friend_content,beg_content,gv_times,get_times,record_time) "
			+ "values (?,?,?,?,?,?)";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, friendInfo.getFriendContent() );
			pst.setString( i++, friendInfo.getBegContent() );
			pst.setShort( i++, friendInfo.gvTimes );
			pst.setShort( i++, friendInfo.getTimes );
			pst.setInt( i++, friendInfo.recordTime );
			
			pst.executeUpdate();
		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	/**
	 * 刷新 所有数据
	 * @param user
	 * @param friendInfo
	 */
	public void updata(UserInfo user, FriendInfo friendInfo) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update friend_info set " +
				"friend_content = ?, " +
				"beg_content = ?, " +
				"gv_times = ?, " +
				"get_times = ?, " +
				"record_time = ? " +
				"where uname = ? ";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, friendInfo.getFriendContent() );
			pst.setString( i++, friendInfo.getBegContent() );
			pst.setShort( i++, friendInfo.gvTimes );
			pst.setShort( i++, friendInfo.getTimes );
			pst.setInt( i++, friendInfo.recordTime );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		
	}
	
	/**
	 * 刷新好友列表数据
	 * @param user
	 * @param friendInfo
	 */
	public void updataFriend(UserInfo user, FriendInfo friendInfo) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update friend_info set " +
				"friend_content = ? " +
				"where uname = ? ";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, friendInfo.getFriendContent() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	/**
	 * 刷新申请列表数据
	 * @param user
	 * @param friendInfo
	 */
	public void updataBeg(UserInfo user, FriendInfo friendInfo) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update friend_info set " +
				"beg_content = ? " +
				"where uname = ? ";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, friendInfo.getBegContent() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
	}
	
	
}
