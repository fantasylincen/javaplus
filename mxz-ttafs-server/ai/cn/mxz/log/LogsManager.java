package cn.mxz.log;

import java.util.List;

public interface LogsManager {

	/**
	 * 获得所有日志记录
	 * @param logType	日志类型
	 * @return
	 */
	List<Log> getAll(LogType logType);

	/**
	 * 记录日志
	 * @param t
	 * @param log
	 */
	void log(LogType t, String log);

	/**
	 * 当前时间
	 * @return
	 */
	String getTimeNow();

	/**
	 * 删除所有日志
	 * @param type
	 */
	void removeAll(LogType type);

	/**
	 * 删除某条日志记录
	 * @param pvp
	 * @param logId
	 */
	void delete(LogType pvp, int logId);

}
