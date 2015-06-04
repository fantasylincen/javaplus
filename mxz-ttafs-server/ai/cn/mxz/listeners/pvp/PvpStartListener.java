package cn.mxz.listeners.pvp;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightingStartEvent;
import cn.mxz.events.Listener;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounterSetter;

public class PvpStartListener implements Listener<FightingStartEvent> {

	@Override
	public void onEvent(FightingStartEvent event) {

		Battle b = event.getBattle();
		
		if (!(b instanceof PvpBattle)) {
			return ;
		}
		
		//记录玩家PVP累计挑战次数
		PlayerCamp under = b.getUnderPlayerCamp();
		City city = under.getCity();
		UserCounterSetter his = city.getUserCounterHistory();
		his.add(CounterKey.PVP_CHALLENGE_TIMES, 1);

		UserCounterSetter c = city.getUserCounter();
		c.add(CounterKey.PVP_CHALLENGE_TIMES, 1);
	}


}
