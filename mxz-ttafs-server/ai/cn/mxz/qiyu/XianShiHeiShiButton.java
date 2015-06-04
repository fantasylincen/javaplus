package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import define.D;

public class XianShiHeiShiButton extends AbstractQiYuButton {

	public XianShiHeiShiButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.XianShiHeiShi_14;
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
//		return isOpen();
	}


}
