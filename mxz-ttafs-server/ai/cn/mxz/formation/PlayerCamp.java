package cn.mxz.formation;

import cn.mxz.battle.Camp;
import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

public interface PlayerCamp extends Camp<Hero> {

	/**
	 * @return 玩家城池
	 */
	City getCity();

}