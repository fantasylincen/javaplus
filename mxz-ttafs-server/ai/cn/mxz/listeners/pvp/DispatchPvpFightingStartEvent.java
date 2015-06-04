package cn.mxz.listeners.pvp;

import cn.mxz.battle.Battle;
import cn.mxz.events.Events;
import cn.mxz.events.FightingStartEvent;
import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpFightingStartEvent;
import cn.mxz.newpvp.PvpBattle;

public class DispatchPvpFightingStartEvent implements Listener<FightingStartEvent> {

	@Override
	public void onEvent(FightingStartEvent event) {

		Battle b = event.getBattle();
		
		if (!(b instanceof PvpBattle)) {
			return ;
		}
		
		PvpBattle battle = (PvpBattle) b;
		Events.getInstance().dispatch(new PvpFightingStartEvent(battle, event.getUnder(), event.getUpper()));
	}


}
