package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import define.D;

public class FeedBackButton extends AbstractQiYuButton {

	public FeedBackButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.ChongZhiHuiKui_20;
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
