package cn.javaplus.database;

import java.sql.PreparedStatement;

import cn.javaplus.exception.RuntimeSQLException;

/**
 * PreparedStatement 获取工具
 * @author 	林岑
 * @since	2013年4月29日 21:35:06
 */
public interface PreparedStatementFetcher {

	/**
	 * 取得PreparedStatement.
	 *
	 * @param 		sql	语句
	 * @return		PreparedStatement
	 * @exception 	RuntimeSQLException	如果sql有语法错误等,会抛出运行时SQL异常
	 */
	PreparedStatement getPst(String sql);
}
