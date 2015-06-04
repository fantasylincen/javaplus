package cn.javaplus.database;

import java.sql.Connection;

/**
 * 数据库连接获取器
 * @author 	林岑
 * @since	2012年12月10日 09:56:39
 *
 */
public interface ConnectionFetcher {
	Connection getConnection();
}
