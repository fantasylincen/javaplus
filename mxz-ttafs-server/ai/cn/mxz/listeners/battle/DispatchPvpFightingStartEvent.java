package cn.mxz.listeners.battle;

import cn.mxz.battle.Battle;
import cn.mxz.events.Events;
import cn.mxz.events.FightingStartEvent;
import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpFigintingStartEvent;
import cn.mxz.newpvp.PvpBattle;

/**
 * 战斗前 将战士气血恢复满
 * 
 * @author 林岑
 * 
 */
public class DispatchPvpFightingStartEvent implements Listener<FightingStartEvent> {

	@Override
	public void onEvent(FightingStartEvent event) {
		Battle battle = event.getBattle();
		if (battle instanceof PvpBattle) {
			PvpBattle pvpBattle = (PvpBattle) battle;
			Events.getInstance().dispatch(new PvpFigintingStartEvent(pvpBattle));
		}
	}


}
