package game.equipment;

import game.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import config.equipment.EquipmentTemplet;
import config.equipment.EquipmentTempletCfg;

import user.UserInfo;
import util.SystemTimer;
import util.db.DatabaseUtil;


public class EquipmentDataProvider {

	private static EquipmentDataProvider instance 	= new EquipmentDataProvider();
	static  EquipmentDataProvider getInstance(){
		return instance;
	}
	private EquipmentDataProvider(){}
	
	
	
	public ConcurrentHashMap<Integer, EquipmentInfo> get(UserInfo user) {
		
		ConcurrentHashMap<Integer,EquipmentInfo> lists = new ConcurrentHashMap<Integer, EquipmentInfo>();
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT * from equip_info where uname=? and is_remove=0";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs = pst.executeQuery();

			while( rs.next() ){
				
				int uid						= rs.getInt( "u_id" );
				int nid						= rs.getInt( "n_id" );
				int theirId					= rs.getInt( "their_id" );
				EquipmentTemplet templet 	= EquipmentTempletCfg.get(nid);
				
				EquipmentInfo e 			= new EquipmentInfo( uid, templet ) ;
				e.setTheirHeroID( theirId );
				
				lists.putIfAbsent( uid, e );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return lists;
	}
	
	public int create( UserInfo user, EquipmentInfo equ ) {
		
		Connection con 	= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		String sql = "insert into equip_info (u_id,uname,n_id,create_time) "
			+ "values (" + equ.getUID() + "," +
						user.getUID() + "," + 
						equ.getTemplet().getNId() + "," + 
						SystemTimer.currentTimeSecond() + ")";
		
		try {
			pst = con.prepareStatement( sql );
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return 0;
	}
	
	public void updata( UserInfo user, EquipmentInfo equ ){
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update equip_info set " +
				"n_id = ?, " +
				"their_id = ? " +
				"where u_id = ? and uname = ? and is_remove = 0 ";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, equ.getTemplet().getNId() );
			pst.setInt( i++, equ.getTheirHeroID() );
			pst.setInt( i++, equ.getUID() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public void updataToList( UserInfo user, List<EquipmentInfo> list ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String value			= "";
		String idstr			= "";
		for( EquipmentInfo e : list ){
			value				+= "WHEN " + e.getUID() + " THEN " + e.getTheirHeroID() + " ";
			idstr				+= !idstr.isEmpty() ? "," : "";
			idstr				+= e.getUID();
		}
		
		String sql 				= "UPDATE equip_info SET " + 
									"their_id = CASE u_id " + value + "END WHERE u_id IN (" + idstr + ")";
		
		try {
			pst = con.prepareStatement( sql );
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
	}
	
	public boolean remove(  UserInfo user, List<EquipmentInfo> list  ){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String idw				= "";
		for( int x = 0; x < list.size(); x++ ){
			if( !idw.isEmpty() ) idw += ",";
			idw 				+= "?";
		}
		
		String sql = "update equip_info set " +
				"is_remove = 1, " +
				"remove_time = ? " +
				"where u_id in(" + idw + ") and uname = ? and is_remove = 0";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, SystemTimer.currentTimeSecond() );
			for( EquipmentInfo e : list )
				pst.setInt( i++, e.getUID() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return false;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return true;
	}
	
	
	public void getback( UserInfo user, List<EquipmentInfo> list ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String idw				= "";
		for( int x = 0; x < list.size(); x++ ){
			if( !idw.isEmpty() ) idw += ",";
			idw 				+= "?";
		}
		
		String sql = "update equip_info set " +
				"is_remove = 0, " +
				"remove_time = 0 " +
				"where u_id in(" + idw + ") and uname = ? and is_remove = 1";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			for( EquipmentInfo e : list )
				pst.setInt( i++, e.getUID() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public void addSyn( UserInfo user, EquipmentSynInfo equSynInfo ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql = "insert into esynthesis_info(uname,syn_id,stuff_id) values (?,?,?)";
		int	i = 1;
		try {;
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, equSynInfo.synToStr() );
			pst.setString( i++, equSynInfo.stuffToStr() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public EquipmentSynInfo getSyn( UserInfo user ) {
		
		EquipmentSynInfo equip	= null;
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT * from esynthesis_info where uname=?";
		
		int	i 					= 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs 		= pst.executeQuery();

			if( rs.next() ){
				equip			= new EquipmentSynInfo( user );
				equip.setSyn( rs.getString( "syn_id" ) );
				equip.setStuff( rs.getString( "stuff_id" ) );
				equip.setCurtime( rs.getInt( "time" ) );
			}
			
		} catch ( SQLException e ) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return equip;
	}
	
	public void updataSyn( UserInfo user, EquipmentSynInfo equSynInfo ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql 				= "update esynthesis_info set " +
									"syn_id = ?, " +
									"stuff_id = ?, " +
									"time = ? " +
									"where uname = ?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, equSynInfo.synToStr() );
			pst.setString( i++, equSynInfo.stuffToStr() );
			pst.setInt( i++, equSynInfo.getCurtime() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	
	public static void main( String[] args ){
		
		EquipmentTempletCfg.init();
		
//		instance.create( new UserInfo( null, 1011842 ), new EquipmentInfo(-1, EquipmentTempletCfg.get( 1001 ), EColour.WHITE ));
//		instance.create( new UserInfo( null, 1011842 ), new EquipmentInfo(-1, EquipmentTempletCfg.get( 1001 ), EColour.WHITE ));
//		instance.create( new UserInfo( null, 1011842 ), new EquipmentInfo(-1, EquipmentTempletCfg.get( 1001 ), EColour.WHITE ));
//		instance.create( new UserInfo( null, 1011842 ), new EquipmentInfo(-1, EquipmentTempletCfg.get( 1001 ), EColour.WHITE ));
//		instance.create( new UserInfo( null, 1011842 ), new EquipmentInfo(-1, EquipmentTempletCfg.get( 1001 ), EColour.WHITE ));
//		instance.create( new UserInfo( null, 1011842 ), new EquipmentInfo(-1, EquipmentTempletCfg.get( 1001 ), EColour.WHITE ));
		
		
		List<EquipmentInfo> list = new ArrayList<EquipmentInfo>();
		
		instance.remove( new UserInfo( null, 1011842 ), list);
		
		System.out.println( "完成!" );
	}
	
}


