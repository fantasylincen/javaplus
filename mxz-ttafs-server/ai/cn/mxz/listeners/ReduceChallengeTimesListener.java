package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightEndEvent;
import cn.mxz.events.Listener;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;

/**
 * 挑战完了之后, 扣除挑战次数
 * @author 林岑
 *
 */
public class ReduceChallengeTimesListener implements Listener<FightEndEvent> {

	@Override
	public void onEvent(FightEndEvent event) {
	
		Battle b = event.getBattle();
		
		if (!(b instanceof PvpBattle)) {
			return ;
		}
		
		City city = event.getCity();
		PvpManager m = city.getNewPvpManager();
		PvpPlayer p = m.getPlayer();
		p.reduceRemainTimes(1);
	}


}
