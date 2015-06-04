package cn.mxz.newpvp;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.PvpWarSituationDao;
import mongo.gen.MongoGen.PvpWarSituationDto;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import db.dao.impl.DaoFactory;

class PvpWarSituationManagerImpl implements PvpWarSituationManager {

	@Override
	public int save(WarSituationPro situation, String challengerId, String defenderId) {

		PvpWarSituationDao DAO = Daos.getPvpWarSituationDao();
		int id = DaoFactory.nextPvpWarSituationSituationId();

		PvpWarSituationDto w = new PvpWarSituationDto();

		w.setCreateTime((int) (System.currentTimeMillis() / 1000));
		w.setData(situation.toByteArray());
		w.setSituationId(id);
		w.setChallengerId(challengerId);
		w.setDefenderId(defenderId);
		boolean isWin = situation.getIsWin();
		w.setIsWin(isWin);
		DAO.save(w);


		City city = CityFactory.getCity(defenderId);

		if(city != null) {
			UserCounter his = city.getUserCounterHistory();
			his.mark(CounterKey.HAS_NEW_PVP_MESSAGE);
		}


		return id;
	}
}
