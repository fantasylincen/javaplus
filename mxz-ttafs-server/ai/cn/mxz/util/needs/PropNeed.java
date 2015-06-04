package cn.mxz.util.needs;

import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.user.Player;
import cn.mxz.util.checker.Checker;
import cn.mxz.util.checker.PlayerChecker;

class PropNeed implements INeeds {

	private Integer	id;

	private Integer	count;

	PropNeed(Integer id, Integer count) {

		this.id = id;

		this.count = count;
	}

	@Override
	public void checkEnouph(Player player) {

		final Checker c = new PlayerChecker(player);

		c.checkProp(id, count);
	}

	@Override
	public void deduct(Player player) {

		final World w = WorldFactory.getWorld();

		final City city = w.get(player.getId());

		int need = getNeed(player);
		
		city.getBagAuto().remove(id, need);
	}

	@Override
	public void discount(float discount) {

		count = (int) (count * discount);
	}

	@Override
	public int getDeductTimesMax(City city) {

		if (count == 0) {

			return 0;
		}
		return city.getBagAuto().getCount(id) / count;
	}

	@Override
	public int getHaveNow(Player player) {

		City city = CityFactory.getCity(player.getId());

		int count2 = city.getBagAuto().getCount(id);

		return count2;
	}

	@Override
	public int getNeed(Player player) {

		return count;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void checkEnouph(City user) {
		checkEnouph(user.getPlayer());
	}

	@Override
	public void deduct(City user) {
		deduct(user.getPlayer());

	}

	@Override
	public int getHaveNow(City user) {
		return getHaveNow(user.getPlayer());
	}

	@Override
	public int getNeed(City user) {
		return getNeed(user.getPlayer());
	}
}
