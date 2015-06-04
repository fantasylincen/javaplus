package cn.mxz.enemy;

import mongo.gen.MongoGen.EnemyDto;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

class EnemyImpl implements Enemy {

	private EnemyDto	enemy;

	EnemyImpl(EnemyDto enemy) {
		this.enemy = enemy;
	}

	@Override
	public City getCity() {
		return CityFactory.getCity(enemy.getEnemyId());
	}

}
