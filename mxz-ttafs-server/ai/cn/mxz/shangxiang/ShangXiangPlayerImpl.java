package cn.mxz.shangxiang;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.Marker;
import define.D;

public class ShangXiangPlayerImpl implements ShangXiangPlayer {

	private City	city;

	public ShangXiangPlayerImpl(City city) {
		this.city = city;
	}

	@Override
	public void receive() {

		if (isNight()) {
			checkNight();
			city.getPlayer().add(PlayerProperty.PHYSICAL, D.PHYSICAL_NIGHT);
			mark(CounterKey.RECEIVED_NIGHT);
		} else {
			checkNoon();
			city.getPlayer().add(PlayerProperty.PHYSICAL, D.PHYSICAL_NOON);
			mark(CounterKey.RECEIVED_NOON);
		}
		city.getUserCounterAuto().add(CounterKey.SHANG_XIANG_TIMES, 1); //增加上香次数
	}

	private void mark(CounterKey c) {
		city.getUserCounterAuto().mark(c);
	}

	private void checkNoon() {
		if (hasReceiveNoon()) {
			throw new OperationFaildException(S.S10232);
		}
	}

	private void checkNight() {
		if (hasReceiveNight()) {
			throw new OperationFaildException(S.S10233);
		}
	}

	@Override
	public int getRemainSec() {

		if (isNight()) {
			return getRemainEnd(D.SHANG_XIANG_TIME_NIGHT);
		}

		if(isNoon()) {
			return getRemainEnd(D.SHANG_XIANG_TIME_NOON);
		}

		int a = getRemainStart(D.SHANG_XIANG_TIME_NIGHT);
		int b = getRemainStart(D.SHANG_XIANG_TIME_NOON);
		return Math.min(a, b);
	}

	private int getRemainEnd(String t) {
		return Util.Time.getRemainSec(getLast(t));
	}

	private String getLast(String t) {
		return t.split(" to ")[1];
	}

	private int getRemainStart(String t) {
		return Util.Time.getRemainSec(getFront(t));
	}

	private String getFront(String t) {
		return t.split(" to ")[0];
	}

	@Override
	public boolean isNight() {
		return Util.Time.isIn(D.SHANG_XIANG_TIME_NIGHT);
	}

	@Override
	public boolean isNoon() {
		return Util.Time.isIn(D.SHANG_XIANG_TIME_NOON);
	}

	@Override
	public boolean hasReceiveNight() {

		Marker c = city.getUserCounter();

		return c.isMark(CounterKey.RECEIVED_NIGHT);

	}

	@Override
	public boolean hasReceiveNoon() {

		Marker c = city.getUserCounter();

		return c.isMark(CounterKey.RECEIVED_NOON);

	}

}
