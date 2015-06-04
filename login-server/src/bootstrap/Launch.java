package bootstrap;

import http.RechargeServer;

import java.io.IOException;
import java.net.InetAddress;

import javax.management.JMException;

import org.xsocket.connection.ConnectionUtils;

import server.agency.ServerAgency;
import server.net.GameHandler;
import user.agency.UserAgency;
import user.net.UserHandler;
import util.MainArgs;
import define.SystemCfg;

/**
 * 登陆服
 * @author DXF
 *
 */
public class Launch{


	public static void main(String[] args) throws IOException, JMException {
		
//		CLog.log( "系统-编码集 = "+System.getProperty("file.encoding") );
//		CLog.log( "默认-编码集 = "+Charset.defaultCharset() + "\n" );
		
		// 游戏服务器 监听
		InetAddress address 		= null;
		ServerAgency serverAgency 	= new ServerAgency( address, SystemCfg.GAME_PORT, new GameHandler() );
		serverAgency.readAllCfg();
		serverAgency.start();		
		ConnectionUtils.registerMBean( serverAgency );
//		CLog.log( " GameServer Listening Start...\n" );
		
		// 用户 监听
		UserAgency userAgency 	= new UserAgency( address, SystemCfg.USER_PORT, new UserHandler() );
		userAgency.readAllCfg();
		userAgency.start();
		ConnectionUtils.registerMBean( userAgency );
//		CLog.log( " User Listening Start...\n" );
		
		new RechargeServer(SystemCfg.RECHARGE_PORT).start();
		MainArgs.set(args);
	}

}
