package game.pvp;

import game.fighter.Hero;
import game.growup.Colour;
import game.growup.Quality;
import game.log.Logs;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.fighter.HeroTemplet;
import config.fighter.HeroTempletCfg;

import user.UserInfo;
import util.db.DatabaseUtil;

public class DanGradingProvider {

	private static DanGradingProvider instance 	= new DanGradingProvider();
	static  DanGradingProvider getInstance(){
		return instance;
	}
	private DanGradingProvider(){}
	
	
	public DanGradingBase get( UserInfo user ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		try {
			String sql = "SELECT * from pvp_mate_info where uname=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			
			rs 	= pst.executeQuery();

			if( rs.next() ) {
				
				DanGradingBase base = new DanGradingBase();
				base.danGrad( DanGrad.fromNumber( rs.getByte( "dan_grad" ) ) );
				base.grade( rs.getInt( "grade" ) );
				base.standings( rs.getInt( "standings" ) );
				base.maxVictory( rs.getShort( "max_victory" ) );
				base.recordVictory( rs.getShort( "record_victory" ) ) ;
				base.maxFailure( rs.getShort( "max_failure" ) );
				base.recordFailure( rs.getShort( "record_failure" ) ) ;
				base.todayCount( rs.getByte( "today_count" ) );
				base.buyCount( rs.getByte( "buy_count" ) );
				base.mateCount( rs.getInt( "mate_count" ) );
				base.setAward( rs.getString( "award" ) );
				base.isGetWelfare( rs.getByte( "is_getwelfare" ) == 1 );
				base.byLootCount( rs.getByte( "by_loot_count" ) );
				base.recordTime( rs.getInt( "record_time" ) );
				
				return base;
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return null;
	}
	
	
	public void updata( UserInfo user, DanGradingBase danGradingInfo ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql = "update pvp_mate_info set " +
				"dan_grad = ?," +
				"grade = ?," +
				"standings = ?," +
				"max_victory = ?," +
				"record_victory = ?," +
				"max_failure = ?," +
				"record_failure = ?," +
				"today_count = ?," +
				"buy_count = ?," +
				"mate_count = ?," +
				"award = ?," +
				"is_getwelfare = ?," +
				"by_loot_count = ?," +
				"record_time = ? " +
				"where uname=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setByte( i++, danGradingInfo.danGrad().toNumber() );
			pst.setInt( i++, danGradingInfo.grade() );
			pst.setInt( i++, danGradingInfo.standings() );
			pst.setShort( i++, danGradingInfo.maxVictory() );
			pst.setShort( i++, danGradingInfo.recordVictory() );
			pst.setShort( i++, danGradingInfo.maxFailure() );
			pst.setShort( i++, danGradingInfo.recordFailure() );
			pst.setByte( i++, danGradingInfo.todayCount() );
			pst.setByte( i++, danGradingInfo.buyCount() );
			pst.setInt( i++, danGradingInfo.mateCount() );
			pst.setString( i++, danGradingInfo.getAward() );
			pst.setByte( i++, (byte) (danGradingInfo.isGetWelfare() ? 1 : 0) );
			pst.setByte( i++, danGradingInfo.byLootCount() );
			pst.setInt( i++, danGradingInfo.recordTime() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	
	public void add( UserInfo user, DanGradingBase danGradingInfo ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql 				= "insert into pvp_mate_info(uname, dan_grad, grade, today_count, buy_count, record_time) values" +
										  "(?,?,?,?,?,?)";
		int	i = 1;
		try {
			pst 	= con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			pst.setByte( i++, danGradingInfo.danGrad().toNumber() );
			pst.setInt( i++, danGradingInfo.grade() );
			pst.setByte( i++, danGradingInfo.todayCount() );
			pst.setByte( i++, danGradingInfo.buyCount() );
			pst.setInt( i++, danGradingInfo.recordTime() );
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	/**
	 * 获得录像记录
	 * @param user
	 * @return
	 */
	public VideoRecording getV( UserInfo user ) {
		
		VideoRecording v		= new VideoRecording();
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		try {
			String sql = "SELECT * from pvp_mate_battle_info where uname=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			
			rs 	= pst.executeQuery();

			while( rs.next() ) {
				
				int id				= rs.getInt( "uId" );
				int touserID		= rs.getInt( "to_uname" );
				ByteBuffer data 	= ByteBuffer.wrap( rs.getBytes( "data" ) );
				int	t				= rs.getInt( "time" );
				VideoBase base 		= new VideoBase( id, touserID, data );
				base.setTime( t );
				base.mLootCash		= rs.getInt( "loot_cash" );
				base.mIsWin			= rs.getByte( "is_win" ) == 1 ;
				base.mIsRevenge		= rs.getByte( "revenge" ) == 1;
				base.mIsComplex		= rs.getByte( "is_revenge" ) == 1;
				
				List<Hero> list		= new ArrayList<Hero>();
				for( int j = 1; j < 6; j++ ){
					Hero h			= mapingToHero( rs.getString( "to_hero_" + j ) );
					if( h != null)
						list.add(h);
				}
				base.setAList( list );
				
				list.clear();
				for( int j = 1; j < 6; j++ ){
					Hero h			= mapingToHero( rs.getString( "mi_hero_" + j ) );
					if( h != null)
						list.add(h);
				}
				base.setDList( list );
				
				v.put( base );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return v;
	}
	
	
	public void updateV( UserInfo user, VideoBase base ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;								  
		String sql = "update pvp_mate_battle_info set " +
				"to_uname = ?," +
				"to_hero_1 = ?," +
				"to_hero_2 = ?," +
				"to_hero_3 = ?," +
				"to_hero_4 = ?," +
				"to_hero_5 = ?," +
				"mi_hero_1 = ?," +
				"mi_hero_2 = ?," +
				"mi_hero_3 = ?," +
				"mi_hero_4 = ?," +
				"mi_hero_5 = ?," +
				"data = ?," +
				"loot_cash = ?," +
				"is_win = ?," +
				"revenge = ?," +
				"is_revenge = ?," +
				"time = ? " +
				"where uId=? and uname=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, base.mTouserID );
			
			for( int j = 0; j < 5; j++ ){
				if( j < base.mALists.size() ){
					pst.setString( i++, maping( base.mALists.get(j) ) );
				}else{
					pst.setString( i++, "" );
				}
			}
			for( int j = 0; j < 5; j++ ){
				if( j < base.mDLists.size() ){
					pst.setString( i++, maping( base.mDLists.get(j) ) );
				}else{
					pst.setString( i++, "" );
				}
			}
			
			pst.setBytes( i++, base.mData.array() );
			pst.setInt( i++, base.mLootCash );
			pst.setByte( i++, (byte) (base.mIsWin ? 1 : 0) );
			pst.setByte( i++, (byte) (base.mIsRevenge ? 1 : 0) );
			pst.setByte( i++, (byte) (base.mIsComplex ? 1 : 0) );
			pst.setInt( i++, base.mTime );
			
			pst.setInt( i++, base.mID );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public int addV( UserInfo user, PvpMateInfo a_winInfo, PvpMateInfo d_winInfo, ByteBuffer data,
			int t, int lootCash, boolean isWin, boolean isRevenge, boolean isOfRevenge ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		String sql = "insert into pvp_mate_battle_info (uId,uname,to_uname,to_hero_1,to_hero_2,to_hero_3,to_hero_4,to_hero_5," +
				"mi_hero_1,mi_hero_2,mi_hero_3,mi_hero_4,mi_hero_5,data,loot_cash,is_win,revenge,is_revenge,time) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			int uid = user.getBasisUniqueID().MATEINFO_ID();
			pst.setInt( i++, uid );
			
			pst.setInt( i++, user.getUID() );
			
			pst.setInt( i++, a_winInfo.getUser().getUID() );
			
			for( int j = 0; j < 5; j++ ){
				if( j < a_winInfo.getList().size() ){
					pst.setString( i++, maping( a_winInfo.getList().get(j) ) );
				}else{
					pst.setString( i++, "" );
				}
			}
			for( int j = 0; j < 5; j++ ){
				if( j < d_winInfo.getList().size() ){
					pst.setString( i++, maping( d_winInfo.getList().get(j) ) );
				}else{
					pst.setString( i++, "" );
				}
			}
			pst.setBytes( i++, data.array() );
			pst.setInt( i++, lootCash );
			pst.setByte( i++, (byte) (isWin ? 1 : 0) );
			pst.setByte( i++, (byte) (isRevenge ? 1 : 0) );
			pst.setByte( i++, (byte) (isOfRevenge ? 1 : 0) );
			pst.setInt( i++, t );
			
			pst.executeUpdate();
			
			return uid;
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		Logs.error( "数据库错误  在  添加战报信息 " );
		return -1;
	}
	
	// 解析
	private String maping( Hero hero ) {
		
		StringBuilder content = new StringBuilder();
		
		content.append( hero.getUID() ).append(",");
		content.append( hero.getNid() ).append(",");
		content.append( hero.getLevel() ).append(",");
		content.append( hero.getQuality().toContent() ).append(",");
		content.append( hero.getPosition() ).append(",");
		content.append( hero.getIsCaptain() ).append(",");
		content.append( hero.getSkillAttack().getLevel() ).append(",");
		content.append( hero.getCaptainSkill().getID() ).append(",");
		content.append( hero.getHpMax() );
		
		return content.toString();
	}
	
	// 解析
	private Hero mapingToHero( String content ) {
		
		if( content == null || content.isEmpty() )
			return null;
		
		String[] list = content.split(",");

		if( list.length != 10 ) return null;
		
		int uid 			= Integer.parseInt( list[0] );
		int nid 			= Integer.parseInt( list[1] );
		short lv			= Short.parseShort( list[2] );
		Colour curQuality	= Colour.valueOf( list[3] );
		HeroTemplet templet = HeroTempletCfg.getById( nid );
		Quality quality		= new Quality(curQuality, templet.qualityMax, Byte.parseByte( list[4] ) );
		byte pos			= Byte.parseByte( list[5] );
		boolean	isCap		= Boolean.parseBoolean( list[6] );
		byte skilllv		= Byte.parseByte( list[7] );
		int capId			= Integer.parseInt( list[8] );
		int hpmax			= Integer.parseInt( list[9] );
		
		Hero h				= new Hero( templet, uid, lv, quality );
		h.setHp( hpmax );
		h.setHpMax( hpmax );
		h.setPosition( pos );
		h.setIsCaptain( isCap );
		h.getSkillAttack().setLevel( skilllv );
		h.getCaptainSkill().setSkill( capId );
		
		return h;
	}
	
	public void removeV( UserInfo user, int battlelogID ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String sql = "delete from pvp_mate_battle_info where uname = ? and uId = ?";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, battlelogID );
			
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
}
