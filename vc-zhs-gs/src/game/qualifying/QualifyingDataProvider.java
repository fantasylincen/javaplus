package game.qualifying;


import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import util.db.DatabaseUtil;

/**
 * 排位赛数据库
 * @author DXF
 *
 */
public class QualifyingDataProvider {
	private static QualifyingDataProvider instance 	= new QualifyingDataProvider();
	static  QualifyingDataProvider getInstance(){
		return instance;
	}
	private QualifyingDataProvider(){}
	public QualifyingBase get( UserInfo user ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		try {
			String sql = "SELECT * from qualifying_info where uId=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			rs = pst.executeQuery();

			if( rs.next() ) {
				int standings = rs.getInt( "standings" );
				byte residue_degree = rs.getByte( "residue_degree" );
				byte frequency = rs.getByte( "frequency" );
				QualifyingBase base = new QualifyingBase();
				base.setStandings(standings);
				base.setResidueDegree(residue_degree);
				base.setFrequency(frequency);
				return base;
			}
		} catch (SQLException e) {
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return null;
	}
	public void add( UserInfo user, QualifyingBase qualifyingBase ) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "insert into qualifying_info( uId, standings, residue_degree, frequency) values" +
										  "( ?,?,	?,        ?)";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, qualifyingBase.getStandings() );
			pst.setByte( i++, qualifyingBase.getResidueDegree() );
			pst.setByte( i++, qualifyingBase.getFrequency() );
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	public void update( UserInfo user, QualifyingBase qualifyingBase ) {
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "update qualifying_info set " +
				"standings=?," +
				"residue_degree=?," +
				"frequency=? " +
				"where uId=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, qualifyingBase.getStandings() );
			pst.setByte( i++, qualifyingBase.getResidueDegree() );
			pst.setByte( i++, qualifyingBase.getFrequency() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	public int addBattlefield( UserInfo user, BattlefieldReport battle ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		String sql = "insert into qualifying_battle_info (u_id,uname,type,data,quilt_uid,rank,timer) "
				+ "values (?,?,?,?,?,?,?)";
		
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, battle.uid );
			pst.setInt( i++, user.getUID() );
			pst.setByte( i++, battle.type );
			pst.setBytes( i++, battle.data.array() );
			pst.setInt( i++, battle.quiltUID );
			pst.setInt( i++, battle.rank );
			pst.setInt( i++, battle.time );
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		return 0;
	}
	public void updateBattlefield( UserInfo user, BattlefieldReport battle ) {
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "update qualifying_battle_info set " +
				"type=?," +
				"data=?," +
				"quilt_uid=?," +
				"rank=?," +
				"timer=?" +
				"where u_id=? and uname=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setByte( i++, battle.type );
			pst.setBytes( i++, battle.data.array() );
			pst.setInt( i++, battle.quiltUID );
			pst.setInt( i++, battle.rank );
			pst.setInt( i++, battle.time );
			pst.setInt( i++, battle.uid );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	public List<BattlefieldReport> getBattlefields( UserInfo user ) {
		List<BattlefieldReport> result = new ArrayList<BattlefieldReport>();
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		try {
			String sql = "SELECT * from qualifying_battle_info where uname=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			rs = pst.executeQuery();

			while( rs.next() ) {
				int uid				= rs.getInt( "u_id" );
				int timer 			= rs.getInt( "timer" );
				byte type 			= rs.getByte( "type" );
				int quilt_uid 		= rs.getInt( "quilt_uid" );
				int rank			= rs.getInt( "rank" );
				ByteBuffer data 	= ByteBuffer.wrap( rs.getBytes( "data" ) );
				BattlefieldReport battle = new BattlefieldReport();
				battle.uid 	= uid;
				battle.type	= type;
				battle.data	= data;
				battle.quiltUID = quilt_uid;
				battle.rank	= rank;
				battle.time	= timer;
				result.add( battle );
			}
		} catch (SQLException e) {
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return result;
	}
	
}
