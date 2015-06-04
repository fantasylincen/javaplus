package cn.mxz.equpment.snatch;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.LogSnatchDao.LogSnatchDtoCursor;
import mongo.gen.MongoGen.LogSnatchDto;
import cn.javaplus.util.Util.Time;
import cn.mxz.city.City;
import cn.mxz.equipment.FightingUser;
import cn.mxz.equipment.SnatchLog;
import cn.mxz.equipment.snatch.SnatchManager;
import cn.mxz.user.Player;

import com.google.common.collect.Lists;

import define.D;

public class SnatchManagerImpl implements SnatchManager {

	private City city;
	private List<SnatchLog> logs;
	private List<FightingUser>	allUser;

	public SnatchManagerImpl(City city) {
		this.city = city;
	}

	@Override
	public List<SnatchLog> getLogs() {
		ensureNotEmpty();
		Comparator<SnatchLog> c = new Comparator<SnatchLog>() {

			@Override
			public int compare(SnatchLog o1, SnatchLog o2) {

				return (int) (o2.getTime().getTime() / 1000 - o1.getTime()
						.getTime() / 1000);
			}
		};

		removeTimeUp();
		Collections.sort(logs, c);
		return logs;
	}

	private void removeTimeUp() {
		Iterator<SnatchLog> it = logs.iterator();
		while (it.hasNext()) {
			SnatchLog next = it.next();
			if(isTimeUp(next)) {
				Daos.getLogSnatchDao().delete(next.getDto());
				it.remove();
			}
		}
	}

	private boolean isTimeUp(SnatchLog next) {
		Date time = next.getTime();
		long t = time.getTime();
		return System.currentTimeMillis() - t > Time.MILES_ONE_HOUR * D.SNATCH_LOG_HOUR;
	}

	private void ensureNotEmpty() {
		if (logs == null) {
			logs = load();
		}
	}

	private List<SnatchLog> load() {

		mongo.gen.MongoGen.LogSnatchDao dao = Daos.getLogSnatchDao();

		LogSnatchDtoCursor findBy = dao.findByUname(getPlayer().getId());
//		LogSnatchDtoCursor findBy2 = dao.findByRobber(getPlayer().getId());
		List<SnatchLog> ls = build(findBy);
//		ls.addAll(build(findBy2));
		return ls;
	}

	private List<SnatchLog> build(LogSnatchDtoCursor findByCursor) {
		List<SnatchLog> ls = Lists.newArrayList();
		for (LogSnatchDto l : findByCursor) {
			ls.add(new SnatchLogImpl(city, l));
		}
		return ls;
	}

	private Player getPlayer() {
		return city.getPlayer();
	}

	@Override
	public void removeAllLogs() {
		Iterator<SnatchLog> it = logs.iterator();
		while (it.hasNext()) {
			SnatchLog snatchLog = (SnatchLog) it.next();
			LogSnatchDto l = snatchLog.getDto();
			mongo.gen.MongoGen.LogSnatchDao DAO = Daos.getLogSnatchDao();
			DAO.delete(l);
			it.remove();
		}
	}

	@Override
	public void addLog(SnatchLog log) {
		ensureNotEmpty();
		mongo.gen.MongoGen.LogSnatchDao DAO = Daos.getLogSnatchDao();
		DAO.save(log.getDto());
		logs.add(log);
	}

	@Override
	public void updateSnatchList(List<FightingUser> allUser) {
//		for (FightingUser f : allUser) {
//			Debuger.debug("SnatchManagerImpl.updateSnatchList()" + f.getId());
//		}
		this.allUser = allUser;
	}

	@Override
	public FightingUser getUser(String userId) {
		for (FightingUser s : this.allUser) {
			String id = s.getId();
			if(userId.equals(id)) {
				return s;
			}
		}
		return null;
	}
}
