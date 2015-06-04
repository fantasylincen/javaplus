package cn.mxz.equipment.snatch;

import java.util.List;

import cn.mxz.equipment.FightingUser;
import cn.mxz.equipment.SnatchLog;

public interface SnatchManager {

	List<SnatchLog> getLogs();

	void removeAllLogs();

	void addLog(SnatchLog log);

	void updateSnatchList(List<FightingUser> allUser);

	FightingUser getUser(String userId);

}
