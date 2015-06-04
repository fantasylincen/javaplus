package db;

import java.sql.Connection;

import cn.mxz.base.config.Cfg;
import cn.mxz.base.config.ServerConfig;
import cn.mxz.util.debuger.Debuger;
import cn.mxz.util.debuger.SystemLog;

import com.lemon.commons.database.ConnectionFetcher;
import com.lemon.commons.database.DBProperties;
import com.lemon.commons.database.ServerProperties;


/**
 *
 * 游戏数据库
 * @author 	林岑
 * @since	2013年5月28日 13:29:36
 *
 */
public class GameDB implements ConnectionFetcher{

	
	private static GameDB instance;

	private ConnectionFetcher fecher;

	private GameDB() {

		ServerProperties config = ServerConfig.getConfig();

		DBProperties p = new DBProperties();

		String dataBasePath = config.getDataBasePath();
		Debuger.debug(dataBasePath);
		p.setDataCon(dataBasePath);

		p.setDrives(Cfg.DBDriver);

		p.setPassword(config.getDataBasePassword());

		p.setUser(config.getDataBaseUname());

		fecher = new DataBasePoolC3P0(p);

		SystemLog.info("Init GameDB Successful!");
	}

	public static final ConnectionFetcher getInstance() {
		if (instance == null) {
			instance = new GameDB();
		}
		return instance;
	}

	@Override
	public Connection getConnection() {
		Connection connection = fecher.getConnection();
//		Debuger.debug("GameDB.getConnection() 创建连接:" + connection);
//		new Exception().printStackTrace();
		return connection;
	}


}
