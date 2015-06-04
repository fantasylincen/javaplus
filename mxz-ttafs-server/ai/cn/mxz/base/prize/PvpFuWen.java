package cn.mxz.base.prize;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.newpvp.PvpFuWenBuyer;
import cn.mxz.user.Player;

/**
 *
 * PVP 符文
 * @author 林岑
 *
 */
public class PvpFuWen implements AwardAble {

	@Override
	public void award(Player player) {
		City city = CityFactory.getCity(player.getId());
		new PvpFuWenBuyer(city).sendFuWenReward();
	}

	public void award(City city) {
		award(city.getPlayer());
	}
}
