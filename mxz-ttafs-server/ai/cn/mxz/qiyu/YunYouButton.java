package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.yunyou.YunyouData;
import define.D;

public class YunYouButton extends AbstractQiYuButton {

	private YunyouData	data;

	public YunYouButton(City city, YunyouData data) {
		super(city);
		this.data = data;
	}


	@Override
	public int getId() {
		return ActivityIds.YunYouXianRen_3;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		return data.getEndSec() < (System.currentTimeMillis() / 1000);//指点结束才显示感叹号，lk改
	}

	@Override
	public int getFighterId() {
		return data.getHeroId();
	}

	@Override
	public int getYunYouId() {
		return data.getId();
	}

}
