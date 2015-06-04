package cn.javaplus.exception;

import java.sql.SQLException;

public class SQLRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 568500123224257421L;

	public SQLRuntimeException(SQLException e) {
		super(e);
	}

	public SQLRuntimeException(String string) {
		super(string);
	}

}
