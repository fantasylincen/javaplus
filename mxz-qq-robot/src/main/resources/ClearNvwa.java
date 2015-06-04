import java.util.Map;

import mongo.gen.MongoGen.Daos;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;

public class ClearNvwa {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String clear(String user) {
		City city = getCity(user);
		
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}

		Daos.getNvwaDao().delete(city.getId());
		city.reloadNvwa();

		return "清除成功";
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