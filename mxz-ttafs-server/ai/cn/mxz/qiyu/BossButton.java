package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import define.D;

public class BossButton extends AbstractQiYuButton implements QiYuButton {

	public BossButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.Zhan_9;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		return false;
	}
}
