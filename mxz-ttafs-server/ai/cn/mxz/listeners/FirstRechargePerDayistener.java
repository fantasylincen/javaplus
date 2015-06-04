package cn.mxz.listeners;

import cn.mxz.events.Listener;
import cn.mxz.events.ZeroClockEvent;
import cn.mxz.firstrechargeperday.FirstRechargePerDay;

// 重新生成每日充值奖励的奖品
public class FirstRechargePerDayistener implements Listener<ZeroClockEvent> {

	@Override
	public void onEvent(ZeroClockEvent e) {
		FirstRechargePerDay.INSTANCE.genAward();
	}

}
