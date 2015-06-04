package cn.mxz.listeners.battle;

import java.util.List;

import cn.mxz.battle.Battle;
import cn.mxz.battle.BattleCamp;
import cn.mxz.events.FightEndEvent;
import cn.mxz.events.Listener;
import cn.mxz.fighter.Fighter;

/**
 * 战斗前 将战士气血恢复满
 * 
 * @author 林岑
 * 
 */
public class HpFullOnFightingEnd implements Listener<FightEndEvent> {

	@Override
	public void onEvent(FightEndEvent event) {
		try {
			Battle battle = event.getBattle();
			hpFull(battle.getUnder());
		} catch (Exception e) {
			System.err.println("no problerm:" + e.getMessage());
		}
	}

	private void hpFull(BattleCamp battleCamp) {
		if (battleCamp != null) {
			List<Fighter> fs = battleCamp.getFighters();
			for (Fighter hero : fs) {
				hero.addHp(hero.getHpMax() - hero.getHpNow());
			}
		}
	}

}
