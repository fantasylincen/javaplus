package cn.mxz.city;

import cn.mxz.log.Log;
import db.domain.Logs;

class LogImpl implements Log {

	private Logs	logs;

	LogImpl(Logs logs) {
		this.logs = logs;
	}

	@Override
	public int getId() {
		return logs.getLogId();
	}

	@Override
	public String getLog() {
		return logs.getLog();
	}

}
