package cn.mxz;

import java.sql.Connection;
import java.sql.SQLException;

import cn.javaplus.exception.SQLRuntimeException;

import com.lemon.commons.database.ConnectionFetcher;
import com.lemon.commons.database.DBProperties;
import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * http://my.oschina.net/lyzg/blog/55133
 * @author 林岑
 *
 */
public class DataBasePoolC3P0 implements ConnectionFetcher {

	private ComboPooledDataSource cpds;


	public DataBasePoolC3P0(DBProperties p) {
		cpds = new ComboPooledDataSource();
		cpds.setUser(p.getUser());
		cpds.setJdbcUrl(p.getDataCon());
		cpds.setPassword(p.getPassword());
//		cpds.setTestConnectionOnCheckin(true);
//		cpds.setTestConnectionOnCheckout(true);
		cpds.setIdleConnectionTestPeriod(900);
		cpds.setInitialPoolSize(30);
		cpds.setMaxIdleTime(9);
		cpds.setMaxPoolSize(50);
		cpds.setMinPoolSize(25);
		cpds.setAutoCommitOnClose(true);
	}

	public Connection getConnection() {
		try {
			Connection connection = cpds.getConnection();
			connection.setAutoCommit(false);
			return connection;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

}
