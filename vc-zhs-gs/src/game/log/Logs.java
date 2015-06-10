package game.log;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import define.SystemCfg;

import user.UserInfo;

/**
 * 输出专用类
 * @author deng
 *
 */
public class Logs {

	private static final Logger	logger 	= Logger.getRootLogger();
	
	/** 信息 日志 */
	public static void info( Object log ) { }
	public static void log( L type, String ... args ) { 
		//logger.info( getContent( type.getId(), args ) ); 
	}
	
	/**
	 * 错误 日志*/
	public static void error( Object log ) { logger.error( "[ systen ]: "+log ); }
	public static void error( Object log, Exception e ) { logger.error( "[ systen ]: "+log, e ); }
	public static void error( UserInfo user, Object log ) { logger.error( buildPrefixStr( user ) + log ); }
	public static void error( UserInfo user, Object log, Exception e ) { logger.error( buildPrefixStr( user ) + log , e ); }
	
	/** debug输出 */
	public static void debug( Object log ) { if( SystemCfg.IS_DEBUG ) logger.debug( "[ systen ]: "+log ); }
	public static void debug( UserInfo user, Object log ) { if( SystemCfg.IS_DEBUG ) logger.debug( buildPrefixStr( user ) + log ); }
	
	/** lua输出 */
	public static void out( Object log ) { if( SystemCfg.IS_DEBUG ) logger.debug( "[ systen.lua ]: "+log ); }
	
	/** 针对此类，提供一个统一的提示信息前缀 */
	private static String buildPrefixStr( UserInfo user ){
		if( user == null ) return "[ null ]: ";
		return "[ " + user.getNickName() + " ]: ";
	}
	
	@SuppressWarnings("unused")
	private static String getContent( String id, String[] args  ){
		
		String content = SystemCfg.GAME_DISTRICT + "," + id;
		
		if( args == null || args.length == 0 ) return content;
		
		for( String s : args )
			content += "," + s;
		
		return content;
	}
	
	public static void main(String[] args){
	
//		long startTime = System.nanoTime();
//		for( int i = 0; i < 100000; i ++ )
//		{
//			log( LogType.LOG_1, "asdsadsa" + i );
//			error("asdasdsadas" + i );
//		}
//		System.out.println( "用时 ："  + ( System.nanoTime() - startTime ) / 1000000000f );
//		
//		logger.debug( "asdasdsadsa" );
//		logger.error( "asdasdsadsa" );
//		logger.trace( "asdasdasdsa" );
		
		PropertyConfigurator.configureAndWatch( "log4jconfig/log4j.properties" );
		
		long startTime = System.nanoTime();
		for( int i = 0; i < 10; i ++ )
		{
			error( "asdsadsa" + i );
		}
		debug( "用时 ："  + ( System.nanoTime() - startTime ) / 1000000000f );
		info( "adsdsadsa" );
		log( L.L_000,":asdsadas" );
		UserInfo user = new UserInfo(null, 1231 );
		user.setNickName( "你好" );
		debug( "asdsadsdsa" );
		debug( user, "asdsad" );
	}
	
	
}
