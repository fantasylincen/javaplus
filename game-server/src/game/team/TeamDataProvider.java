package game.team;

import game.fighter.Hero;
import game.growup.Colour;
import game.growup.Quality;
import game.log.Logs;
import game.pvp.MatchingType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.fighter.HeroTemplet;
import config.fighter.HeroTempletCfg;

import user.UserInfo;
import util.ErrorCode;
import util.db.DatabaseUtil;

/**
 *  团队管理数据库  团队不是很重要  所以在玩家退出游戏的时候存
 * @author DXF
 *
 */
public class TeamDataProvider {

	private static TeamDataProvider instance 	= new TeamDataProvider();
	static  TeamDataProvider getInstance(){
		return instance;
	}
	
	
	public Object[] get( UserInfo user ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		Object[] obj 			= new Object[7];
		obj[0]					= new ArrayList<TeamHero>();
		obj[1]					= null;
		
		obj[2]					= new ArrayList<TeamHero>();
		obj[3]					= new ArrayList<TeamHero>();
		obj[4]					= new ArrayList<TeamHero>();
		obj[5]					= MatchingType.GREEN_CARD;
		obj[6]					= new ArrayList<TeamHero>();
		
		String sql = "SELECT * from team_base where uname=?";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs = pst.executeQuery();

			if( rs.next() ){
				
				obj[0]			= mapping( rs.getString( "content" ) );
				obj[1]	 		= analysis( rs.getString( "assist_friends" ) );
				obj[2]			= mapping( rs.getString( "mate_content_green" ) );
				obj[3]			= mapping( rs.getString( "mate_content_blue" ) );
				obj[4]			= mapping( rs.getString( "mate_content_purple" ) );
				obj[5]			= MatchingType.fromNumber( rs.getByte( "mate_last_type" ) );
				obj[6]			= mapping( rs.getString( "qualifying_content" ) );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return obj;
	}

	private AssistBase analysis( String content ) {
		
		if( content.isEmpty() ) return null;
		
		String[] data 			= content.split(",");
//		if( data.length != 11 ) return null;
		
		int userUid 			= Integer.parseInt( data[0] );
		byte pos 				= Byte.parseByte( data[1] );
		if( pos == -1 ) 		return null;
		boolean isdie 			= Byte.parseByte( data[2] ) == 1;
		boolean isAbsoluteDie 	= Byte.parseByte( data[3] ) == 1;
		
		// 英雄数据
		int uid					= Integer.parseInt( data[4] );
		int templetId 			= Integer.parseInt( data[5] );
		short lv				= Short.parseShort( data[6] );
		Colour curQuality		= Colour.valueOf( data[7] );
		
		HeroTemplet templet 	= HeroTempletCfg.getById( templetId );
		Quality quality			= new Quality(curQuality, templet.qualityMax, Byte.parseByte( data[8] ) );
		
		byte accordLv			= Byte.parseByte( data[9] );
		int captainId			= Integer.parseInt( data[10] );
		int expert				= 0;
		if( data.length >= 12 )
			expert				= Integer.parseInt( data[11] );
		
		Hero hero 				= new Hero( templet, uid, lv, quality );
		hero.getSkillAttack().setLevel( accordLv );
		hero.getCaptainSkill().setSkill( captainId );
		
		AssistBase teamHero = new AssistBase( uid, pos, isdie );
		teamHero.IsAbsoluteDie( isAbsoluteDie );
		teamHero.setUserUID( userUid );
		teamHero.setCaptainHero( hero );
		teamHero.setExpert( expert );
		
		return teamHero;
	}


	// 获得的时候 解析
	private List<TeamHero> mapping( String content ) {
		
		List<TeamHero> teamList = new ArrayList<TeamHero>();
		
		if( content.isEmpty() ) return teamList;
		
		String[] team = content.split("\\|");
		
		for( int i = 0; i < team.length; i++ ){
			
			if( team[i].isEmpty() ) continue;
			
			String[] data 			= team[i].split(",");
			
			if( data.length != 4 ) continue;
			
			int uid 				= Integer.parseInt( data[0] );
			byte pos 				= Byte.parseByte( data[1] );
			boolean isdie 			= Byte.parseByte( data[2] ) == 1;
			boolean isAbsoluteDie 	= Byte.parseByte( data[3] ) == 1;
			
			TeamHero teamHero = new TeamHero( uid, pos, isdie );
			teamHero.IsAbsoluteDie( isAbsoluteDie );
			
			teamList.add( teamHero );
		}
		
		return teamList;
	}

	/**
	 * 刷新数据
	 * @param user
	 * @param teamHeroList
	 * @param assist  
	 * @param mate 
	 * @return
	 */
	public ErrorCode updata(UserInfo user, String teamHeroList, String assist, String[] mate, String qualifying, MatchingType type ) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "update team_base set " +
				"content = ?, " +
				"assist_friends = ?, " +
				"mate_content_green = ?, " +
				"mate_content_blue = ?, " +
				"mate_content_purple = ?, " +
				"qualifying_content = ?, " +
				"mate_last_type = ? " +
				"where uname=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, teamHeroList );
			pst.setString( i++, assist );
			pst.setString( i++, mate[0] );
			pst.setString( i++, mate[1] );
			pst.setString( i++, mate[2] );
			pst.setString( i++, qualifying );
			pst.setByte( i++, type.toNumber() );
			
			pst.setInt( i++, user.getUID() );
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return ErrorCode.DB_ERROR;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		return ErrorCode.SUCCESS;
	}

	/**
	 * 添加
	 * @param user
	 * @param teamHeroList
	 */
	public ErrorCode add(UserInfo user, String teamHeroList ) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql 	= "insert into team_base(uname,content) values(?,?)";
		int	i	 	= 1;
		try {
			pst 	= con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, teamHeroList );
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return ErrorCode.DB_ERROR;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		return ErrorCode.SUCCESS;
	}
	
	
}
