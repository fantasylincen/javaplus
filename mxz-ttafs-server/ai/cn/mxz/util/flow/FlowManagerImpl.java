package cn.mxz.util.flow;

import cn.javaplus.collections.counter.Counter;
import cn.mxz.city.City;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;
import cn.mxz.util.debuger.Debuger;

class FlowManagerImpl implements FlowManager {

	private City	city;
	private static Counter<String> size;

	FlowManagerImpl(City city) {
		this.city = city;
		if(size == null) {
			size = new Counter<String>();
		}
	}

	@Override
	public void add(int sizeThisTime) {
		size.add(city.getId(), sizeThisTime);
	}

	@Override
	public void commit() {

		UserCounterSetter today = city.getUserCounter();

		UserCounterSetter his = city.getUserCounterHistory();

		int s = size.get(city.getId());

		today.add(CounterKey.FLOW, s);

		his.add(CounterKey.FLOW, s);

		size.set(city.getId(), 0);

		Debuger.info("FLOW", city.getId() + ":" + s);
	}

	@Override
	public int getToday() {

		UserCounter c = city.getUserCounter();

		int t = c.get(CounterKey.FLOW);

		return t + size.get(city.getId());
	}

	@Override
	public int getHistory() {

		UserCounter c = city.getUserCounterHistory();

		int t = c.get(CounterKey.FLOW);

		return t + size.get(city.getId());
	}

}
