package cn.mxz.shenmo;

import cn.mxz.bag.BagAuto;
import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.user.Player;

public class SimplePrize implements Prize {

	int prizeId;
	int count;
	
	public SimplePrize(int id, int count) {
		this.prizeId  = id;
		this.count = count;
	}
	public void award(City city) {
		award(city.getPlayer());
	}

	@Override
	public void award(Player player) {
		City city = CityFactory.getCity(player.getId());
		BagAuto bag = city.getBagAuto();
		bag.addProp(prizeId, count);
	}

	@Override
	public int getId() {
		return prizeId;
	}

	@Override
	public int getCount() {
		return count;
	}

}
