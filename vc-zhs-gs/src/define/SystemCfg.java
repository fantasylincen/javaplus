package define;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import deng.xxoo.utils.XOTime;

/**
 *  保留系统信息，诸如启动时间，开服时间等一系列参数
 *  变量没法用final
 * @author admin
 *
 */
public class SystemCfg {
	
	private static final String FILE 		= "config/system.xml";

	/** 游戏区id  */
	public static byte  	 	GAME_DISTRICT;
	/** 服务器名字 */
	public static String 		SERVER_NAME;
	/** 服务器IP地址 */
	public static String 		SERVER_ADDRESS;
	/** 端口 */
	public static int  	        PORT;
	/** 合并的区ID */
	public static byte[]		COMBINE_DISTRICT = null;
	
	/**
	 * 系统版本
	 */
	public static String	    VERSION		= "1.0.1";
	
	/**
	 * 属于平台 LOCAL,本地 APPLE,苹果 91,91
	 */
	public static String		PLATFORM	= "LOCAL";
	
	/**
	 * 是否DEBUG
	 */
	public static boolean	IS_DEBUG		= true;
	
	/**
	 * 系统本次启动时间
	 */
	public static long 	START_MILS;
	public static long	START_SERVER_T;
	
	
	
	
	
	/**+
	 * 连接登陆服 IP
	 */
	public static String	    LOGIN_ADDRESS;
	
	/**
	 * 连接登陆服 端口
	 */
	public static int	    	LOGIN_PORT;

	/**
	 * 世界服 IP
	 */
	public static String		WORLD_ADDRESS;
	
	/**
	 * 配置表路径
	 */
	public static String		FILE_NAME;
	
	/**
	 * 日志路径
	 */
	public static String		LOG_PATH;

	
	public static void init(){
		try {
			SAXBuilder builder 	= new SAXBuilder();
			Document document	= builder.build( FILE );
			Element root 		= document.getRootElement();
			
			START_MILS		= XOTime.currentTimeMillis();
			IS_DEBUG		= Boolean.parseBoolean( root.getChildText( "is_debug" ) );
			FILE_NAME		= root.getChildText( "file_name" );
			GAME_DISTRICT 	= Byte.parseByte( root.getChildText( "game_district" ) );
			PORT 			= Integer.parseInt( root.getChildText( "port" ) );
			LOGIN_ADDRESS 	= root.getChildText( "login_address" );
			LOGIN_PORT 		= Integer.parseInt( root.getChildText( "login_port" ) );
			WORLD_ADDRESS	= root.getChildText( "world_address" );
			LOG_PATH		= root.getChildText( "log_path" );
			PLATFORM		= root.getChildText( "platform" );
			SERVER_NAME		= root.getChildText( "server_name" );
			SERVER_ADDRESS	= root.getChildText( "server_address" );
			String content 	= root.getChildText( "combine_district" );
			if( !content.isEmpty() ){
				String arr[] 		= content.split( "," );
				COMBINE_DISTRICT 	= new byte[arr.length];
				for( int i = 0; i < arr.length; i++ ){
					COMBINE_DISTRICT[i] = Byte.parseByte( arr[i] );
				}
			}
			
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}	

}
