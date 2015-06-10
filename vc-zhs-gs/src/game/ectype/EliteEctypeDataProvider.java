package game.ectype;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import util.db.DatabaseUtil;

/**
 * 英雄副本 记录次数 数据库
 * @author DXF
 */
public class EliteEctypeDataProvider {

	private static EliteEctypeDataProvider instance = new EliteEctypeDataProvider();
	static  EliteEctypeDataProvider getInstance(){
		return instance;
	}
	private EliteEctypeDataProvider(){
	}

	public Object[] get(UserInfo user) {
		
		// 获取数据库连接
		Connection con 				= DatabaseUtil.getConnection();
		PreparedStatement pst 		= null;
		ResultSet rs 				= null;
		
		
		Object[] obj 			= new Object[4];
		obj[0]					= new ArrayList<EliteCountBase>();
		obj[1]					= new ArrayList<Byte>();
		obj[2]					= new ArrayList<Byte>();
		obj[3]					= new ArrayList<ArrayList<Byte>>();
		
		int i 						= 1;
		try {
			String sql = "SELECT * from elite_ecytpe_info where uname=?";
			
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs 	= pst.executeQuery();
			
			if( rs.next() ) {
				
				List<EliteCountBase> list 			= new ArrayList<EliteCountBase>();
				EliteEctypeCountManager.lastTime 	= rs.getInt( "time" );
				
				String[] content 					= rs.getString( "content" ).split("\\|");
				
				for( String s : content ){
					
					if( s.isEmpty() ) continue;
					
					String[] temp 		= s.split(":");
					
					EliteCountBase	ecb = new EliteCountBase();
					ecb.m_nMid			= Short.parseShort( temp[0] );
					String[] plist 		= temp[1].split(";");
					for( String ss : plist ){
						
						String[] elite 		= ss.split(",");
						EliteCount ec		= new EliteCount( );
						ec.m_nPid			= Integer.parseInt( elite[0] );
						ec.m_nTodayCount	= Byte.parseByte( elite[1] );
						ec.m_nBuyCount		= Byte.parseByte( elite[2] );
						
						ecb.m_nPList.add( ec );
					}
						
					list.add( ecb );
				}
				
				obj[0]	= list;
				obj[1]	= mapingss( rs.getString( "dragonet_1" ) );
				obj[2]	= mapingss( rs.getString( "dragonet_2" ) );
				obj[3]	= mapingss1( rs.getString( "torefinewithfire" ) );
			}
			
		} catch (SQLException e) {
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return obj;
	}

	private List<List<Byte>> mapingss1( String string ) {
		List<List<Byte>> list 	= new ArrayList<List<Byte>>();
		if( string.isEmpty() )  return list;
		
		String[] content 		= string.split("\\|");
		if( content.length != 3 ) return list;
		
		for( int i = 0; i < content.length; i++ )
		{
			List<Byte> ls 		= new ArrayList<Byte>();
			String[] s 			= content[i].split(",");
			ls.add( Byte.parseByte( s[0]) );
			ls.add( Byte.parseByte( s[1]) );
			list.add(ls);
		}
		
		return list;
	}
	
	
	// 解析小龙数据
	private List<Byte> mapingss( String string ) {
		
		List<Byte> list 	= new ArrayList<Byte>();
		
		if( string.isEmpty() )  return list;
			
		String[] content	= string.split( "," );
		if( content.length != 3 ) return list;
		
		for( int i = 0; i < content.length; i++ ){
			list.add( Byte.parseByte( content[i] ) );
		}
		
		return list;
	}
	
	public void updata( UserInfo user, List<EliteCountBase> lists ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String sql 				= "update elite_ecytpe_info set content = ? where uname = ?";
		
		int	i 					= 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, maping( lists ) );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

	public void add( UserInfo user, List<EliteCountBase> lists, List<Byte> dragonetLists, List<Byte> dragonetLists1, List<List<Byte>> torefinewithfire ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql	 			= "insert into elite_ecytpe_info (uname,time,content,dragonet_1,dragonet_2,torefinewithfire) "
									+ "values (?,?,?,?,?,?)";
		int	i 					= 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			
			pst.setInt( i++, EliteEctypeCountManager.lastTime );
			
			pst.setString( i++, maping( lists ) );
			
			pst.setString( i++, mapingsss( dragonetLists ) );
			
			pst.setString( i++, mapingsss( dragonetLists1 ) );
			
			pst.setString( i++, mapingsss1( torefinewithfire ) );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	private String mapingsss1(List<List<Byte>> torefinewithfire) {
		
		StringBuilder content = new StringBuilder();
		
		for( List<Byte> ecb : torefinewithfire ){
			
			for( Byte b : ecb )
				content.append( b ).append(",");
			
			content.append( "|" );
		}
		
		return content.toString();
	}
	
	private String mapingsss(List<Byte> dragonetLists) {
		StringBuilder content = new StringBuilder();
		
		for( Byte ecb : dragonetLists ){
			content.append( ecb ).append(",");
		}
		return content.toString();
	}
	
	
	private String maping( List<EliteCountBase> lists ) {
		
		StringBuilder content = new StringBuilder();
		
		for( EliteCountBase ecb : lists ){
			
			content.append( ecb.m_nMid ).append(":");
			
			for( EliteCount ec : ecb.m_nPList ){
				
				content.append( ec.m_nPid ).append(",");
				content.append( ec.m_nTodayCount ).append(",");
				content.append( ec.m_nBuyCount ).append(";");
			}
			
			content.append("|");
		}
		
		return content.toString();
	}
	
	
	public void updataDragonet(UserInfo user, List<Byte> dragonetLists, List<Byte> dragonetLists1) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String sql 				= "update elite_ecytpe_info set dragonet_1 = ?, dragonet_2 = ? where uname = ?";
		
		int	i 					= 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, mapingsss( dragonetLists ) );
			pst.setString( i++, mapingsss( dragonetLists1 ) );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public void updataTorefine(UserInfo user, List<List<Byte>> torefineWithFire) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String sql 				= "update elite_ecytpe_info set torefinewithfire = ? where uname = ?";
		
		int	i 					= 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, mapingsss1( torefineWithFire ) );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public void updataAll( UserInfo user, List<EliteCountBase> lists, List<Byte> dragonetLists, List<Byte> dragonetLists1, List<List<Byte>> torefinewithfire ){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String sql 				= "update elite_ecytpe_info set time = ?, content = ?, dragonet_1 = ?, dragonet_2 = ?, torefinewithfire = ? where uname = ?";
		
		int	i 					= 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, EliteEctypeCountManager.lastTime );
			
			pst.setString( i++, maping( lists ) );
			
			pst.setString( i++, mapingsss( dragonetLists ) );
			
			pst.setString( i++, mapingsss( dragonetLists1 ) );
			
			pst.setString( i++, mapingsss1( torefinewithfire ) );
			
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

}
