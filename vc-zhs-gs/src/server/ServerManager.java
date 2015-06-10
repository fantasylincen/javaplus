package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import game.activity.ActivityManager;

import login.server.LoginSocket;
import login.server.event.EventList;
import login.server.event.LoginEvent;

import net.GameHandler;

import org.xsocket.connection.ConnectionUtils;
import org.xsocket.connection.INonBlockingConnection;

import config.ConfigMain;

import telnet.ElementManager;
import user.UserInfo;
import user.UserManager;
import util.SystemTimer;
import util.runnable.ThreadManager;
import util.taskscheduling.ServerTaskImpl;
import datalogging.DataLogDataProvider;
import define.GameDataProvider;
import define.SystemCfg;
import deng.xxoo.utils.XOTime;

/**
 * 服务器管理
 * @author DXF
 */
public class ServerManager {

	// 当前服务器状态 1,开启  2,未开启  3,开启中 4,关闭中
	private static byte 		curServerStatus			= 2;
	private static String[]		curServerStatusStr		= {"流畅","维护","开启中","关闭中"};

	// 游戏服务器
	private static GameServer 	gameServer				= null;
	
	// 登陆服务器
	private static LoginSocket 	loginServer				= null;
	
	
	public static LoginSocket getLoginSocket(){
		return loginServer;
	}
	public static INonBlockingConnection getLoginCon() {
		return loginServer != null ? loginServer.getCon() : null;
	}
	
	/**
	 * 连接登陆服务器
	 * @throws Exception
	 */
	public static void startLoginServer( PrintWriter out ) throws Exception{
		
		if( loginServer != null )
			return;
		
		// 连接登陆服
		try {
			
			loginServer	= new LoginSocket();
			LoginEvent l = (LoginEvent)EventList.LOGIN_VERIFICATION.getEventInstance();
			l.run( loginServer.getCon(), null );
			if( out != null ){
				out.print( "<完成>.\r\n" );
				out.flush();
			}
			
			LoginManager.getInstance().start();
			
		} catch (Exception e) {
			out.print( "<失败>.\r\n" );
			out.flush();
			throw new Exception( "" );
		}
	}
	
	/** 重新连接登陆服 
	 * @throws IOException */
	public static void reLoadLogin() throws IOException {
		
//		if( loginServer == null || loginServer.getCon() == null || !loginServer.isOpen() )
//			loginServer	= new LoginSocket();
		if( loginServer != null )
			loginServer.close();
		loginServer		= null;
		loginServer		= new LoginSocket();
		
		LoginEvent l 	= (LoginEvent)EventList.LOGIN_VERIFICATION.getEventInstance();
		l.run( loginServer.getCon(), null );
	}
	
	/**
	 * 关闭登陆付
	 * @param out
	 * @throws Exception
	 */
	public static void closeLoginServer(  PrintWriter out  ) throws Exception{
		if( loginServer != null ){
			
			LoginManager.getInstance().stop();
			
			loginServer.close();
			loginServer = null;
		}
		
		if( out != null ){
			out.print( "已断开登录服!\r\n" );
			out.flush();
		}
	}
	/**
	 * 关闭登陆付
	 * @param out
	 * @throws Exception
	 */
	public static void closeLoginServer(){
		if( loginServer != null )
			loginServer = null;
	}
	
	/**
	 * 开启服务器
	 * @throws Exception
	 */
	public static void startServer( PrintWriter out ) throws Exception{
		
		if( curServerStatus == 1 || curServerStatus == 3 )
			throw new Exception( "服务器已开启 或者正在开启中!" );
		
		start( out );
	}
	
	/**
	 * 关闭服务器
	 * @throws Exception 
	 */
	public static void stopServer( PrintWriter out ) throws Exception {
		
		if( curServerStatus == 2 || curServerStatus == 4 )
			throw new Exception( "服务器已关闭 或者正在关闭中!" );
		
		stop( out );
		
		out.print( "服务器已关闭!\r\n" );
	}
	

	/**
	 * 重载游戏
	 * @param out  
	 * @throws Exception 
	 */
	public static void reloadworld( PrintWriter out ) throws Exception {
		
		stop( out );
		
		start( out );
	}
	
	/**
	 * 重载系统配置
	 * @param out  
	 * @throws Exception 
	 */
	public static void reloadprOperties( PrintWriter out ) throws Exception {
		
		if( !isOpen() )
			throw new Exception( "服务器尚未开启 不能重载配置!" );
		
		ConfigMain.readAllCfg();
		
		out.print( "重载系统配置  成功!\r\n" );
	}
	
	private static void start(PrintWriter out) throws Exception{
		
		XOTime.beginTimer( );
		
		if( gameServer != null )
			return;
		
		curServerStatus	= 3;
		
		out.print( "正在加载配置文件\t" );
		out.flush();
		ConfigMain.readAllCfg();
		out.print( "<完成>.\r\n" );
		out.flush();
		
		out.print( "正在启动线程      \t" );
		out.flush();
		// 开起时间线程
		SystemTimer.start();
		// 开启 任务调用线程
		new ServerTaskImpl().start();
		ThreadManager.getInstance().start();
		SystemCfg.START_SERVER_T 	= GameDataProvider.getInstance().getML( );
		DataLogDataProvider.getInstance().init();
		out.print( "<完成>.\r\n" );
		out.flush();
		
		out.print( "正在读取玩家数据\t" );
		out.flush();
		UserManager.getInstance().accessDatabase();
		out.print( "<完成>.\r\n" );
		out.flush();
		
		out.print( "正在连接 登录服务器\t" );
		out.flush();
		startLoginServer( out );
		
		out.print( "正在启动 游戏服务器\t" );
		out.flush();
		// 开启服务器监听
		gameServer 	= new GameServer( null, SystemCfg.PORT, new GameHandler() );
		gameServer.start();
		ConnectionUtils.registerMBean( gameServer );
		out.print( "<完成>.\r\n" );
		out.flush();
	
		out.print( "本次服务器启动时间：" + XOTime.endTimer( )/1000f + "秒." + "   读取人数：" + UserManager.getInstance().getMemoryAllUser().size() + "\r\n" );
		curServerStatus	= 1;
	}
	
	private static void stop( PrintWriter out ) throws Exception{
		
		closeLoginServer( out );
		
		if( gameServer == null )
			return;
		
		curServerStatus	= 4;
		
		ThreadManager.getInstance().stop();

		List<UserInfo> list = UserManager.getInstance().getOnline();

		// 关闭服务器
		gameServer.close() ;
		gameServer	= null;
		
		// 保存所有玩家数据
		out.print( "正在保存玩家数据..." + list.size() + "\r\n" );
		out.flush();
		UserManager.getInstance().updataAll( list );
		
		ActivityManager.getInstance().update();
		curServerStatus	= 2;
	}
	
	public static boolean isOpen(){
		return curServerStatus == 1;
	}
	
	public static boolean isClose(){
		return curServerStatus == 2;
	}

	public static String getServerStatus() {
		return curServerStatusStr[curServerStatus - 1];
	}

	/**
	 * 打印命令列表
	 * @param out
	 */
	public static void printCommands( PrintWriter out, byte jurisdiction ) {
		out.print("\r\n");
		out.print("----------MENU----------\r\n");
		List<String> list = ElementManager.getAllContent( jurisdiction );
		for( String s : list )
			out.print( s + "\r\n" );
		out.print("----------END-----------\r\n");
		out.print("\r\n");
		out.flush();
	}

}
