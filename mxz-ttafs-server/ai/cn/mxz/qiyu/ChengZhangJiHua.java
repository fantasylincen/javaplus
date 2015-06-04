package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import define.D;

public class ChengZhangJiHua  extends AbstractQiYuButton  {

	public ChengZhangJiHua(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.ChengZhangJiHua_16;
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
