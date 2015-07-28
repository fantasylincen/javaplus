package cn.javaplus.common.util;

import java.sql.SQLException;

/**
 * 运行时数据库异常
 */
public class RuntimeSQLException extends RuntimeException {

	private static final long serialVersionUID = 7321177274493326440L;

	public RuntimeSQLException(SQLException e) {
		super(e);
	}
	
}
