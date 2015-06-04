import java.util.Map;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.util.counter.CounterKey;
import db.dao.impl.ChuangZhenDao2;
import db.dao.impl.DaoFactory;
import db.domain.ChuangZhen;

public class ResetChuangZhenTimes {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void run(String user) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}

		ChuangZhenDao2 dao = DaoFactory.getChuangZhenDao();
		ChuangZhen dd = dao.get(city.getId());
		dd.setTimes(0);
		
		dao.update(dd);
		city.freeChuangZhenPlayer();
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