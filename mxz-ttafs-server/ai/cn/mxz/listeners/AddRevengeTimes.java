package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.enemy.EnemyBattle;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;

public class AddRevengeTimes implements Listener<FightingWinEvent> {

	@Override
	public void onEvent(FightingWinEvent event) {
		Battle b = event.getBattle();
		if (b instanceof EnemyBattle) {
			City city = event.getCity();
			UserCounterSetter c = city.getUserCounterHistory();
			c.add(CounterKey.REVENGE_TIMES, 1);
		}
	}
}
