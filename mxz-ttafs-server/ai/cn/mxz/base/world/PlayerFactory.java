package cn.mxz.base.world;

import cn.mxz.city.City;
import cn.mxz.user.Player;

public class PlayerFactory {

	public static Player getPlayer(String uname) {

		World world = WorldFactory.getWorld();

		City city = world.get(uname);

		if(city == null) {

			return null;
		}

		return city.getPlayer();
	}

}
