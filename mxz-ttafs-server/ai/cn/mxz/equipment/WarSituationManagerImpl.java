package cn.mxz.equipment;

import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import db.dao.impl.DaoFactory;
import db.dao.impl.WarSituationDao;
import db.domain.WarSituation;
import db.domain.WarSituationImpl;

class WarSituationManagerImpl implements WarSituationManager {

	@Override
	public int save(WarSituationPro situation) {

		WarSituationDao DAO = DaoFactory.getWarSituationDao();
		int id = DaoFactory.nextWarSituationSituationId();

		WarSituation w = new WarSituationImpl();
		w.setCreateTime((int) (System.currentTimeMillis() / 1000));
		w.setData(situation.toByteArray());
		w.setSituationId(id);
		DAO.add(w);

		return id;
	}

}
