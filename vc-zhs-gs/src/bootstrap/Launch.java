package bootstrap;

import define.SystemCfg;
import recharge.LTVDataProvider;

import org.apache.log4j.PropertyConfigurator;
import org.xsocket.connection.ConnectionUtils;

import passback.server.PassBackHandler;
import passback.server.PassBackServer;
import telnet.TelnetServer;
import util.db.DatabaseUtil;
import game.log.Logs;
import net.GameHandler;

import cn.javaplus.util.MainArgs;
import config.ConfigMain;
import datalogging.DataLogDataProvider;
import define.GameDataProvider;
import server.GameServer;
import server.ServerManager;
import user.UserManager;
import util.SystemTimer;
import util.runnable.ThreadManager;
import util.taskscheduling.ServerTaskImpl;

/**
 * 服务器启动程序
 * 
 * @author DXF
 */
public class Launch {

	public static void main(String[] args) throws Exception {

		MainArgs.set(args);

		// 初始化 日志
		PropertyConfigurator.configureAndWatch("log4jconfig/log4j.properties");
		SystemCfg.init();
		DatabaseUtil.initialize();
		// 启动 控制服务器

		if (!MainArgs.contains("debug")) {
			new TelnetServer().start();
		}

		// /////////////////////////////////////////////////////////////////
		if (MainArgs.contains("debug")) {
			ConfigMain.readAllCfg();
			SystemTimer.start();
			new ServerTaskImpl().start();
			ThreadManager.getInstance().start();
			SystemCfg.START_SERVER_T = GameDataProvider.getInstance().getML();
			DataLogDataProvider.getInstance().init();
			UserManager.getInstance().accessDatabase();
			ServerManager.startLoginServer(null);
			GameServer gameServer = new GameServer(null, SystemCfg.PORT, new GameHandler());
			gameServer.start();
			ConnectionUtils.registerMBean(gameServer);
			Logs.debug("服务器启动成功  服务器ID=" + SystemCfg.GAME_DISTRICT);
		}
		// /////////////////////////////////////////////////////////////////

		// 初始化一下ltv
		LTVDataProvider.getInstance().init();
		
		// 开启 充值回传 服务器
		if (SystemCfg.PLATFORM.equals("91") || 
				SystemCfg.PLATFORM.equals("TB") || 
				SystemCfg.PLATFORM.equals("LB") ||
				SystemCfg.PLATFORM.equals("PP") ){
			
			PassBackServer p_server = new PassBackServer(null, SystemCfg.PORT + 800, new PassBackHandler());
			p_server.start();
			ConnectionUtils.registerMBean(p_server);
		}

	}

}
