package cn.mxz;

import java.sql.Date;

import cn.mxz.log.MXZLogger.LogData;

public class LogDataTest implements LogData {

	@Override
	public Date getTime() {
		return new Date(System.currentTimeMillis());
	}

	@Override
	public int getServerId() {
		return 1;
	}

	@Override
	public String getHead() {
		return "xxx";
	}

	@Override
	public String getLog() {
		return "xxx";
	}

}
