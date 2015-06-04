package cn.mxz.hhdlb;

import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import define.D;

public class HhdlbManager {

	private City city;

	public HhdlbManager(City city) {
		this.city = city;
	}

	public City getCity() {
		return city;
	}

	public void receive() {
		if(!canReceive()) {
			throw new OperationFaildException(60286);
		}
		city.getPlayer().reduceGold(D.HHDLB_GOLD);
		city.getPrizeSender1().send(D.HHDLB_REWARD);
		UserCounter his = city.getUserCounterHistory();
		his.mark(CounterKey.HAS_RECEIVE_HHDLB);
	}

	public int getAll() {
		UserCounter his = city.getUserCounterHistory();
		return his.get(CounterKey.TOTAL_RECHARGE_GOLD_COUNT);
	}

	public boolean canReceive() {
		if(hasReceive()) {
			return false;
		}
		int all = getAll();
		return all >= D.HHDLB_GOLD_NEED;
	}

	public boolean hasReceive() {
		UserCounter his = city.getUserCounterHistory();
		return his.isMark(CounterKey.HAS_RECEIVE_HHDLB);
	}
}
