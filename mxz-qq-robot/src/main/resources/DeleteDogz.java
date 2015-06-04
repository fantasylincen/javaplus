import java.util.List;
import java.util.Map;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import db.dao.impl.DaoFactory;
import db.dao.impl.UserDogzDao2;
import db.domain.UserDogz;

public class DeleteDogz {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void deleteDogz(String user) {
		City city = getCity(user);
		if (city == null) {
			throw new RuntimeException("玩家不存在" + user);
		}

		UserDogzDao2 dao = DaoFactory.getUserDogzDao();
		List<UserDogz> all = dao.findByUname(city.getId());
		
		for (UserDogz dto : all) {
			dao.delete(dto);
		}
		
		city.reloadDogzManager();
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