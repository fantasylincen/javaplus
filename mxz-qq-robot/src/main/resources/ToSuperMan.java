import java.util.Map;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.listeners.TesterAccess;
import cn.mxz.listeners.TesterCreate;

public class ToSuperMan {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void toSuperMan(String user) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}

		new TesterCreate().toTester(city);
		new TesterAccess().guide(city);
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