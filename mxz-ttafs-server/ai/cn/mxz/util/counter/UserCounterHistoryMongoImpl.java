package cn.mxz.util.counter;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserCountersAllDto;
import cn.mxz.city.City;

/**
 * 历史计数器
 *
 * @author 林岑
 *
 */
public class UserCounterHistoryMongoImpl extends AbstractCounter<UserCountersAllDto> {

	public UserCounterHistoryMongoImpl(City city) {
		super(city);
	}

	@Override
	protected CounterDao<UserCountersAllDto> getDao() {
		return Daos.getUserCountersAllDao();
	}

}
