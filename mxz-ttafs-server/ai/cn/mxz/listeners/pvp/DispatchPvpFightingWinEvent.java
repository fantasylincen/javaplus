package cn.mxz.listeners.pvp;

import cn.mxz.battle.Battle;
import cn.mxz.events.Events;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpFightingWinEvent;
import cn.mxz.newpvp.PvpBattle;

public class DispatchPvpFightingWinEvent implements Listener<FightingWinEvent> {

	@Override
	public void onEvent(FightingWinEvent event) {

		Battle b = event.getBattle();
		
		if (!(b instanceof PvpBattle)) {
			return ;
		}
		
		PvpBattle battle = (PvpBattle) b;
		Events.getInstance().dispatch(new PvpFightingWinEvent(battle));
	}


}
