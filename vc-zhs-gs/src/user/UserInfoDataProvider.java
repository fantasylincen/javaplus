package user;


import game.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import define.SystemCfg;

import util.ErrorCode;
import util.SystemTimer;
import util.UtilBase;
import util.db.DatabaseUtil;

/**
 * 和数据库打交道
 * 单体
 * 
 * @author deng 2013-6-20 下午05:53:59
 */
class UserInfoDataProvider {
	private static UserInfoDataProvider instance = new UserInfoDataProvider();
	public static  UserInfoDataProvider getInstance(){
		return instance;
	}
	private UserInfoDataProvider(){ }
	
	/**
	 * 通过用户昵称获取玩家的用户名
	 * @param name
	 * @return
	 */
	int getNameByNickName( String nickName ){
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT name from user_base where nick_name=?";
			pst = con.prepareStatement( sql );
			pst.setString( 1, nickName );
			rs = pst.executeQuery();

			if( rs.next() ) {
				return rs.getInt( "name" );
			}
			else{//数据库无此玩家
				return 0;
			}			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return 0;
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
	}
	
	/**
	 * 从数据库中获取玩家信息
	 * @param user
	 * @return
	 * 		DB_ERROR,USER_NOT_FOUND
	 */
	ErrorCode get( UserInfo user ) {
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from user_base where name=?";
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			rs = pst.executeQuery();

			if( rs.next() ) {
				user.setNickName( rs.getString("nick_name") );
				
				user.setLevel( rs.getShort("level") );
				user.setVipLevel( rs.getByte("vip_level") );
				user.setRechargeGold( rs.getInt("recharge_gold") );
				user.RMB = rs.getFloat("rmb");
				user.setRechargeMoney( rs.getFloat( "recharge_money" ) );
				user.setRechargeMoney1( rs.getFloat( "recharge_money1" ) );
				user.setRechargeMoney2( rs.getFloat( "recharge_money2" ) );
				user.strongestAres = rs.getFloat( "strongestares_recharge" );
				user.setTrophyNumer( rs.getInt("trophy_num") );
				user.addExp( rs.getInt("exp") ); // 这个要在level后面收
				
				user.addCash( rs.getInt( "cash" ) );
				user.addGold( rs.getInt( "gold" ) );
				user.getMonthCardFate()[0] = rs.getShort( "month_card_fate" );
				user.getMonthCardFate()[1] = rs.getShort( "month_card_fate_1" );
				user.getMonthCardFate()[2] = rs.getShort( "month_card_fate_2" );
				user.setRestriction( rs.getString( "restriction" ) );
				user.addFriendshipValue( rs.getInt( "friend_value" ) );
				
				user.setStrengthMax( rs.getShort( "max_strength" ) ); // 这个必须放在cur_strength前面
				user.changeStrength( rs.getShort( "cur_strength" ), "db.get" );
				user.setStrReplyTime( rs.getInt( "strength_record_time" ) );// 这个要在cur_strength后面收
				user.setBuyStrCount( rs.getByte( "buy_str_count" ) );
				user.setBuyStrCountMax( rs.getByte( "buy_str_count_max" ) );
				user.christmasTimes = rs.getByte( "add_buystr_count" );
				user.setPvpMateBuyCount( rs.getByte( "pvp_matebuy_count" ) );
				
				user.setBagCapacity( rs.getShort("bag_capacity" ) );
				user.setEquipbagCapacity( rs.getShort("equip_capacity" ) );
				user.setFriendCapacity( rs.getShort("friend_capacity") );
				
				user.setStatus( UserStatus.fromNum( rs.getByte( "status" ) ) );
				user.setLoginCount( rs.getShort( "login_count" ) );
				user.setDayLoginCount( rs.getInt( "continuous_login_count" ) );
				user.setLastLoginTime( rs.getInt( "lastLogin_time" ) );
				user.setLastLogoutTime( rs.getInt( "lastlogout_time" ) );
				user.setCreateTime( rs.getInt( "create_time" ) );
				user.setAdult( rs.getBoolean( "is_adult" ) ) ;
				user.setNewbieGuideID( rs.getShort( "newbie_guide" ) ) ;
				user.xinkai = rs.getFloat( "xinkai" );
				user.xinkai1 = rs.getFloat( "xinkai1" );
				user.christmasRecharge = rs.getFloat( "xinkai2" );
				user.isTurnover	= rs.getBoolean( "is_turnover" );
				user.isFirsttimeThatcard = rs.getBoolean( "is_firsttimethatcard" );
				user.setIsCardTo10( rs.getByte( "is_cardto10" ) );
				user.setIsHthemeWeek( rs.getByte( "is_hthemeweek" ) );
				user.setAllBuyTimes( rs.getString( "all_buytimes" ) );
			}
			else{//数据库无此玩家
				return ErrorCode.USER_NOT_FOUND;
			}			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return ErrorCode.DB_ERROR; 
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 检测昵称或用户名在游戏中是否已经存在
	 * @param user
	 * @return
	 * 		true:	昵称或者用户名已存在
	 */
	boolean nameIsDuplicate( UserInfo user ){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		String sql 				= "SELECT is_adult from user_base where name=? or nick_name=?";
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( 1, user.getUID() );
			pst.setString( 2, user.getNickName() );
			rs = pst.executeQuery();

			if( rs.next() ) {
				return true;  
			}
			else{//数据库无此玩家
				return false;
			}	
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		return true;
	}
	
	/**
	 * 创建玩家
	 * @param user
	 * @return
	 * 		USER_DUPLICATE_NAME,DB_ERROR
	 */
	ErrorCode create(UserInfo user) {
		
		ErrorCode code = isIllegal( user );
		if( code != ErrorCode.SUCCESS ) return code;
		
		// 设置服务器ID 然后外层不用考虑
		user.setServerID( SystemCfg.GAME_DISTRICT );
		user.setCreateTime( SystemTimer.currentTimeSecond() );
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "insert into user_base(name, nick_name, cash, gold,create_time,is_adult) values" +
										  "(   ?,	     ?,   ?,?,	?,        ?)";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setString( i++, user.getNickName() );
			pst.setInt( i++, user.getCash() );
			pst.setInt( i++, user.getGold() );
			pst.setInt( i++, user.getCreateTime() );
			pst.setBoolean( i++, user.isAdult() );
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
			return ErrorCode.DB_ERROR;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		return ErrorCode.SUCCESS;
	}
	
	public ErrorCode isIllegal( UserInfo user ){
		
		// 检查用户名是否有非法字符
		if( UtilBase.nameIsIllegal( user.getNickName() ) ){
			Logs.error( user, "创建用户出错  用户名含有非法字符  at=" + user.getNickName() );
			return ErrorCode.USER_ILLEGAL_NAME;
		}

		// 先检查 用户名是否重复  
		if( nameIsDuplicate( user ) ){
			Logs.error( user, "创建用户出错  用户名相同  at=" + user.getNickName() );
			return ErrorCode.USER_DUPLICATE_NAME;
		}
		
		return ErrorCode.SUCCESS;
	} 
	
	/**
	 * 修改玩家信息，针对某些改动比较频繁且重要的字段，可考虑专门做一个语法糖，优化的事情放到以后再说
	 * @param user
	 * @return
	 * 		DB_ERROR
	 */
	ErrorCode update(UserInfo user) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "update user_base set " +
				"nick_name=?," +
				"level=?," +
				"vip_level=?," +
				"recharge_gold=?," +
				"rmb=?," +
				"recharge_money=?," +
				"recharge_money1=?," +
				"recharge_money2=?," +
				"strongestares_recharge=?," +
				"exp=?," +
				"cash = ?," +
				"gold = ?," +
				"trophy_num = ?," +
				"month_card_fate = ?," +
				"month_card_fate_1 = ?," +
				"month_card_fate_2 = ?," +
				"restriction = ?," +
				"friend_value = ?," +
				"cur_strength=?," +
				"max_strength=?," +
				"strength_record_time=?," +
				"buy_str_count=?," +
				"buy_str_count_max=?," +
				"add_buystr_count=?," +
				"pvp_matebuy_count=?," +
				"bag_capacity=?," +
				"equip_capacity=?," +
				"friend_capacity=?," +
				"status=?," +
				"login_count=?,"+
				"continuous_login_count=?,"+
				"lastLogin_time=?,"+
				"lastlogout_time=?," +
				"is_adult=?," +
				"newbie_guide=?," +
				"xinkai=?," +
				"xinkai1=?," +
				"xinkai2=?," +
				"is_turnover=?," +
				"is_firsttimethatcard=?," +
				"is_cardto10=?," +
				"is_hthemeweek=?," +
				"all_buytimes=? " +
				"where name=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, user.getNickName() );
			pst.setShort( i++, user.getLevel() );
			pst.setByte( i++, user.getVipLevel() );
			pst.setInt( i++, user.getRechargeGold() );
			pst.setFloat( i++, user.RMB );
			pst.setFloat( i++, user.getRechargeMoney() );
			pst.setFloat( i++, user.getRechargeMoney1() );
			pst.setFloat( i++, user.getRechargeMoney2() );
			pst.setFloat( i++, user.strongestAres );
			pst.setInt( i++, user.getCurExp() );
			
			pst.setInt( i++, user.getCash() );
			pst.setInt( i++, user.getGold() );
			pst.setInt( i++, user.getTrophyNumer() );
			pst.setShort( i++, user.getMonthCardFate()[0] );
			pst.setShort( i++, user.getMonthCardFate()[1] );
			pst.setShort( i++, user.getMonthCardFate()[2] );
			pst.setString( i++, user.getRestrictionToString() );
			pst.setInt( i++, user.getFriendshipValue() );
			
			user.getStrReplyTimeToSecond( 1 );
			pst.setShort( i++, user.getStrength() );
			pst.setShort( i++, user.getStrengthMax() );
			pst.setInt( i++, user.getStrReplyTime() );
			pst.setByte( i++, user.getBuyStrCount() );
			pst.setByte( i++, user.getBuyStrCountMax() );
			pst.setByte( i++, user.christmasTimes );
			pst.setByte( i++, user.getPvpMateBuyCount() );
			
			pst.setShort( i++, user.getBagCapacity() );
			pst.setShort( i++, user.getEquipbagCapacity() );
			pst.setShort( i++, user.getFriendCapacity() );
			
			pst.setByte( i++, user.getStatus().toNum() );
			pst.setShort( i++, user.getLoginCount() );
			pst.setInt( i++, user.getDayLoginCount() );
			pst.setInt( i++, user.getLastLoginTime() );
			pst.setInt( i++, user.getLastLogoutTime() );
			pst.setBoolean( i++, user.isAdult() );
			pst.setShort( i++, user.getNewbieGuideID() );
			pst.setFloat( i++, user.xinkai );
			pst.setFloat( i++, user.xinkai1 );
			pst.setFloat( i++, user.christmasRecharge );
			pst.setBoolean( i++, user.isTurnover );
			pst.setBoolean( i++, user.isFirsttimeThatcard );
			pst.setByte( i++, user.getIsCardTo10() );
			pst.setByte( i++, user.getIsHthemeWeek() );
			pst.setString( i++, user.getAllBuyTimes() );
			
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
	 * 获得整张表
	 * @param onlineUsers
	 */
	public ConcurrentHashMap<Integer, UserInfo> getAll() {
		ConcurrentHashMap<Integer, UserInfo> onlineUsers = new ConcurrentHashMap<Integer, UserInfo>();
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		int curTime				= SystemTimer.currentTimeSecond() - 259200 ; // 7天604800 3天259200
		String sql 				= "SELECT * from user_base where lastlogout_time > ?";
//		String sql 				= "SELECT * from user_base";
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( 1, curTime );
			rs 	= pst.executeQuery();

			while( rs.next() ) {
				
				
				UserInfo user = new UserInfo( null, rs.getInt("name") );
//				synchronized ( user ) {
					
				user.setNickName( rs.getString("nick_name") );
				
				user.setLevel( rs.getShort("level") );
				user.setVipLevel( rs.getByte("vip_level") );
				user.setRechargeGold( rs.getInt("recharge_gold") );
				user.RMB = rs.getFloat( "rmb" );
				user.setRechargeMoney( rs.getFloat( "recharge_money" ) );
				user.setRechargeMoney1( rs.getFloat( "recharge_money1" ) );
				user.setRechargeMoney2( rs.getFloat( "recharge_money2" ) );
				user.strongestAres = rs.getFloat( "strongestares_recharge" );
				user.addExp( rs.getInt("exp") ); // 这个要在level后面收
				
				user.addCash( rs.getInt( "cash" ) );
				user.addGold( rs.getInt( "gold" ) );
				user.setTrophyNumer( rs.getInt( "trophy_num" ) );
				user.getMonthCardFate()[0] = rs.getShort( "month_card_fate" );
				user.getMonthCardFate()[1] = rs.getShort( "month_card_fate_1" );
				user.getMonthCardFate()[2] = rs.getShort( "month_card_fate_2" );
				user.setRestriction( rs.getString( "restriction" ) );
				user.addFriendshipValue( rs.getInt( "friend_value" ) );
				
				user.setStrengthMax( rs.getShort( "max_strength" ) ); // 这个必须放在cur_strength前面
				user.changeStrength( rs.getShort( "cur_strength" ), "db.get" );
				user.setStrReplyTime( rs.getInt( "strength_record_time" ) );// 这个要在cur_strength后面收
				user.setBuyStrCount( rs.getByte( "buy_str_count" ) );
				user.setBuyStrCountMax( rs.getByte( "buy_str_count_max" ) );
				user.christmasTimes = rs.getByte( "add_buystr_count" );
				user.setPvpMateBuyCount( rs.getByte( "pvp_matebuy_count" ) );
				
				user.setBagCapacity( rs.getShort("bag_capacity" ) );
				user.setEquipbagCapacity( rs.getShort("equip_capacity" ) );
				user.setFriendCapacity( rs.getShort("friend_capacity") );
				
				user.setStatus( UserStatus.fromNum( rs.getByte( "status" ) ) );
				user.setLoginCount( rs.getShort( "login_count" ) );
				user.setDayLoginCount( rs.getInt( "continuous_login_count" ) );
				user.setLastLoginTime( rs.getInt( "lastLogin_time" ) );
				user.setLastLogoutTime( rs.getInt( "lastlogout_time" ) );
				user.setCreateTime( rs.getInt( "create_time" ) );
				user.setAdult( rs.getBoolean( "is_adult" ) ) ;
				user.setNewbieGuideID( rs.getShort( "newbie_guide" ) ) ;
				user.xinkai = rs.getFloat( "xinkai" );
				user.xinkai1 = rs.getFloat( "xinkai1" );
				user.christmasRecharge = rs.getFloat( "xinkai2" );
				user.isTurnover	= rs.getBoolean( "is_turnover" );
				user.isFirsttimeThatcard = rs.getBoolean( "is_firsttimethatcard" );
				user.setIsCardTo10( rs.getByte( "is_cardto10" ) );
				user.setIsHthemeWeek( rs.getByte( "is_hthemeweek" ) );
				user.setAllBuyTimes( rs.getString( "all_buytimes" ) );
				
				// 获取 团队信息
				user.getTeamManager();
				
				// 获取 装备信息
				user.getEquipmentManager();
				
				// 获取 英雄数据
				user.getHeroManager();
				
				// 获取 好友数据
//				user.getFriendManager();
				
				// 获取 副本信息
				user.getEctypeManager();
				
				// 获得 精英副本信息
				user.getEliteEctypeCountManager();
				
				// 初始 所有唯一ID基础值
				user.getBasisUniqueID().initDataUniqueID();
				
				onlineUsers.putIfAbsent( user.getUID(), user );
//				}
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		
		return onlineUsers;
	}
	
	public void updataToIsCL( UserInfo user ) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "update user_base set " +
				"continuous_login_count=? "+
				"where name=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getDayLoginCount() );
			
			pst.setInt( i++, user.getUID() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public void remove( UserInfo user ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		
		List<String> sql		= new ArrayList<String>();
		
		sql.add( "delete from user_base where name = ?" );
		sql.add( "delete from user_hero_base where uname = ?" );
		sql.add( "delete from team_base where uname = ?" );
		sql.add( "delete from talent_info where uname = ?" );
		sql.add( "delete from pvp_mate_info where uname = ?" );
		sql.add( "delete from inviting_friend_info where uname = ?" );
		sql.add( "delete from friend_info where uname = ?" );
		sql.add( "delete from fight_reconnect where uname = ?" );
		sql.add( "delete from esynthesis_info where uname = ?" );
		sql.add( "delete from equip_info where uname = ?" );
		sql.add( "delete from elite_ecytpe_info where uname = ?" );
		sql.add( "delete from ecytpe_base where uname = ?" );
		sql.add( "delete from captain_skill_info where uname = ?" );
		sql.add( "delete from award_info where uname = ?" );
		
		try {
			
			while( !sql.isEmpty() ){
				
				pst = con.prepareStatement( sql.remove(0) );
				
				pst.setInt( 1, user.getUID() );
				
				pst.executeUpdate();
			}
			
		} catch (SQLException e) {
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public List<UserInfo> getList( int uid ) {
		
		List<UserInfo> ls		= new ArrayList<UserInfo>();
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		
//		String temp 			= "";
		int temp = 0;
		if( SystemCfg.COMBINE_DISTRICT != null ){
//			for( int i = 0; i < SystemCfg.COMBINE_DISTRICT.length; i++ ){
////				temp			+= 
//			}
			temp = uid + 1000000000;
		}
		
		String sql 				= "SELECT name,nick_name,level from user_base where name=" + uid + ( temp == 0 ? "" : (" or name=" + temp) ) ;
		try {
			pst = con.prepareStatement( sql );
			rs 	= pst.executeQuery();

			while( rs.next() ) {
				
				UserInfo user = new UserInfo( null, rs.getInt("name") );
				user.setNickName( rs.getString("nick_name") );
				user.setLevel( rs.getShort("level") );
				
				ls.add( user );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		return ls;
	}
	
	public static void main( String[] args ){
		
//		System.out.println( UtilBase.nameIsIllegal( "阿斯s23" ) );
		
		instance.remove( new UserInfo( null, 1011996 ) );
		
		System.out.println( "完成" );
	}
	



}
