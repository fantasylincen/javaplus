package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.chuangzhen.ChuangZhenPlayer;
import cn.mxz.city.City;
import define.D;

public class ChuangZhenButton extends AbstractQiYuButton {

	public ChuangZhenButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.DuTianJie_10;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if(D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		ChuangZhenPlayer p = city.getChuangZhenPlayer();
		return p.getCurrentTimes() < p.getMaxTimes();
	}
}
