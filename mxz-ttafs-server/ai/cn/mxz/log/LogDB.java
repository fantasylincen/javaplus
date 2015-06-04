package cn.mxz.log;

import java.sql.Connection;

import cn.mxz.base.config.Cfg;
import cn.mxz.base.config.ServerConfig;

import com.lemon.commons.database.ConnectionFetcher;
import com.lemon.commons.database.DBProperties;
import com.lemon.commons.database.ServerProperties;

import db.DataBasePoolC3P0;


/**
 *
 * 游戏日志数据库
 * @author 	林岑
 *
 */
public class LogDB implements ConnectionFetcher{

	private static LogDB instance;

	private ConnectionFetcher fecher;

	private LogDB() {

		ServerProperties config = ServerConfig.getConfig();

		DBProperties p = new DBProperties();

		p.setDataCon(config.getLogDataBasePath());

		p.setDrives(Cfg.DBDriver);

		p.setPassword(config.getLogDataBasePassword());

		p.setUser(config.getLogDataBaseUname());

		System.out.println("日志数据库地址:" + config.getLogDataBasePath());
		System.out.println("日志数据库用户:" + config.getLogDataBaseUname());
		System.out.println("日志数据库密码:" + config.getLogDataBasePassword());

		fecher = new DataBasePoolC3P0(p);
	}

	public static final ConnectionFetcher getInstance() {
		if (instance == null) {
			instance = new LogDB();
		}
		return instance;
	}

	@Override
	public Connection getConnection() {
		return fecher.getConnection();
	}


}
