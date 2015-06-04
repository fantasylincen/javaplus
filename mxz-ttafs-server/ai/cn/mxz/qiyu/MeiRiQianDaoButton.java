package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.zan.ZanManager;
import define.D;

public class MeiRiQianDaoButton extends AbstractQiYuButton {

	public MeiRiQianDaoButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.QianDaoYouLi_8;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		ZanManager zan = city.getZanManager();
		return !zan.getTodayIsClick();
	}

}
