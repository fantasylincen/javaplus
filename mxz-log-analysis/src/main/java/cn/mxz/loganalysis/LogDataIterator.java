package cn.mxz.loganalysis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import cn.javaplus.exception.UnImplMethodException;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.define.ConfigProperties;

public class LogDataIterator implements Iterator<LogData> {

	private static final String TABLE_NAME = ConfigProperties.getString("TABLE_NAME");
	private PreparedStatement ps;
	private ResultSet rs;
	private Connection connection;
	private boolean hasNext;

	public LogDataIterator() {
		connection = LogDB.getInstance().getConnection();
		try {
			ps = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE `log_head` LIKE '%Service%' ORDER BY log_time;");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}
	
	public LogDataIterator(String timeStart, String timeEnd) {
		connection = LogDB.getInstance().getConnection();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		Date end = null;
		try {
			start = sp.parse(timeStart);
			end = sp.parse(timeEnd);
		} catch (ParseException e1) {
			throw new RuntimeException(e1);
		}
		try {
			ps = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE log_time > ? AND log_time < ? AND log_head = 'Service';");
			ps.setDate(1, new java.sql.Date(start.getTime()));
			ps.setDate(2, new java.sql.Date(end.getTime()));
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}
	
	public boolean hasNext() {
		try {
			hasNext = rs.next();
		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		}
		return hasNext;
	}

	public LogData next() {
		return new LogDataImpl(rs);
	}

	public void remove() {
		throw new UnImplMethodException();
	}
	
	public void close() {
		Closer.close(rs, ps, connection);
	}

}
