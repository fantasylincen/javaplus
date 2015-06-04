package cn.mxz.base.prize;

import cn.mxz.bossbattle.Prize;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.spirite.SpiriteManager;
import cn.mxz.user.Player;

/**
 * 送魂魄
 * @author 林岑
 *
 */
public class HPPrize implements Prize {

	private Integer	id;
	private Integer	count;

	public HPPrize(Integer id, Integer count) {
		this.id = id;
		this.count = count;
	}

	public void award(City city) {
		award(city.getPlayer());
	}
	@Override
	public void award(Player player) {
		City city = CityFactory.getCity(player.getId());
		SpiriteManager sm = city.getSpiriteManager();

		for (int i = 0; i < count; i++) {
			sm.add(id);
		}

	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public int getCount() {
		return count;
	}

}
