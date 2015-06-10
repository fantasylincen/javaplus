package game.ectype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import user.UserInfo;
import util.db.DatabaseUtil;


/**
 * 副本星级数据库
 * @author DXF
 */
public class EctypeStartLevelDataProvider {
	private static EctypeStartLevelDataProvider instance = new EctypeStartLevelDataProvider();
	public static  EctypeStartLevelDataProvider getInstance(){
		return instance;
	}
	private EctypeStartLevelDataProvider(){
	}
	
	public ConcurrentHashMap<Integer, Byte> get( UserInfo user ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		try {
			String sql = "SELECT content from ectype_start where name=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			rs = pst.executeQuery();

			if( rs.next() ) {
				return analysis( rs.getString( "content" ) );
			} else {//数据库无此玩家
				return null;
			}			
		} catch (SQLException e) {
			return null;
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
	}
	private ConcurrentHashMap<Integer, Byte> analysis( String string ) {
			
		ConcurrentHashMap<Integer, Byte> result = new ConcurrentHashMap<Integer, Byte>();
		
		if( string.isEmpty() ) return result;
		
		String[] list = string.split("\\|");
		
		for( String x : list ){
			String[] content = x.split( "," );
			int id 		= Integer.parseInt( content[0] );
			byte star 	= Byte.parseByte( content[1] );
			result.putIfAbsent( id, star );
		}
		return result;
	}
	
	public void update( UserInfo user, ConcurrentHashMap<Integer, Byte> starLevel ) {
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "update ectype_start set content=? where name=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString(i++, listToString(starLevel) );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	private String listToString(ConcurrentHashMap<Integer, Byte> starLevel) {
		
		StringBuilder content = new StringBuilder();
		for( Map.Entry<Integer,Byte> e: starLevel.entrySet() )
			content.append( e.getKey() ).append(",").append( e.getValue() ).append( "|" );
		return content.toString();
	}
	
	public void add( UserInfo user ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "insert into ectype_start (name,content) values(?,'')";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	
	
	
}
