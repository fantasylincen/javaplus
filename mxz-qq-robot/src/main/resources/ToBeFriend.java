import java.util.Map;

import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.friend.FriendCenter;

public class ToBeFriend {

	public String run() {
		return "RUN_FOUNCTION";
	}

	public void run(String user1, String user2) {
		City city1 = getCity(user1);
		City city2 = getCity(user2);

		FriendCenter c = FriendCenter.getInstance();
		c.remove(city1.getId(), city2.getId());
		c.addFriend(city1.getId(), city2.getId());
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

		City city2 = getCity(id);

		if (city2 == null) {
			throw new RuntimeException("玩家不存在" + user);
		}

		return city2;
	}
}