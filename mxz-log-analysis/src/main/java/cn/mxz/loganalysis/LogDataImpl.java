package cn.mxz.loganalysis;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import cn.javaplus.util.Util;

public class LogDataImpl implements LogData {

	private ResultSet rs;
	private String log;
	private String head;
	private int serverId;
	private Timestamp time;
	private int id;

	public LogDataImpl(ResultSet resultSet) {
		this.rs = resultSet;
		mapping();
	}

	private void mapping() {
		try {
			time = rs.getTimestamp("log_time");
			serverId = rs.getInt("server_id");
			head = rs.getString("log_head");
			log = rs.getString("log_text");
			id = rs.getInt("ids");
		} catch (SQLException e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	public Date getTime() {
		
		return new Date(time.getTime());
	}

	public int getServerId() {
		return serverId;
	}

	public String getHead() {
		return head;
	}

	public String getLog() {
		return log;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		return sf.format(time) + "	" + head + "	"
				+ serverId + "	" + log;
	}
}