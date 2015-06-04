package recharge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import user.UserInfo;
import util.SystemTimer;
import util.UtilBase;
import util.db.DatabaseUtil;


public class LTVDataProvider {
	private static LTVDataProvider instance = new LTVDataProvider();
	public static  LTVDataProvider getInstance(){
		return instance;
	}
	private LTVDataProvider(){
	}
	
	private final String DATE 		= "日期";
	private final String NEW_PLAYER = "新近设备";
	private final String DAY 		= "日";
	private final int MAX_DAY		= 30;
	

	public void addMoney( UserInfo user, float money ) {
		
		// 玩家创建时间
		String createTime = UtilBase.secondsToDate( user.getCreateTime(), "yyyy-MM-dd" );
		// 算出玩家离创建到现在几天了
		int day = createGoNowToDay( user.getCreateTime() );
		if( day > MAX_DAY ) return;
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		String daystr			= day+DAY;
		String sql 				= !isNull( createTime, daystr ) ? ("update ltv set "+daystr+"="+daystr+"+"+money+" where "+DATE+"=?") : 
			("update ltv set "+daystr+"="+money+" where "+DATE+"=?");
		
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, createTime );
			pst.executeUpdate();
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	private boolean isNull(String createTime, String daystr) {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		try {
			String sql = "SELECT "+daystr+" from ltv where "+DATE+"=?";
			pst 	= con.prepareStatement( sql );
			pst.setString( 1, createTime );
			rs 		= pst.executeQuery();
			if( rs.next() ){// 这里先检查 是否有了
				return rs.getFloat( daystr ) == 0;
			}
		} catch (SQLException e) { } finally { DatabaseUtil.close( rs, pst, con ); }
		return true;
	}
	private int createGoNowToDay( int createTime ) {
		long earliestDtae		= SystemTimer.getEarliestDate( createTime*1000l, -1, 24, 0, 0 );
		long apartadTime		= SystemTimer.currentTimeMillis() - earliestDtae;
		// 如果就是在创建的当天 那么算1日噻
		if( apartadTime <= 0 ) return 1;
		// 用经过的时间除以每一日的时间 得出几天  这里还要加上前一日所以直接从第二日开始
		int day	= (int) (apartadTime/86400000) + 2;
		return day;
	}
	
	public void addPlayer( UserInfo user ){
		// 玩家创建时间
		String createTime 		= UtilBase.secondsToDate( user.getCreateTime(), "yyyy-MM-dd" );
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		String sql 				= "update ltv set "+NEW_PLAYER+"="+NEW_PLAYER+"+"+1+" where "+DATE+"=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, createTime );
			pst.executeUpdate();
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	
	public void init(){
		String createTime 		= UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy-MM-dd" );
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		try {
			String sql = "SELECT "+NEW_PLAYER+" from ltv where "+DATE+"=?";
			pst 	= con.prepareStatement( sql );
			pst.setString( 1, createTime );
			rs 		= pst.executeQuery();
			if( !rs.next() ){// 这里先检查 是否有了
				sql = "insert into ltv (" + DATE + ") values (?)";
				pst.close();
				pst = con.prepareStatement( sql );
				pst.setString( 1, createTime );
				pst.executeUpdate();
			}
		} catch (SQLException e) { } finally { DatabaseUtil.close( rs, pst, con ); }
	}
	
	public void init( String createTime ){
//		String createTime 		= UtilBase.secondsToDate( SystemTimer.currentTimeSecond(), "yyyy-MM-dd" );
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		try {
			String sql = "SELECT "+NEW_PLAYER+" from ltv where "+DATE+"=?";
			pst 	= con.prepareStatement( sql );
			pst.setString( 1, createTime );
			rs 		= pst.executeQuery();
			if( !rs.next() ){// 这里先检查 是否有了
				sql = "insert into ltv (" + DATE + ") values (?)";
				pst.close();
				pst = con.prepareStatement( sql );
				pst.setString( 1, createTime );
				pst.executeUpdate();
			}
		} catch (SQLException e) { } finally { DatabaseUtil.close( rs, pst, con ); }
	}
	
	private static List<Data> l = new ArrayList<Data>();
	
	public static void main( String[] args ){
//		UserInfo user = new UserInfo( null, 1212 );
//		user.setCreateTime( SystemTimer.currentTimeSecond() );
//		LTVDataProvider.getInstance().addMoney(user, 9.99f );
//		LTVDataProvider.getInstance().init();
		int conut = 0;
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;		
		ResultSet rs 			= null;
		ResultSet rs1 			= null;
		try {
			String sql = "SELECT * from log_purcharse";
			pst	= con.prepareStatement( sql );
			rs 	= pst.executeQuery();
			while( rs.next() ){
				++conut;
				int UID = rs.getInt( "uname" );
				long cur= getCurTime( rs.getString( "purchase_date" ) );
				sql 	= "SELECT create_time from user_base where name=" + UID;
				pst 	= con.prepareStatement( sql );
				rs1 	= pst.executeQuery();
				if( rs1.next() ){
					int cTime = rs1.getInt( "create_time" );
					String createTime = UtilBase.secondsToDate( cTime, "yyyy-MM-dd" );
					LTVDataProvider.getInstance().init( createTime );
					if( put( createTime, UID ) )
						addPlayer( createTime );
					float moeny = rs.getFloat( "money" );
					addMoney( cTime, moeny, cur );
					System.out.println( "第" + conut + "个  UID=" + UID + "  createTime=" + createTime + "  money=" + moeny );
				}else{
					System.out.println( "第" + conut + "个  UID=" + UID + "  没有找到" );
				}
			}
			
			System.out.println( );
			System.out.println( );
			for( Data d : l )
				System.out.println( d.time + " > " + d.list.size() );
		} catch (SQLException e) { } finally { DatabaseUtil.close( rs, pst, con ); }
	}
	
	private static long getCurTime( String string ) {
		String content = string.substring( 0, 19 );
		Calendar c = Calendar.getInstance();
		try {//2014-09-30 00:47:45 Etc/GMT
			c.setTime( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(content) );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.getTimeInMillis();
	}
	private static void addPlayer(String createTime) {
		// 玩家创建时间
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		String sql 				= "update ltv set 新近设备=新近设备+"+1+" where 日期=?";
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, createTime );
			pst.executeUpdate();
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	private static boolean put(String createTime, int uID) {
		Data x = null;
		for( Data d : l ){
			if( d.time.equals( createTime ) ){
				x = d;
				break;
			}
		}
		if( x != null ){
			if( x.list.indexOf( uID ) == -1 ){
				x.list.add( uID );
				return true;
			}else{ return false; }
		}else{
			x = new Data();
			x.time = createTime;
			x.list.add( uID );
			l.add( x );
			return true;
		}
	}
	public static void addMoney( int cTime, float money, long cur ) {
		// 玩家创建时间
		String createTime = UtilBase.secondsToDate( cTime, "yyyy-MM-dd" );
		// 算出玩家离创建到现在几天了
		long earliestDtae		= SystemTimer.getEarliestDate( cTime*1000l, -1, 24, 0, 0 );
		long apartadTime		= cur - earliestDtae;
		// 用经过的时间除以每一日的时间 得出几天  这里还要加上前一日所以直接从第二日开始
		int day	= apartadTime <= 0 ? 1 : ((int) (apartadTime/86400000) + 2);
		if( day > 30 ) return;
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;
		String daystr			= day+"日";
		String sql 				= !LTVDataProvider.getInstance().isNull( createTime, daystr ) ? ("update ltv set "+daystr+"="+daystr+"+"+money+" where "+"日期"+"=?") : 
			("update ltv set "+daystr+"="+money+" where "+"日期"+"=?");
		int	i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, createTime );
			pst.executeUpdate();
		} catch (SQLException e) { } finally { DatabaseUtil.close( null, pst, con ); }
	}
	
	
}

class Data{
	String time ;
	List<Integer> list = new ArrayList<Integer>();
}
