package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.firstrechargeperday.FirstRechargePerDay;
import cn.mxz.util.counter.CounterKey;
import define.D;

public class MeiRiShouChong extends AbstractQiYuButton {

	private ShouChongYouLi button;

	public MeiRiShouChong(City city, ShouChongYouLi button) {
		super(city);
		this.button = button;
	}

	@Override
	public int getId() {
		return ActivityIds.MeiRiShouChong_17;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		// TODO LK
		return FirstRechargePerDay.INSTANCE.showTips(this.city);

	}

	@Override
	public boolean isOpen() {

		if(D.LANGUAGE == 1) {
			 if (button.isOpen()) {
			 return false;
			 }
			
			 boolean open = super.isOpen();
			
			 return open && !hasReceive();
		} else {
			
			return true;
		}
		
	}

	private boolean hasReceive() {
		return city.getUserCounter().isMark(CounterKey.FIRST_RECHARGE_PER_DAY);
	}
}
