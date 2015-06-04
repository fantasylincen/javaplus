package cn.mxz.util.counter;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserCountersDto;
import cn.mxz.city.City;

/**
 * 今日计数器
 *
 * @author 林岑
 *
 */
public class UserCounterMongoImpl extends AbstractCounter<UserCountersDto> {

	public UserCounterMongoImpl(City city) {
		super(city);
	}

	@Override
	protected CounterDao<UserCountersDto> getDao() {
		return Daos.getUserCountersDao();
	}
}
