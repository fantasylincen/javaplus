package cn.mxz.mission.type;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.mission.old.PropPrize;
import cn.mxz.user.Player;
import define.D;

public class CashPrize implements PropPrize {

	private int	cash;

	public CashPrize(int cash) {
		this.cash = cash;
	}

	@Override
	public int getId() {
		return D.ID_CASH;
	}

	@Override
	public int getCount() {
		return cash;
	}

	public void award(City city) {
		award(city.getPlayer());
	}

	@Override
	public void award(Player player) {
		player.add(PlayerProperty.CASH, cash);
	}

}
