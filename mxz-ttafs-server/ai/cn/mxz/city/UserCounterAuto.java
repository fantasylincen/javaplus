package cn.mxz.city;

import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class UserCounterAuto implements UserCounter {

	public class UnImplMethodException extends RuntimeException {
		private static final long	serialVersionUID	= -4922072318885434495L;
	}

	private City	city;

	public UserCounterAuto(City city) {
		this.city = city;
	}

	@Override
	public boolean isMark(CounterKey id, Object... args) {
		throw new UnImplMethodException();
	}

	@Override
	public void mark(CounterKey id, Object... args) {
		city.getUserCounterHistory().mark(id, args);
		city.getUserCounter().mark(id, args);
	}

	@Override
	public void add(CounterKey id, int add, Object... args) {
		city.getUserCounterHistory().add(id, add, args);
		city.getUserCounter().add(id, add, args);
	}

	@Override
	public int get(CounterKey id, Object... args) {
		throw new UnImplMethodException();
	}

	@Override
	public void clear(CounterKey id, Object... args) {
		city.getUserCounterHistory().clear(id, args);
		city.getUserCounter().clear(id, args);
	}

	@Override
	public void set(CounterKey guideStatus, int value, Object... args) {
		city.getUserCounterHistory().set(guideStatus, value, args);
		city.getUserCounter().set(guideStatus, value, args);
	}

}
