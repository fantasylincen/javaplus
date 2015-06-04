package cn.mxz.pvp;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.log.Log;
import cn.mxz.log.LogType;
import cn.mxz.log.LogsManager;
import cn.mxz.protocols.user.log.LogsP.LogsPro;

public class LogsBuilder {

	public LogsPro build(City city, LogType type) {

		LogsManager logManager = city.getLogsManager();

		List<Log> logs = logManager.getAll(type);

		return build(logs);
	}

	public LogsPro build(List<Log> all) {
		
		LogsPro.Builder b = LogsPro.newBuilder();

		for (Log log : all) {
			b.addLogs(new LogBuilder().build(log));
		}

		return b.build();
	}

}
