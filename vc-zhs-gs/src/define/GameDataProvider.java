package define;

import game.log.Logs;

import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import telnet.events.SetActivityEevet;
import user.UserManager;
import util.db.DatabaseUtil;


/**
 * 游戏的一些数据
 * @author DXF
 */
public class GameDataProvider {
	
	private static GameDataProvider instance 	= new GameDataProvider();
	public static  GameDataProvider getInstance(){
		return instance;
	}
	private GameDataProvider(){
	}
	
	public void create(){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		String sql 				= "insert into game_data (id) values (1)";
		try {
			pst = con.prepareStatement( sql );
			pst.executeUpdate();
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public ByteBuffer[] getFA() {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		ResultSet res			= null;
		
		String sql 				= "SELECT front_animation,front_animation1 from game_data";
		
		try {
			pst = con.prepareStatement( sql );
			res = pst.executeQuery();
			
			if( res.next() ){
				ByteBuffer[] xx = new ByteBuffer[2];
				byte[] data 	= res.getBytes( "front_animation" );
				byte[] data1 	= res.getBytes( "front_animation1" );
				xx[0] = data == null ? null : ByteBuffer.wrap( data );
				xx[1] = data1 == null ? null : ByteBuffer.wrap( data1 );
				return xx;
			}else{
				create();
			}
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return null;
	}
	
	public void addFA( ByteBuffer[] data ) {
		
		data[0].flip();
		data[1].flip();
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		String sql 				= "update game_data set front_animation=?, front_animation1=?";
		
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			
			pst.setBytes( i++, data[0].array() );
			pst.setBytes( i++, data[1].array() );
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public long getML(  ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		ResultSet res			= null;
		
		String sql 				= "SELECT * from game_data";
		
		try {
			pst = con.prepareStatement( sql );
			res = pst.executeQuery();
			
			if( res.next() ){
				
				String content 		= res.getString( "mikka_login" );
				initRanking( res.getString( "ranking" ) );
				SetActivityEevet.initActivit( res.getString( "activit_data" ) );
				
				if( content == null || content.isEmpty() ){
					return 0;
				}
				
				String[] list 			= content.split(",");
				
				Calendar currentDate 	= Calendar.getInstance();
				currentDate.set( Calendar.YEAR, Integer.parseInt( list[0] ) );
				currentDate.set( Calendar.MONTH, Integer.parseInt( list[1] ) - 1 );
				currentDate.set( Calendar.DAY_OF_MONTH, Integer.parseInt( list[2] ) );
				currentDate.set( Calendar.HOUR_OF_DAY, Integer.parseInt( list[3] ) );
				currentDate.set( Calendar.MINUTE, Integer.parseInt( list[4] ) );
				currentDate.set( Calendar.SECOND, Integer.parseInt( list[5] ) );
				
				return currentDate.getTimeInMillis();
			
			}else{
				create();
			}
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
		
		return 0;
	}
	
	
	public void addML( String time ){
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		String sql 				= "update game_data set mikka_login=?";
		
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, time );
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public void updateRanking() {
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		String sql 				= "update game_data set ranking=?";
		
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, bianyi() );
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}

	private String bianyi() {
		StringBuilder content = new StringBuilder();
		for( int i : GameData.ranking )
			content.append( i ).append( "," );
		return content.toString();
	}
	private void initRanking( String string ) {
		GameData.ranking.clear();
		if ( string.isEmpty() ) return;
		String conten[] = string.split(",");
		for( int i = 0; i < conten.length && i < 1000; i++ ){
			int uID = Integer.parseInt( conten[i] );
			if( UserManager.getInstance().getByName(uID) == null )
				continue;
			GameData.ranking.add( uID );
		}
	}
	
	public void updateActivit( String dataToString ) {
		
		Connection con 			= DatabaseUtil.getConnection();
		PreparedStatement pst 	= null;			
		
		String sql 				= "update game_data set activit_data=?";
		
		int i = 1;
		try {
			pst = con.prepareStatement( sql );
			pst.setString( i++, dataToString );
			pst.executeUpdate();
			
		} catch (SQLException e) {
			Logs.error( e.getLocalizedMessage(), e );
		} finally {
			DatabaseUtil.close( null, pst, con );
		}
	}
	
	public static void main( String[] args ){
		
		//创建这个表
		instance.create();
		
		// 添加开服时间 这个一定要是整点的 
//		instance.addML( "2014,4,2,0,0,0" );
		
//		instance.getML();
		
		System.out.println( "完成!" );
	}

	
	
}
