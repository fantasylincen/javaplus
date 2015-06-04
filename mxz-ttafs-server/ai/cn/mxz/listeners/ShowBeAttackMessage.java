package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.city.City;
import cn.mxz.events.FightEndEvent;
import cn.mxz.events.Listener;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.newpvp.PvpManager;
import cn.mxz.newpvp.PvpPlayer;

public class ShowBeAttackMessage implements Listener<FightEndEvent> {

	@Override
	public void onEvent(FightEndEvent e) {

		Battle battle = e.getBattle();
		
		if (!(battle instanceof PvpBattle)) {
			return ;
		}
		

		PlayerCamp under = battle.getUnderPlayerCamp();

		PlayerCamp upper = battle.getUpperPlayerCamp();

		City city = upper.getCity();

		PvpManager manager = city.getNewPvpManager();

		PvpPlayer player = manager.getPlayer();

		player.addBeAttackMessage(under);
	}

}
