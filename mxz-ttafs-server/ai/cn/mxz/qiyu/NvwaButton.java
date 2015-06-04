package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import define.D;

public class NvwaButton extends AbstractQiYuButton {

	public NvwaButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.ShangGuHunXia_19;
	}

	@Override
	public boolean isOpen() {
		return super.isOpen() && isInActivityTime();
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
