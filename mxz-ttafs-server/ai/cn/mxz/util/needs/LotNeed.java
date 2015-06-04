package cn.mxz.util.needs;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.user.Player;
import cn.mxz.util.checker.PlayerChecker;
import define.D;

class LotNeed implements INeeds {

	private Integer	count;

	LotNeed(Integer count) {

		this.count = count;
	}

	@Override
	public void checkEnouph(Player player) {

		new PlayerChecker(player).checkPlayerProperty(PlayerProperty.CHARM, count);
	}

	@Override
	public void deduct(Player player) {

		player.reduce(PlayerProperty.CHARM, getNeed(player));
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

		return city.getPlayer().get(PlayerProperty.CHARM) / count;
	}

	@Override
	public int getHaveNow(Player player) {

		return player.get(PlayerProperty.CHARM);
	}

	@Override
	public int getNeed(Player player) {

		return count;
	}

	@Override
	public int getId() {
		return D.ID_LOT;
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
