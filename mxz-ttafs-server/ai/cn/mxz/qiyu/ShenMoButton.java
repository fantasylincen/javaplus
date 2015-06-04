package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.prompt.PromptManager;
import define.D;

public class ShenMoButton extends AbstractQiYuButton {

	public ShenMoButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.MoShenRuQin_1;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		PromptManager pm = city.getPromptManager();
		boolean ymsjlklq = pm.getYmsjlklq();
		boolean ymsktz = pm.getYmsktz();
		// System.out.println("dddddddddddddddddddddddddddddddddddddd " +
		// ymsjlklq );
		return ymsjlklq || ymsktz;
	}
}
