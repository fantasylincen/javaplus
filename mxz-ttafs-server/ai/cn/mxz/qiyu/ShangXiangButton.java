package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.shangxiang.ShangXiangPlayer;
import define.D;

public class ShangXiangButton extends AbstractQiYuButton {

	public ShangXiangButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.MeiRiShangXiang_11;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		ShangXiangPlayer p = city.getShangXiangPlayer();
		return p.isNight() && !p.hasReceiveNight() || p.isNoon() && !p.hasReceiveNoon();
	}


}
