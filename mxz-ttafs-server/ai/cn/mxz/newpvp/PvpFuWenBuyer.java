package cn.mxz.newpvp;

import message.S;
import cn.mxz.VipPrivilegeTemplet;
import cn.mxz.VipPrivilegeTempletConfig;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.shop.Shopper;
import cn.mxz.user.Player;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;
import cn.mxz.util.counter.UserCounterSetter;
import define.D;

public class PvpFuWenBuyer {

	private City	city;

	public PvpFuWenBuyer(City city) {
		this.city = city;
	}

	public void buyFuWen() {

		Shopper s = new Shopper(city);
		s.buy(D.PVP_FUWEN_ID, 1);

		markBuyTimes();
	}

	private void markBuyTimes() {
		UserCounterSetter uc = city.getUserCounter();
		uc.add(CounterKey.PVP_FU_WEN_BUY_TIMES, 1);
	}

	/**
	 * 使用给与符文的奖励, 不会扣除符文
	 */
	public void sendFuWenReward() {
		checkUseTimes();

		PvpManager m = city.getNewPvpManager();
		PvpPlayer p = m.getPlayer();
		p.addRemainTimes(D.PVP_FUWEN_TIMES_ADD);

		UserCounterSetter uc = city.getUserCounter();
		uc.add(CounterKey.USE_FU_WEN_TIMES, 1);
	}

	private void checkUseTimes() {
		Player player = city.getPlayer();
		int vipLevel = city.getVipPlayer().getLevel();
		VipPrivilegeTemplet temp = VipPrivilegeTempletConfig.get((byte) vipLevel);

		UserCounter uc = city.getUserCounter();
		int times = uc.get(CounterKey.USE_FU_WEN_TIMES);

		int t = temp.getAthleticsTimes();

		if (city.isTester()) { //无限使用斗法符文
			t = Integer.MAX_VALUE;
		}

		if (times > t) {
			throw new OperationFaildException(S.S10220);
		}
	}

}
