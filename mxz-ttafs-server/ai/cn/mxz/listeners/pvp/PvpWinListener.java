package cn.mxz.listeners.pvp;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.util.counter.CounterKey;

public class PvpWinListener implements Listener<FightingWinEvent> {

	@Override
	public void onEvent(FightingWinEvent event) {

		//记录最强王者争霸赛中今日连胜次数
		Battle b = event.getBattle();
		
		if (!(b instanceof PvpBattle)) {
			return ;
		}
		
		PlayerCamp under = b.getUnderPlayerCamp();

		City city = under.getCity();
		PvpManager pm = city.getNewPvpManager();
		pm.addPvpWinTimes(1);
		pm.addPvpWinSteakToday(1);

		city.getUserCounter().clear(CounterKey.PVP_LOSE_STREAK_TODAY);

	}
}
