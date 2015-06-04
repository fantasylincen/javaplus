package game.growup.captain;

import game.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.UserInfo;
import util.db.DatabaseUtil;

/**
 * 记录当前重置队长技能
 * @author DXF
 *
 */
public class CaptainSkillDataProvider {

	private static CaptainSkillDataProvider instance 	= new CaptainSkillDataProvider();
	static  CaptainSkillDataProvider getInstance(){
		return instance;
	}
	
	
	public CaptaninSkillBase get(UserInfo user) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from captain_skill_info where name=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			rs = pst.executeQuery();

			if( rs.next() ) {
				
				CaptaninSkillBase skillBase = new CaptaninSkillBase();
				
				skillBase.setNID( rs.getInt("skill_id") );
				skillBase.setRecordTime( rs.getInt( "record_time" ) );
				skillBase.setUseTime( rs.getByte( "use_time" ) );
				
				return skillBase;
			}
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return null;
	}


	public void updata( UserInfo user, CaptaninSkillBase captanin ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update captain_skill_info set " +
				"skill_id = ?, " +
				"use_time = ?, " +
				"record_time = ? " +
				"where name = ? ";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, captanin.getNID() );
			pst.setByte( i++, captanin.getUseTime() );
			pst.setInt( i++, captanin.getRecordTime() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}


	public void add( UserInfo user, CaptaninSkillBase captanin ) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "insert into captain_skill_info (name,record_time) "
			+ "values (?,?)";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, captanin.getRecordTime() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
	}

	
}
