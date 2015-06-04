package game.events.all.tickling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import user.UserInfo;
import util.SystemTimer;
import util.db.DatabaseUtil;

public class TicklingDB {

	public static void put( String content, UserInfo user ) {
		
		TicklingBase t = isHave( user ); 
		if( t != null ){
			update( t, content, user );
		}else{
			add( content, user );
		}
	}
	
	private static void add( String content, UserInfo user ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		String sql = "insert into tickling_info(Id,uname, content, time) values" +
		  "(?,?,?,?)";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getBasisUniqueID().TICKLINGINFO_ID() );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, SystemTimer.currentTimeSecond() + ";" + content );
			pst.setInt( i++, SystemTimer.currentTimeSecond() );
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

	private static void update( TicklingBase t, String content, UserInfo user ) {
		
		String cont = t.getContent() + "&." + SystemTimer.currentTimeSecond() + ";" + content;
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "update tickling_info set " +
				"content=?," +
				"time=? " +
				"where Id=? and uname=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, cont );
			pst.setInt( i++, SystemTimer.currentTimeSecond() );
			pst.setInt( i++, t.id );
			pst.setInt( i++, user.getUID() );
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

	private static TicklingBase isHave( UserInfo user ){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;						
		ResultSet rs 			= null;
		try {
			String sql 			= "SELECT * from tickling_info where uname=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			rs = pst.executeQuery();
			if( rs.next() ){
				TicklingBase t	= new TicklingBase();
				t.content 		= rs.getString("content").split( "&." );
				t.uid			= rs.getInt( "uname" );
				t.time			= rs.getInt( "time" );
				t.id			= rs.getInt( "Id" );
				t.reply			= rs.getString( "reply" ).split( "&." );
				return t;
			}
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( rs, pst, con );
		}
		return null;
	}
	
	public static List<TicklingBase> get(){
		
		List<TicklingBase> list = new ArrayList<TicklingBase>();
		List<TicklingBase> list1 = new ArrayList<TicklingBase>();
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from tickling_info";
			pst = con.prepareStatement( sql );
			rs = pst.executeQuery();

			while( rs.next() ) {
				TicklingBase t	= new TicklingBase();
				String temp		= rs.getString("content");
				if( !temp.isEmpty() )
					t.content 	= temp.split( "&." );
				t.uid			= rs.getInt( "uname" );
				t.time			= rs.getInt( "time" );
				t.id			= rs.getInt( "Id" );
				temp			= rs.getString( "reply" );
				if( !temp.isEmpty() )
					t.reply		= temp.split( "&." );
				
				if( t.reply == null )
					list1.add( t );
				else
					list.add( t );
			}
		} catch (SQLException e) {
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		Collections.sort( list, posComparator );
		Collections.sort( list1, posComparator );
		
		list.addAll(list1);
		return list;
	}
	
	public static final Comparator<TicklingBase> posComparator = new Comparator<TicklingBase>(){
        @Override
        public int compare( TicklingBase f1, TicklingBase f2 ) {
            return f1.time - f2.time;
        }
	};

	public static TicklingBase get( int index, UserInfo user ) {
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from tickling_info where Id=? and uname=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, index );
			pst.setInt( 2, user.getUID() );
			rs = pst.executeQuery();

			if( rs.next() ) {
				TicklingBase t	= new TicklingBase();
				t.content 		= rs.getString("content").split( "&." );
				t.uid			= rs.getInt( "uname" );
				t.time			= rs.getInt( "time" );
				t.id			= rs.getInt( "Id" );
				t.reply			= rs.getString( "reply" ).split( "&." );
				return t;
			}
		} catch (SQLException e) {
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		return null;
	}

	public static void updateReply( TicklingBase t, String content, UserInfo user ) {
		
		String cont = t.getReplyToString() + "&." + SystemTimer.currentTimeSecond() + ";" + content;
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "update tickling_info set " +
				"reply=? " +
				"where Id=? and uname=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, cont );
			pst.setInt( i++, t.id );
			pst.setInt( i++, user.getUID() );
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	

}
