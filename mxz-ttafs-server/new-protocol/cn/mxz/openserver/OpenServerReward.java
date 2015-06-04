package cn.mxz.openserver;

import cn.javaplus.util.Util;
import cn.mxz.OpenServicerTemplet;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.city.UserPrizeSender;
import cn.mxz.fighter.FighterSnapshoot;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

/**
 * 开服奖励
 * 
 * @author 林岑
 * 
 */
public class OpenServerReward {

	private OpenServicerTemplet temp;
	private City city;
	private UserCounter c;

	public OpenServerReward(OpenServicerTemplet temp, City city) {
		this.temp = temp;
		this.city = city;
		c = city.getUserCounterHistory();
	}

	public int getDay() {
		return temp.getDay();
	}

	public boolean getHasReceive() {
		return c.isMark(CounterKey.OPEN_SERVER_REWARD_HAS_RECEIVE, getDay());
	}

	private void markReceive() {
		c.mark(CounterKey.OPEN_SERVER_REWARD_HAS_RECEIVE, getDay());
	}

	public void sendReward() {
		String award = temp.getAward();
		UserPrizeSender sd = city.getPrizeSender1();
		FighterSnapshoot s = new FighterSnapshoot(city);
		s.snapshoot();
		sd.send(award);
		markReceive();
		s.snapshoot();
	}

	// public boolean getCanReceive() {
	//
	// boolean mark = city.getUserCounter().isMark(
	// CounterKey.OPEN_SERVER_REWARD_HAS_RECEIVE_TODAY);
	// if (mark) {
	// return false;
	// }
	//
	// if (getHasReceive()) {
	// return false;
	// }
	//
	// int day = c.get(CounterKey.OPEN_SERVER_REWARD_PROGRESS);
	//
	// int day2 = getDay();
	//
	// return day == day2 - 1;
	// }

	public boolean getCanReceive() {

		if (getHasReceive()) {
			return false;
		}

		if (isPassProgress()) {
			return false;
		}

		return true;
	}

	/**
	 * 这个奖励的天数 是不是已经超过今日天数
	 * 
	 * @return
	 */
	private boolean isPassProgress() {
		UserCounter his = city.getUserCounterHistory();
		int day = his.get(CounterKey.OPEN_SERVER_REWARD_PROGRESS);
		int dayOffset = getDay();
		return dayOffset > day;
	}
	
	/**
	 * 这个奖励的天数 是不是已经超过今日天数
	 * 
	 * @return
	 */
	private boolean isPassCurrentDay() {

		long i = city.getPlayer().get(PlayerProperty.CREATE_SEC);
		long createMilis = i * 1000;

		int currentDay = Util.Time.getCurrentDay();
		int createDay = Util.Time.getDay(createMilis);

		int dayOffset = getDay();

		return createDay + dayOffset - 1 > currentDay;
	}

	// public void receive() {
	// String award = temp.getAward();
	// city.getPrizeSender1().send(award);
	// c.mark(CounterKey.OPEN_SERVER_REWARD, getDay());
	// }
	//
	// public boolean getCanReceive() {
	//
	// long i = city.getPlayer().get(PlayerProperty.CREATE_SEC);
	// long milis = i * 1000 ;
	// int cd = Util.Time.getCurrentDay();
	// int day = Util.Time.getDay(milis);
	//
	// int d = cd - day + 1;
	//
	// if (getHasReceive()) {
	// return false;
	// }
	//
	// return d == getDay();
	//
	// }

	@Override
	public int hashCode() {
		return (city.getId() + ":" + getDay()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpenServerReward other = (OpenServerReward) obj;
		return other.city.getId().equals(city.getId())
				&& getDay() == other.getDay();
	}

}
