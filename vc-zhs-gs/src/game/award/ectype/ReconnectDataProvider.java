package game.award.ectype;


import game.award.AwardType;
import game.award.AwardInfo;
import game.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.UserInfo;
import util.ErrorCode;
import util.db.DatabaseUtil;

/**
 * 只为记录 玩家断线重连数据
 * 单体
 * 
 * @author DXF 2013-7-1 下午05:53:59
 */
class ReconnectDataProvider {
	private static ReconnectDataProvider 	instance = new ReconnectDataProvider();
	
	static  ReconnectDataProvider getInstance(){
		return instance;
	}
	
	private ReconnectDataProvider(){
	}
	
	
	/**
	 * 获取 玩家副本奖励信息
	 * @param user
	 * @param is_remove  
	 * @return
	 */
	public EctypeAward get(UserInfo user, byte is_remove ) {

		EctypeAward ectypeAward = null;
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		
		String sql 		= "SELECT * from fight_reconnect where uname=? and is_remove=?";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			pst.setByte( i++, is_remove );
			rs = pst.executeQuery();

			if( rs.next() ){
				ectypeAward = mapping( rs );
			}
//			while( rs.next() ){
//				EctypeAward ea = mapping( rs );
//				remove( user, ea );
//			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return ectypeAward;
	}
	
	private EctypeAward mapping( ResultSet rs ) throws SQLException {
		int fid			= rs.getInt( "fight_id" );
		int pId 		= rs.getInt( "points_id" );
		byte the 		= rs.getByte( "the_lv" );
		String	content	= rs.getString( "award_content" );
		byte iswin		= rs.getByte( "is_win" );
		int fireBoss	= rs.getInt( "fire_boss_id" );
		
		List<AwardInfo> list = parsingList( content );
		
		EctypeAward e	= new EctypeAward( pId, the, list, fid, iswin == 1 );
		e.setFireBoss( fireBoss );
		
		return e;
	}

	private List<AwardInfo> parsingList( String msg )
	{
		List<AwardInfo> list = new ArrayList<AwardInfo>();
		
		if( msg.isEmpty() ) return list;
		
		String[] content = msg.split("\\|"); 
		
		for( String s : content ){
			String[] award = s.split(",");
			AwardType awa 	= AwardType.fromNumber( Integer.parseInt( award[0] ) );
			int id				= Integer.parseInt( award[1] );
			short num			= Short.parseShort( award[2] );
			list.add( new AwardInfo( awa, id, num ) );
		}
		
		return list;
	}
	
	/**
	 * 修改 副本奖励记录
	 * @param user
	 * @param temp
	 * @return
	 */
	public ErrorCode upData(UserInfo user, EctypeAward temp) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update fight_reconnect set " +
				"fight_id = ?, " +
				"points_id = ?, " +
				"the_lv = ?, " +
				"award_content = ?, " +
				"is_win = ?, " +
				"fire_boss_id = ?, " +
				"is_remove = ? " +
				"where uname = ?";
		
		// 0表示没删除
		byte is_remove 	= 0;
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, temp.getFightId() );
			pst.setInt( i++, temp.getId() );
			pst.setByte( i++, temp.getTheLv() );
			pst.setString( i++, temp.getContentStr() );
			pst.setByte( i++, (byte)(temp.getIsWin() ? 1 : 0) );
			pst.setInt( i++, temp.getFireBoss() );
			pst.setByte( i++, is_remove );
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
	 * 添加一个 副本 奖励记录
	 * @param user
	 * @param award
	 * @return
	 */
	public ErrorCode add(UserInfo user, EctypeAward award) 
	{
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;					
		
		String sql = "insert into fight_reconnect (uname,fight_id,points_id,the_lv,fire_boss_id,award_content,is_win) "
			+ "values (?,?,?,?,?,?,?)";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			
			pst.setInt( i++, award.getFightId() );
			
			pst.setInt( i++, award.getId() );

			pst.setByte( i++, award.getTheLv() );
			
			pst.setInt( i++, award.getFireBoss() );
			
			pst.setString( i++, award.getContentStr() );
			
			pst.setByte( i++, (byte)(award.getIsWin() ? 1 : 0) );
			
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
	 * 删除一个
	 * @param user
	 * @param award
	 * @return
	 */
	public ErrorCode remove( UserInfo user, EctypeAward award ) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;	
		String sql = "update fight_reconnect set " +
				"award_content = '', " +
				"is_remove = ? " +
				"where uname = ?";
		byte is_remove 	= 1;
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setByte( i++, is_remove );
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
	
}
