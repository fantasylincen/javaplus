package cn.mxz.enemy;

import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.EnemyDao;
import mongo.gen.MongoGen.EnemyDao.EnemyDtoCursor;
import mongo.gen.MongoGen.EnemyDto;
import cn.mxz.city.City;

import com.google.common.collect.Lists;


public class EnemyManagerImpl implements EnemyManager {

	private City	city;

	public EnemyManagerImpl(City city) {
		this.city = city;
	}

	@Override
	public void add(String userId) {

		if(isEnemy(userId)) {
			return;
		}

		EnemyDto e = new EnemyDto();

		e.setEnemyId(userId);
		e.setUname(city.getId());

		Daos.getEnemyDao().save(e);
	}

	public boolean isEnemy(String userId) {
		EnemyDao DAO = Daos.getEnemyDao();
		return DAO.get(city.getId(), userId) != null;
	}

	@Override
	public void remove(String userId) {
		EnemyDao DAO = Daos.getEnemyDao();
		DAO.delete(userId, city.getId());
	}

	@Override
	public List<Enemy> getAll() {
		EnemyDao DAO = Daos.getEnemyDao();
		EnemyDtoCursor all = DAO.findByUname(city.getId());

		List<Enemy> ls = Lists.newArrayList();
		for (EnemyDto enemy : all) {
			ls.add(new EnemyImpl(enemy));
		}
		return ls;
	}

}
