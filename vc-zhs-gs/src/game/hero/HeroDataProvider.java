package game.hero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import game.fighter.Hero;
import game.growup.Colour;
import game.growup.Quality;
import game.log.Logs;

import config.fighter.HeroTemplet;
import config.fighter.HeroTempletCfg;

import user.UserInfo;
import util.SystemTimer;
import util.db.DatabaseUtil;

/**
 * 玩家英雄的相关数据库方法
 * 单列
 * 
 * @author DXF
 */
public class HeroDataProvider {
	
	private static HeroDataProvider instance 	= new HeroDataProvider();
	static  HeroDataProvider getInstance(){
		return instance;
	}
	private HeroDataProvider(){}
	
	/**
	 *  添加一个玩家英雄在数据库  带返回主键值
	 * @param level 
	 * @param quality 
	 * @param pos 
	 * @param ecytpe
	 * @param uname
	 * @return 
	 */
	public int add(  UserInfo user , Hero hero )
	{
		Connection con 	= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		
		String sql = "insert into user_hero_base (u_id,uname,hero_nid,quality,hero_lv,create_time) "
			+ "values (" + hero.getUID() + "," 
						+ user.getUID() + "," + 
						hero.getNid() + ",'" + 
						hero.getQuality().toContent() + "'," + 
						hero.getLevel() + "," + 
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

	/**
	 * 获取玩家所有 英雄数据
	 * @param user
	 * @return
	 */
	public ConcurrentHashMap<Integer, Hero> getAllHero( UserInfo user ) {
		
		ConcurrentHashMap<Integer,Hero> map = new ConcurrentHashMap<Integer, Hero>();
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql = "SELECT * from user_hero_base where uname=? and is_remove=0";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs = pst.executeQuery();

			while( rs.next() ){
				Hero h 				= mapping( rs );
				
				h.settlementPropertyToTalent( user.getTalentManager().getTalents() ); // 这里要结算一下天赋信息
				h.settlementPropertyToDangrad( user.getDanGradingManager().getInfo().danGrad() ); // 这里结算一下段位信息
				
				// 这下面 是装备信息
				String equipContent	= rs.getString( "equip_content" );
				if( !equipContent.isEmpty() ){
					String[] list 	= equipContent.split( "," );
					for( byte x = 0; x < list.length; x++ ){
						int id		= Integer.parseInt( list[x] );
						if( id != -1 )
							h.getEquBar().set( x, user.getEquipmentManager().get(id) );
					}
					h.settlementPropertyToEquip(); // 结算装备
				}
				
				map.putIfAbsent( h.getUID(), h );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return map;
	}
	
	private Hero mapping( ResultSet rs ) throws SQLException {
		
		int uid				= rs.getInt( "u_id" );
		int templetId 		= rs.getInt( "hero_nid" );
		short lv			= rs.getShort( "hero_lv" );
		String[] content	= rs.getString( "quality" ).split(",");
		Colour curQuality	= Colour.valueOf( content[0] );
		
		HeroTemplet templet = HeroTempletCfg.getById( templetId );
		
		Quality quality		= new Quality(curQuality, templet.qualityMax, Byte.parseByte( content[1] ) );
		
		int exp				= rs.getInt( "hero_exp" );
		byte accordLv		= rs.getByte( "accord_skill_lv" );
		int captainId		= rs.getInt( "captain_skill" );
		
		Hero hero 			= new Hero( templet, uid, lv, quality );
		hero.initExp( exp );
		hero.getSkillAttack().setLevel( accordLv );
		hero.getCaptainSkill().setSkill( captainId );
		
		return hero;
	}


	/**
	 * 刷新玩家英雄数据
	 * @param user
	 * @param h
	 */
	public void upData(UserInfo user, Hero h) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update user_hero_base set " +
				"hero_lv = ?, " +
				"quality = ?, " +
				"hero_exp = ?, " +
				"accord_skill_lv = ?, " +
				"captain_skill = ?, " +
				"equip_content = ? " +
				"where u_id = ? and uname = ? and is_remove = 0";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setShort( i++, h.getLevel() );
			pst.setString( i++, h.getQuality().toContent() );
			pst.setInt( i++, h.getExp() );
			pst.setByte( i++, h.getSkillAttack().getLevel() );
			pst.setInt( i++, h.getCaptainSkill().getID() );
			pst.setString( i++, h.getEquBar().toContent() );
			pst.setInt( i++, h.getUID() );
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

	
	/**
	 * 删除
	 * @param user
	 * @param h
	 */
	public boolean remove(UserInfo user, Hero h) {
//		Connection con 			= DatabaseUtil.getConnection();
//		PreparedStatement pst 	= null;	
//		String sql = "update user_hero_base set " +
//				"is_remove = 1, " +
//				"remove_time = ? " +
//				"where u_id = ? and is_remove = 0";
//		int	i = 1;
//		try {
//			pst = con.prepareStatement( sql );
//			
//			pst.setInt( i++, SystemTimer.currentTimeSecond() );
//			pst.setInt( i++, h.getUID() );
//			
//			pst.executeUpdate();
//		} catch (SQLException e) {
//			logger.debug( e.getLocalizedMessage(), e );
//			return false;
//		} finally {
//			DatabaseUtil.close( null, pst, con );
//		}
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		String sql = "delete from user_hero_base where uname = ? and u_id = ?";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			pst.setInt( i++, h.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return false;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return true;
	}	
	
	public static void main( String[] args ){
		
		
	}
}
