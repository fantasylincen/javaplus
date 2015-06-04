import java.util.List;
import java.util.Map;

import cn.mxz.bag.BagSnapsort;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserGridDao2;
import db.dao.impl.UserPiecesGridDao2;
import db.domain.UserGrid;
import db.domain.UserPiecesGrid;

public class PassGuide {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public String pass(String user) {
		City city = getCity(user);
		new cn.mxz.listeners.init.PassGuide().pass(city);
		return "SUCCESS";
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