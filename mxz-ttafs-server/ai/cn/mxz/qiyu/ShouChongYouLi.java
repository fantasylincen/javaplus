package cn.mxz.qiyu;

import cn.mxz.activity.ActivityIds;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import define.D;

public class ShouChongYouLi extends AbstractQiYuButton {

	public ShouChongYouLi(City city) {
		super(city);
	}

	@Override
	public int getId() {
		return ActivityIds.ShouChongYouLi_15;
	}

	@Override
	public boolean getHasTips() {
		if (D.LANGUAGE == 2 && !isOpen()) {
			return false;
		}
		if (isNewPlayer()) {
			return false;
		}
		UserCounter his = city.getUserCounterHistory();
		boolean hasReceive = his.isMark(CounterKey.HAS_RECEIVE_FIRST_RECHARGE_REWARD);
		if(hasReceive) {
			return false;
		}
		return false;
	}
	
	@Override
	public boolean isOpen() {
		boolean open = super.isOpen();
		return open && !hasReceive();
	}

	private boolean hasReceive() {
		UserCounter his = city.getUserCounterHistory();
		boolean hasReceive = his.isMark(CounterKey.HAS_RECEIVE_FIRST_RECHARGE_REWARD);
		return hasReceive;
	}

}
