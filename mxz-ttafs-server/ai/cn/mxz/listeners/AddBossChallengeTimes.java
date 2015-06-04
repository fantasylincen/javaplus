package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightEndEvent;
import cn.mxz.events.Listener;
import cn.mxz.shenmo.battle.ShenmoBattle;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;

public class AddBossChallengeTimes implements Listener<FightEndEvent> {

	@Override
	public void onEvent(FightEndEvent event) {

		Battle b = event.getBattle();
		if (b instanceof ShenmoBattle) {

			City city = event.getCity();

			// 这2行代码成就任务需要
			UserCounterSetter uc = city.getUserCounterHistory();
			uc.add(CounterKey.SHEN_MO_CHALLENGE_TIMES, 1);// 记录一次攻击boss的次数
		}

	}

}
