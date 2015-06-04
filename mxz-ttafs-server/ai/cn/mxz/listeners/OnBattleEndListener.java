package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.events.FightEndEvent;
import cn.mxz.events.Listener;
import cn.mxz.shenmo.battle.ShenmoBattle;

public class OnBattleEndListener implements Listener<FightEndEvent> {

	@Override
	public void onEvent(FightEndEvent event) {

		Battle b = event.getBattle();
		if (b instanceof ShenmoBattle) {
//			ShenmoBattle battle = (ShenmoBattle) b;
//			City city = event.getCity();							 	//玩家
//			BattleCamp upper = battle.getUpper(); //战斗结束时的神魔阵容
//			PlayerCamp under = battle.getUnderPlayerCamp();	//战斗结束时 我的阵容
		}
		
	}
}
