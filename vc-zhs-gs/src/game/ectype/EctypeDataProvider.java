package game.ectype;

import game.log.Logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import config.mission.EctypeType;

import user.UserInfo;
import util.ErrorCode;
import util.db.DatabaseUtil;

/**
 * 副本系统的相关数据库方法
 * 单列
 * 
 * @author DXF
 */
public class EctypeDataProvider {
	
	private static EctypeDataProvider instance = new EctypeDataProvider();
	static  EctypeDataProvider getInstance(){
		return instance;
	}
	
	private EctypeDataProvider(){
	}
	
	
	/**
	 * 获得当前玩家的副本信息  不管主线还是支线 都是最后一个ID（目前只有主线ID）
	 * @param name
	 * @return
	 */
	public ConcurrentHashMap<EctypeType, EctypeBase> get( UserInfo user )
	{
		ConcurrentHashMap<EctypeType, EctypeBase> ecytpes = new ConcurrentHashMap<EctypeType, EctypeBase>();
		
		// 获取数据库连接
		Connection con = DatabaseUtil.getConnection();
		
		PreparedStatement pst 	= null;
		ResultSet rs 			= null;
		
		int i = 1;
		try {
			String sql = "SELECT * from ecytpe_base where uname=?";
			
			pst = con.prepareStatement( sql );
			pst.setInt( i++, user.getUID() );
			rs = pst.executeQuery();
			
			if( rs.next() ) {
				
				EctypeBase value = new EctypeBase();
				value.setFeederList( rs.getString( "common" ) );
				ecytpes.putIfAbsent( EctypeType.COMMON, value );
				
				value = new EctypeBase();
				value.setFeederList( rs.getString( "elite" ) );
				ecytpes.putIfAbsent( EctypeType.ELITE, value );
				
				value = new EctypeBase();
				value.setFeederList( rs.getString( "challenge" ) );
				ecytpes.putIfAbsent( EctypeType.CHALLENGE, value );
				
				value = new EctypeBase();
				value.setFeederList( rs.getString( "activity" ) );
				ecytpes.putIfAbsent( EctypeType.ACTIVITY, value );
			}
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		}
		finally{			
			DatabaseUtil.close( rs, pst, con );
		}
		return ecytpes;
	}
	
	/**
	 *  添加一个副本记录在数据库 （目前只有主线）
	 * @param ectypes
	 * @param uname
	 * @return
	 */
	ErrorCode add( ConcurrentHashMap<EctypeType, EctypeBase> ectypes, UserInfo user ) 
	{
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;								  
		String sql = "insert into ecytpe_base (uname,common,elite,challenge,activity) "
			+ "values (?,?,?,?,?)";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setInt( i++, user.getUID() );
			
			pst.setString( i++, ectypes.get( EctypeType.COMMON ).getFeederList() );
			
			pst.setString( i++, ectypes.get( EctypeType.ELITE ).getFeederList() );
			
			pst.setString( i++, ectypes.get( EctypeType.CHALLENGE ).getFeederList() );
			
			pst.setString( i++, ectypes.get( EctypeType.ACTIVITY ).getFeederList() );
			
			pst.executeUpdate();
		} catch (SQLException e) {
			ectypes.clear();
			Logs.error( e.getLocalizedMessage(), e );
			return ErrorCode.DB_ERROR;
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		
		return ErrorCode.SUCCESS;
	}
	
	/**
	 * 修改玩家的副本信息
	 * @param ectypeType 
	 * @param ecytpe
	 * @param uname
	 * @return
	 */
	ErrorCode update( EctypeType ectypeType, EctypeBase ecytpe, UserInfo user ) {
		
		Connection con = DatabaseUtil.getConnection();
		PreparedStatement pst = null;	
		
		String sql = "update ecytpe_base set " + 
				ectypeType.getDBTypeToStr() + " = ? " +
				"where uname = ?";
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setString( i++, ecytpe.getFeederList() );
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
