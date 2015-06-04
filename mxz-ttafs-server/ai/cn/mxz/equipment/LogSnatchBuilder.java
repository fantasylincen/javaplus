package cn.mxz.equipment;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.protocols.user.equipment.EquipmentP.LogSnatchPro;
import cn.mxz.protocols.user.equipment.EquipmentP.LogSnatchPro.LogsNubPro;
import db.dao.impl.AvoidFighterDao;
import db.dao.impl.DaoFactory;
import db.domain.AvoidFighter;

public class LogSnatchBuilder {

	private int remainTime(City city) {

		AvoidFighterDao DAO = DaoFactory.getAvoidFighterDao();

		AvoidFighter avoidFighter = DAO.get(city.getId());

		int i = 0;

		if (avoidFighter != null) {

			int startTime = (int) avoidFighter.getStartTime();

			int nowTime = (int) System.currentTimeMillis() / 1000;

			if (nowTime - startTime < 3 * 3600) {

				i = 3 * 3600 - (nowTime - startTime);
			}

		} else {

			i = 0;
		}

		return i;
	}

	public LogSnatchPro build(City city, List<SnatchLog> ls) {
		LogSnatchPro.Builder b = LogSnatchPro.newBuilder();
		b.setProtectTime(remainTime(city));
		for (SnatchLog s : ls) {
			if(s.getOther() == null) {
				continue;
			}
			LogsNubPro bb = new LogsNubBuilder().build(city, s);
			b.addLog(bb);
		}
		return b.build();
	}

	public LogSnatchPro buildDev() {
		LogSnatchPro.Builder b = LogSnatchPro.newBuilder();
		b.setProtectTime(0);
		b.addLog(new LogsNubBuilder().buildDev());
		return b.build();
	}
}
