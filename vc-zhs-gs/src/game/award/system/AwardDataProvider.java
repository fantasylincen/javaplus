package game.award.system;


import game.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import config.saward.SAwardType;

import user.UserInfo;
import util.db.DatabaseUtil;

/**
 * 奖励中心 数据库
 * @author DXF
 *
 */
public class AwardDataProvider {
	
	private static AwardDataProvider instance 	= new AwardDataProvider();
	static  AwardDataProvider getInstance(){
		return instance;
	}
	
	private AwardDataProvider(){
	}

	public Map<SAwardType, AwardBase> get( UserInfo user ) {
		
		Map<SAwardType, AwardBase> maps = new HashMap<SAwardType, AwardBase>();
		
		// 获取数据库连接
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		
		int i = 1;
		try {
			String sql = "SELECT * from award_info where uname=?";
			
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs = pst.executeQuery();
			
			if( rs.next() ) {
				
				i 				= 0;
				SAwardType type = null;
				AwardBase value = null;
				
				while( (type = SAwardType.fromNumber( i++ )) != null ){
					value 		= new AwardBase();
					value.setContent( rs.getString( type.toString() ) );
					maps.put( type, value );
				}
			}
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return maps;
	}

	public void add( UserInfo user, Map<SAwardType, AwardBase> nAwards ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 		= "insert into award_info (uname";
		String qm		= "?";
		
		int	i 			= 0;
		SAwardType type = null;
		while( (type = SAwardType.fromNumber( i++ )) != null ){
			sql 	+= "," + type.toString();
			qm		+= ",?";
		}
		sql 			+= ") values (" + qm +")";
		
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( 1, user.getUID() );
			
			i			= 0;
			while( (type = SAwardType.fromNumber( i++ )) != null ){
				
				AwardBase award = nAwards.get(type);
				if( award == null ){
					award = new AwardBase();
					nAwards.put( type, award );
				}
				pst.setString( i + 1, award.getContent() );
			}
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

	public void updata(UserInfo user, Map<SAwardType, AwardBase> nAwards) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String sql 		= "update award_info set ";
		
		int	i 			= 0;
		SAwardType type = null;
		while( (type = SAwardType.fromNumber( i++ )) != null ){
			if( i > 1 ) sql += ",";
			sql 	+= type.toString() + " = ?";
		}
		sql 			+= " where uname = ?";
		
		try {
			pst = con.prepareStatement( sql );
			
			i			= 0;
			while( (type = SAwardType.fromNumber( i++ )) != null ){
				
				AwardBase award = nAwards.get(type);
				if( award == null ){
					award = new AwardBase();
					nAwards.put( type, award );
				}
				pst.setString( i, award.getContent() );
			}
			
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	

}
