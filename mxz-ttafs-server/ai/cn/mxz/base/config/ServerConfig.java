package cn.mxz.base.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Properties;

import cn.javaplus.time.TimeUpTimer;
import cn.javaplus.util.Util;
import cn.mxz.config.ConfigProperties;
import cn.mxz.util.debuger.Debuger;

import com.lemon.commons.database.ServerProperties;

/**
 * AI服务器配置
 *
 * @author 林岑
 * @time 2013-3-29
 */
public class ServerConfig {

	public static final String CONFIG_SERVER_PROPERTIES = "config/server.properties";

	private static ServerProperties	config;

	private static int						serverId;

	private static long	serverStartMillis;

	private static TimeUpTimer timer = new TimeUpTimer(30000);

	private static boolean isLocalConfig;

	/**
	 * 获取服务器配置
	 *
	 * @return
	 */
	public static ServerProperties getConfig() {

		if(timer.isTimeUp()) {
			try {
				reloadConfig();
			} catch (Exception e) {
				e.printStackTrace();
			}
			timer.restart();
		}
		
		return config;
	}

	/**
	 * 从数据库中从新加载配置
	 */
	private static void reloadConfig() {

		ConfigDAO DAO = isLocalConfig ? new LocalConfigDAOImpl() : new ConfigDAOImpl2();

		final ServerProperties c = DAO.get(serverId);

		if (c != null) {

			config = c;

//			Debuger.debug("ServerConfig.reloadConfig() " + serverId + ":" + config);
			
		} else {

			Debuger.error("未找到该服务器的配置:" + serverId);
		}
	}

	/**
	 * 根据main函数传入的参数, 指定游戏运行方式, 调试模式/正式模式
	 */
	public static void init(String... args) {

		initServerId(args);

//		reloadConfig();

		serverStartMillis = System.currentTimeMillis();
	}

	/**
	 * 运行持续时间
	 * @return
	 */
	public static long getServerRunMillis() {
		return System.currentTimeMillis() - serverStartMillis;
	}

	/**
	 * 服务器启动时间
	 * @return
	 */
	public static long getServerStartMillis() {
		return serverStartMillis;
	}

	/**
	 * 初始化服务器ID
	 *
	 * @param args
	 */
	private static void initServerId(String... args) {

		final Properties prop = new Properties();

		try {

			prop.load(new FileInputStream(new File(ConfigProperties.PATH)));

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

		
		isLocalConfig = contains("-localconfig", args);

		if(!isLocalConfig) {
			serverId = getArg("-serverId:", args);
		} else {
			serverId = Util.Property.getProperties(CONFIG_SERVER_PROPERTIES).getInt("id");
		}

	}

	private static boolean contains(String a, String[] args) {
		for (String arg : args) {

			if (arg.equals(a)) {

				return true;
			}
		}
		return false;
	}

	private static int getArg(String a, String[] args) {

		for (String arg : args) {

			if (arg.startsWith(a)) {

				return new Integer(arg.replaceAll(a, "").trim());
			}
		}

//		昆哥你那个区我改成了: -serverId:559102  -serverId:559104
		throw new RuntimeException("必须指定参数: " + a);
	}

	/**
	 * AI 服务器端口
	 *
	 * @return
	 */
	public static int getGamePort() {
		return getConfig().getPort();
	}

	/**
	 * 服务器ID
	 */
	public static int getServerId() {

		return serverId;
	}

	/**
	 * 开服时间
	 *
	 * @return
	 */

	public static long getServerOpenTime() {

		KeyValueManager manager = new KeyValueManagerImpl();

		String s = manager.get(KeyValueDefine.SERVER_OPEN_TIME);

		return new Long(s);
	}

	/**
	 * 开服所在天数
	 *
	 * @return
	 */
	public static int getServerOpenDay() {
		Calendar is = Calendar.getInstance();
		int off = is.get(Calendar.ZONE_OFFSET);
		long t = getServerOpenTime() + off;
		return (int) (t / Util.Time.MILES_ONE_DAY);
	}
}
