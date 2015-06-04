package cn.mxz.base.prize;

import cn.mxz.city.City;
import cn.mxz.monthcard.MonthCard;
import cn.mxz.user.Player;

public class YueKa implements AwardAble {

	@Override
	public void award(Player player) {
		award(player.getCity());
	}

	@Override
	public void award(City city) {
//		new MonthCard(city).add();
//		city.getMonthCard().add();
	}

}
