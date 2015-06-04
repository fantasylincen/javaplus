package cn.mxz.log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import cn.javaplus.exception.SQLRuntimeException;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.log.MXZLogger.LogData;
import cn.mxz.util.debuger.SystemLog;

import com.lemon.commons.database.ConnectionFetcher;

public class LogTableDAO {

	private ConnectionFetcher fetcher;

	private static boolean hasEnsureLogTableExist;
	
	public LogTableDAO(ConnectionFetcher fetcher) {
		this.fetcher = fetcher;
		ensureLogTableExist();
	}

	private void ensureLogTableExist() {
		if(!hasEnsureLogTableExist) {
			ensureLogTables();
			hasEnsureLogTableExist = true;
		}
	}

	private void ensureLogTables() {
		for (int i = 0; i < 12; i++) {
			String content = Util.File.getContent("res/createlogtable.sql");
			String tableName = getTableName(i);
			content = content.replaceAll("TABLE_NAME", tableName);
			excute(content);
			SystemLog.debug("LogTableDAO.ensureLogTables() 确保日志表存在:" + tableName);
		}
	}

	private static String getTableName(int monthAdd) {
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) + monthAdd + 1;
		while(m > 12) {
			m -= 12;
			y += 1;
		}
		return "log" + y + parse(m);
	}

	private static String parse(int m) {
		if(m < 10) {
			return "0" + m;
		}
		return "" + m;
	}

	private void excute(String sql) {

		PreparedStatement ps = null;
		Connection connection = null;

		try {

			connection = fetcher.getConnection();
			ps = connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			Closer.close(ps);
			Closer.close(connection);
		}		
	}

	public void addAll(List<LogData> ls) {

		if (ls.isEmpty()) {
			return;
		}

		PreparedStatement ps = null;
		Connection connection = null;

		try {
			String tableName = getTableName(0);
			String sql = "INSERT INTO " + tableName + " (log_time, server_id, log_head, log_text) VALUES (?, ?, ?, ?)";
			connection = fetcher.getConnection();

			ps = connection.prepareStatement(sql);
			for (LogData udo : ls) {

				String head = udo.getHead();
				String log = udo.getLog();

				head = Util.Str.ensureMaxLen(head, 32);
				log = Util.Str.ensureMaxLen(log, 1024);

				Date time = udo.getTime();
				Timestamp tt = new Timestamp(time.getTime());
				ps.setTimestamp(1, tt);
				ps.setInt(2, udo.getServerId());
				ps.setString(3, head);
				ps.setString(4, log);

				ps.addBatch();

			}
			ps.executeBatch();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} catch (Throwable e) {
			System.err.println("日志写入失败" + e.getClass().getName() + ":" + e.getMessage());
		} finally {
			Closer.close(ps);
			Closer.close(connection);
		}

	}

}
