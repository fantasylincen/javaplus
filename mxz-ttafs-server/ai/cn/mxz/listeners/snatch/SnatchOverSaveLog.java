package cn.mxz.listeners.snatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.LogSnatchDao;
import mongo.gen.MongoGen.LogSnatchDao.LogSnatchDtoCursor;
import mongo.gen.MongoGen.LogSnatchDto;
import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.equipment.snatch.SnatchManager;
import cn.mxz.equpment.snatch.SnatchLogImpl;
import cn.mxz.events.Listener;
import cn.mxz.events.snatch.SnatchOverEvent;
import cn.mxz.util.debuger.Debuger;
import define.D;

public class SnatchOverSaveLog implements Listener<SnatchOverEvent> {

	private static AtomicInteger maxId;

	@Override
	public void onEvent(SnatchOverEvent e) {

		ensureMaxId();

		boolean win = e.getBattle().isWin();
		Debuger.debug("SnatchOverSaveLog.onEvent() battle is win:" + win);
		if (!win) {
			return;
		}
		
		if(e.isRobot()) {
			return;
		}

		mongo.gen.MongoGen.LogSnatchDao dao = Daos.getLogSnatchDao();

		LogSnatchDto ls = dao.createDTO();

		ls.setId(maxId.addAndGet(1));

		ls.setIswin(win);

		ls.setWarsituationid(e.getWarsituationId());

		ls.setUname(e.getUserId());

		ls.setDatatype(e.getStuffTempletId());

		boolean success = e.isSuccess();
		Debuger.debug("SnatchOverSaveLog.onEvent() is success:" + success);
		ls.setIsSnatchSuccess(success);

		ls.setNub(D.EQUIPMENT_SNATCH_COUNT);
		
//		LogSnatchDto logSnatch = dao.get(DaoFactory.nextLogSnatchId());
//		if (logSnatch != null) {
//			ls.setNub(D.EQUIPMENT_SNATCH_COUNT
//					+ dao.get(DaoFactory.nextLogSnatchId()).getNub());
//		} else {
//		}

		ls.setRobber(e.getRobber().getId());

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String format = df.format((new Date()));

		Date parse = null;

		try {

			parse = df.parse(format);

		} catch (ParseException eee) {

			throw Util.Exception.toRuntimeException(eee);
		}

		ls.setTime((int) (parse.getTime() / 1000));

		City c = CityFactory.getCity(e.getUserId());

		addLog(ls, c);

//		City rb = e.getRobber();
//		addLog(ls, rb);
	}

	private void ensureMaxId() {
		if (maxId != null) {
			return;
		}
		LogSnatchDao dao = Daos.getLogSnatchDao();
		LogSnatchDtoCursor find = dao.find();
		int max = Integer.MIN_VALUE;
		for (LogSnatchDto d : find) {
			int id = d.getId();
			if (id > max) {
				max = id;
			}
		}
		maxId = new AtomicInteger(max);
	}

	private void addLog(LogSnatchDto ls, City c) {
		if (c != null) {
			SnatchManager sm = c.getSnatchManager();
			sm.addLog(new SnatchLogImpl(c, ls));
		}
	}

}
