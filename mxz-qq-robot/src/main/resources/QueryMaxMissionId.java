import java.util.Collection;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserCountersAllDao;
import mongo.gen.MongoGen.UserCountersAllDao.UserCountersAllDtoCursor;
import mongo.gen.MongoGen.UserCountersAllDto;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;

public class QueryMaxMissionId {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String getMaxMissionId() {
		Collection<City> all = WorldFactory.getWorld().getAll();
		int max = 0;
		for (City city : all) {
			int id = city.getUserCounterHistory().get(CounterKey.MAX_MISSION_ID);
			if(id > max ) 
				max = id;
//			System.out.println(id);
		}
		return "over " + max;
	}
}