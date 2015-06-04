package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightingStartEvent;
import cn.mxz.events.Listener;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.util.counter.CounterKey;
import cn.mxz.util.counter.UserCounter;

public class SavePvpRankStartToday implements Listener<FightingStartEvent> {

	@Override
	public void onEvent(FightingStartEvent e) {
		Battle b = e.getBattle();
		if (!(b instanceof PvpBattle)) {
			return ;
		}
		
		Battle battle = (Battle) b;
		PlayerCamp under = battle.getUnderPlayerCamp();
		City city = under.getCity();
		
		UserCounter uc = city.getUserCounter();
		int i = uc.get(CounterKey.PVP_RANK_START_TODAY);
		if(i == 0) {
			PvpManager pm = city.getNewPvpManager();
			PvpPlayer player = pm.getPlayer();
			uc.set(CounterKey.PVP_RANK_START_TODAY, player.getRankInAll());
		}
	}

}
