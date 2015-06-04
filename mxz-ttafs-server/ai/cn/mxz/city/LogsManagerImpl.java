package cn.mxz.city;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.time.Time;
import cn.mxz.log.Log;
import cn.mxz.log.LogType;
import cn.mxz.log.LogsManager;
import db.dao.impl.DaoFactory;
import db.dao.impl.LogsDao;
import db.domain.Logs;
import db.domain.LogsImpl;

class LogsManagerImpl implements LogsManager {

	private City	city;


	LogsManagerImpl(City city) {

		this.city = city;
	}

	@Override
	public List<Log> getAll(LogType logType) {

		LogsDao DAO = DaoFactory.getLogsDao();

		List<Logs> findBy = DAO.findByUname(city.getId());

		removeOutOfDate(findBy);	//移除过期的

		limit(findBy);				//最多保留20条日志

		filter(findBy, logType);


		List<Log> list = new ArrayList<Log>();

		for (Logs logs : findBy) {

			list.add(new LogImpl(logs));
		}

		return list;
	}

	/**
	 * 截取日志上限, 最多保留20条
	 * @param findBy
	 */
	private void limit(List<Logs> findBy) {

		List<Logs> sub = cn.javaplus.util.Util.Collection.sub(findBy, 20);

		Iterator<Logs> it = findBy.iterator();

		while (it.hasNext()) {

			Logs next = it.next();

			if(!sub.contains(next)) {

				it.remove();

				DaoFactory.getLogsDao().delete(next.getLogId());
			}
		}
	}

	/**
	 * 移除过期日志
	 * @param findBy
	 */
	private void removeOutOfDate(List<Logs> findBy) {

		Iterator<Logs> it = findBy.iterator();

		while (it.hasNext()) {

			Logs next = it.next();

			Date createTime = next.getCreateTime();

			long time = System.currentTimeMillis();

			time -= createTime.getTime();

			if(time > Time.MILES_ONE_DAY * 5) {

				it.remove();

				DaoFactory.getLogsDao().delete(next.getLogId());
			}
		}
	}

	/**
	 * 过滤出所有日志类型为logType的日志
	 * @param findBy
	 * @param logType
	 */
	private void filter(List<Logs> findBy, LogType logType) {

		Iterator<Logs> it = findBy.iterator();

		while (it.hasNext()) {

			Logs next = it.next();

			if(next.getType() != logType.getId()) {

				it.remove();
			}
		}
	}

	@Override
	public void log(LogType t, String log) {

		LogsDao DAO = DaoFactory.getLogsDao();

		Logs l = new LogsImpl();

		l.setCreateTime(new Date());

		l.setLogId(DaoFactory.nextLogsLogId());

		l.setType(t.getId());

		l.setUname(city.getId());

		l.setLog(log);


		DAO.add(l);
	}

	@Override
	public String getTimeNow() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String format = df.format(new Date());
		return format;
	}

	@Override
	public void removeAll(LogType type) {

		LogsDao DAO = DaoFactory.getLogsDao();

		List<Logs> findBy = DAO.findByUname(city.getId());

		for (Logs logs : findBy) {

			DAO.delete(logs.getLogId());
		}
	}

	@Override
	public void delete(LogType pvp, int logId) {

		LogsDao DAO = DaoFactory.getLogsDao();

		DAO.delete(logId);
	}

}
