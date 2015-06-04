package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import define.D;

public class YaoQianShuButton extends AbstractQiYuButton {

	public YaoQianShuButton(City city) {
		super(city);
	}


	@Override
	public int getId() {
		return ActivityIds.YaoQianShu_5;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
//		Cornucopia c = CornucopiaFactory.getCornucopia(city.getId());
//		int cc = c.getRunTimesToday();
//		return cc < c.getMaxTime();
		
//		CornucopiaTemplet temp = CornucopiaTempletConfig.get(cc + 1);
//		c.getMaxTime()
//		return temp != null;
		
		return false;
	}


}
