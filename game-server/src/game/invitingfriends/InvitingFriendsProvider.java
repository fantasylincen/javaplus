package game.invitingfriends;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.UserInfo;
import user.UserManager;
import util.db.DatabaseUtil;

public class InvitingFriendsProvider {

	private static InvitingFriendsProvider instance 	= new InvitingFriendsProvider();
	static  InvitingFriendsProvider getInstance(){
		return instance;
	}
	private InvitingFriendsProvider(){}
	
	
	public InvitingFriendsBase get( UserInfo user ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql = "SELECT * from inviting_friend_info where uname=? ";
		
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				
				int uid						= rs.getInt( "inviting_my" );
				String content				= rs.getString( "my_inviting_content" );
				if( UserManager.getInstance().getByName(uid) == null )
					uid = -1;
				
				InvitingFriendsBase base 	= new InvitingFriendsBase();
				base.setIMyID( uid );
				base.setListContent( content );
				
				return base;
			}
			
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return null;
	}
	
	public void add( UserInfo user, InvitingFriendsBase invitingFriendsBase ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		
		String sql 				= "insert into inviting_friend_info(uname,inviting_my,my_inviting_content) values(?,?,?)";
		int	i 					= 1;
		try {;
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, invitingFriendsBase.getIMyID() );
			pst.setString( i++, invitingFriendsBase.getListContent());
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public void updata( UserInfo user, InvitingFriendsBase invitingFriendsBase ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql = "update inviting_friend_info set " +
				"inviting_my = ?, " +
				"my_inviting_content = ? " +
				"where uname=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, invitingFriendsBase.getIMyID() );
			pst.setString( i++, invitingFriendsBase.getListContent() );
			
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
}
