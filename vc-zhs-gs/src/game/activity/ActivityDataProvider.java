package game.activity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.db.DatabaseUtil;


/**
 * 活动 数据库
 * @author DXF
 */
public class ActivityDataProvider {

	private static ActivityDataProvider instance = new ActivityDataProvider();
	static  ActivityDataProvider getInstance(){
		return instance;
	}
	private ActivityDataProvider(){
	}

	public void create(){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		String sql 				= "insert into dragon_info (is_open) values (0)";
		try {
			pst = con.prepareStatement( sql );
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public DragonBase get( ) {
		
		DragonBase dragonBase 	= null;
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 				= "SELECT * from dragon_info";
		
		try {
			pst = con.prepareStatement( sql );
			rs 	= pst.executeQuery();

			if( rs.next() ){
				
				byte open = rs.getByte( "is_open" );
				if( open != 1 ) return null;
				
				dragonBase = new DragonBase();
				dragonBase.set_hpBase( rs.getInt( "hp_base" ) );
				dragonBase.set_hpCur( rs.getInt( "hp_cur" ) );
				dragonBase.set_attackBase( rs.getInt( "attack_base" ) );
				dragonBase.setUserList( rs.getString( "user_list" ) );
			}else{
				create();
			}
			
		} catch (SQLException e) {
			System.out.println( e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return dragonBase;
	}
	
	public void update( DragonBase _dragonBase ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update dragon_info set " +
				"hp_base = ?, " +
				"hp_cur = ?, " +
				"attack_base = ?, " +
				"is_open = ?, " +
				"user_list = ? " ;
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, _dragonBase.getHpMax() );
			pst.setInt( i++, _dragonBase.getHpCur() );
			pst.setInt( i++, _dragonBase.getAttack() );
			pst.setByte( i++, (byte)(_dragonBase.isOpen() ? 1 : 0) );
			pst.setString( i++, _dragonBase.getUserList() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

	public static void main( String[] args ){
		
		instance.get();
		
		System.out.println( "完成" );
	}
	
}
