package cn.mxz.listeners.pvp;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightingLoseEvent;
import cn.mxz.events.Listener;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.util.counter.CounterKey;

public class PvpLoseListener implements Listener<FightingLoseEvent> {

	@Override
	public void onEvent(FightingLoseEvent event) {

		Battle b = event.getBattle();

		if (!(b instanceof PvpBattle)) {
			return;
		}

		PlayerCamp under = b.getUnderPlayerCamp();

		City city = under.getCity();
		PvpManager pm = city.getNewPvpManager();
		pm.addPvpLoseTimes(1);
		pm.clearPvpWinSteakToday();

		city.getUserCounter().add(CounterKey.PVP_LOSE_STREAK_TODAY, 1);
	}

}
