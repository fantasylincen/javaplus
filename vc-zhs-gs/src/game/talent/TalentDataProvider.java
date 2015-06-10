package game.talent;

import game.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import config.talent.TalentTemplet;
import config.talent.TalentTempletCfg;

import user.UserInfo;
import util.db.DatabaseUtil;

/**
 * 天赋数据库
 * @author DXF
 */
public class TalentDataProvider {
	
	private static TalentDataProvider instance 	= new TalentDataProvider();
	static  TalentDataProvider getInstance(){
		return instance;
	}
	private TalentDataProvider(){}
	
	
	/**
	 * 获取天赋所有属性数据
	 * @param user
	 * @return
	 */
	public ConcurrentHashMap<TalentType, TalentBase> get(UserInfo user) {
		
		ConcurrentHashMap<TalentType, TalentBase> talens = new ConcurrentHashMap<TalentType, TalentBase>();
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql = "SELECT * from talent_info where uname=? ";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs = pst.executeQuery();

			if( rs.next() ){
				
				for( TalentTemplet templet : TalentTempletCfg.getData().values() ){
					
					TalentType type = TalentType.fromNumber( templet.getId() );
					
					String content	= rs.getString( type.getDBStr() );
					
					TalentBase talentBase = new TalentBase( type, (short)1 );
					if( !content.isEmpty() ){
						
						String[] list = content.split(",");
						
						talentBase.setLevel( Short.parseShort( list[0] ) );
						talentBase.setRecordingTime( Integer.parseInt( list[1] ) );
					}
					
					talens.putIfAbsent(type, talentBase);
				}
			}
			
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return talens;
	}
	
	/**
	 * 刷新数据库
	 * @param curTalent
	 */
	public void updata( UserInfo user, TalentBase curTalent ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update talent_info set " +
				curTalent.getType().getDBStr() + "= ? " +
				"where uname = ?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, curTalent.getContent() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	/**
	 * 添加
	 * @param user
	 * @param talents
	 */
	public void add(UserInfo user, ConcurrentHashMap<TalentType, TalentBase> talents) {
		
		StringBuilder name 		= new StringBuilder();
		StringBuilder valuename	= new StringBuilder();
		String[] content		= new String[ talents.values().size() ];
		
		int idx = 0;
		for( TalentBase talent : talents.values() ){
			name.append(",").append( talent.getType().getDBStr() );
			valuename.append(",").append("?");
			content[idx++] = talent.getContent();
		}

		if( name.toString().isEmpty() || valuename.toString().isEmpty() ){
			Logs.error( user, "天赋 添加默认数据 DB出错！" );
			return;
		}
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "insert into talent_info(uname" + name.toString() + ")" +
					" values(?" + valuename.toString() + ")";
		int	i = 1;
		try {;
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			for( idx = 0; idx < content.length; idx++ ){
				pst.setString(i++, content[idx]);
			}
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
	}
	
	
	
}
