import java.util.Collection;
import java.util.List;
import java.util.Map;

import mongo.gen.MongoGen.DailyTaskDto;
import cn.mxz.base.task.Task;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserDogzDao2;
import db.domain.UserDogz;

public class FinishAllDailyTask {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void finish(String user) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}

		Collection<Task<DailyTaskDto>> all = city.getDailyTaskManager().getAll();
		for (Task<DailyTaskDto> task : all) {
			task.finishOneTime();
			task.finishOneTime();
		}
		city.freeDailyTaskManager();
	}

	/**
	 * 根据 昵称 或者 id 获取用户
	 */
	private City getCity(String user) {
		City city = CityFactory.getCity(user);
		if (city != null) {
			return city;
		}

		Map<String, String> all = WorldFactory.getWorld().getNickManager()
				.getNickAll();
		String id = all.get(user);
		if (id == null) {
			return null;
		}

		return getCity(id);
	}
}