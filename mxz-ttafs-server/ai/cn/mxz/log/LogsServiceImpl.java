package cn.mxz.log;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.LogSnatchDao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.LogsService;
import cn.mxz.protocols.user.log.LogsP.LogsAll;

@Component("logsService")
@Scope("prototype")

public class LogsServiceImpl extends AbstractService implements LogsService {

	@Override
	public void clear() {
		LogsManager lm = getCity().getLogsManager();
		lm.removeAll(LogType.PVP);

		getCity().getFriendManager().refuseAll();

		getCity().getEquipmentManager().removeAllSnatchLog();
	}

	@Override
	public LogsAll getAll() {
//		return new LogsAllBuilder().build(getCity());
		return null;
	}

	@Override
	public void remove(int type, int logId) {

		if(type == 1) {
			LogsManager logManager = getCity().getLogsManager();
			logManager.delete(LogType.PVP, logId);
		} else if (type == 2) {
			LogSnatchDao DAO = Daos.getLogSnatchDao();
			DAO.delete(logId);
		}
	}


}
