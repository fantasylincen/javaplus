package cn.mxz.loganalysis;

import java.sql.Connection;

import cn.mxz.DataBasePoolC3P0;
import cn.mxz.define.ConfigProperties;

import com.lemon.commons.database.ConnectionFetcher;
import com.lemon.commons.database.DBProperties;


/**
 *
 * 游戏日志数据库
 * @author 	林岑
 *
 */
public class LogDB implements ConnectionFetcher{
	
//	// 驱动程序名
//    String driver = ConfigProperties.getString("DB_DRIVER"); 
//
//    // URL指向要访问的数据库名scutcs
//    String url = ConfigProperties.getString("DB");
//
//    // MySQL配置时的用户名
//    String user = ConfigProperties.getString("UNAME"); 
//
//    // MySQL配置时的密码
//    String password = ConfigProperties.getString("PWD"); 
    
//	public Connection getConnection() {
//	
//
//         try {
//			// 加载驱动程序
//			 Class.forName(driver); 
//
//			 // 连续数据库
//			 Connection conn = DriverManager.getConnection(url, user, password);
//			 return conn;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException();
//		} 
//
//	}

	private static LogDB instance;

	private ConnectionFetcher fecher;

	private LogDB() {
		DBProperties p = new DBProperties();
		p.setDataCon(ConfigProperties.getString("DB"));
		p.setDrives(ConfigProperties.getString("DB_DRIVER"));
		p.setPassword(ConfigProperties.getString("PWD"));
		p.setUser(ConfigProperties.getString("UNAME"));

		fecher = new DataBasePoolC3P0(p);
		
	}

	public static final ConnectionFetcher getInstance() {
		if (instance == null) {
			instance = new LogDB();
		}
		return instance;
	}

	public Connection getConnection() {
		return fecher.getConnection();
	}
	
	
	
}
