package cn.mxz.vip;

import cn.mxz.VipGiftTemplet;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class GiftImpl implements Gift {

	private VipGiftTemplet	vip;
	private City			user;

	public GiftImpl(VipGiftTemplet vip, City user) {
		this.vip = vip;
		this.user = user;
	}

	@Override
	public int getLevel() {
		return vip.getLevel();
	}

	@Override
	public boolean getHasBought() {
		UserCounter his = user.getUserCounterHistory();
		return his.isMark(CounterKey.VIP_GIFT_BOUGHT, vip.getLevel());
	}

	@Override
	public int getPropId() {
		return vip.getGiftId();
	}

}
