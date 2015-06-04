package cn.mxz.chuangzhen;

import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import define.D;

public class CashReward implements PropReward {

	private int	cashEveryStar;
	private int	star;

	public CashReward(int cashEveryStar, int star) {
		this.cashEveryStar = cashEveryStar;
		this.star = star;
	}

	@Override
	public int getId() {
		return D.ID_CASH;
	}

	@Override
	public int getCount() {
		return cashEveryStar * star;
	}

	public void award(City city) {
		int count = getCount();
		city.getPlayer().add(PlayerProperty.CASH, count);
	}

}
