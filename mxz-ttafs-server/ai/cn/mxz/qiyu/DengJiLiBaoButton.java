package cn.mxz.qiyu;

import java.util.List;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.levelupreward.Reward;
import define.D;

public class DengJiLiBaoButton extends AbstractQiYuButton {

	public DengJiLiBaoButton(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.DengJiLiBao_7;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		List<Reward> all = city.getLevelUpRewardPlayer().getRewards();
		for (Reward reward : all) {
			if(reward.canReceive() ) {
				return true;
			}
		}
		return false;
	}
}
