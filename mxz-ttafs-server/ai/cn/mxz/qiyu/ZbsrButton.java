package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import define.D;

public class ZbsrButton extends AbstractQiYuButton {

	public ZbsrButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.ZhuangBeiShangRen_18;
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
