package cn.mxz.hhdlb;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.qiyu.AbstractQiYuButton;
import define.D;

public class HhdlbButton extends AbstractQiYuButton {

	public HhdlbButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.HaoHuaLiBao_21;
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
