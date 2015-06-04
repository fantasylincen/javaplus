package cn.mxz.xianshi;

import cn.mxz.city.City;
import cn.mxz.script.Script;
import cn.mxz.util.counter.CounterKey;

public class XianShiTransformImpl implements XianShiTransform {

	private City	user;

	@Override
	public WanLiGold getWanLiGold() {
		Recruitor rt = RecruitorFactory.create(3, user, true);
		int times = user.getUserCounter().get(CounterKey.XUN_XIAN_TEN_TIMES_TIMES);
		double disCount = Script.XunXian.getDiscount(times);
		return new WanLiGoldImpl(rt.getNeed(disCount));
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

}
