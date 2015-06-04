package cn.mxz.listeners.battle;

import java.util.List;

import cn.mxz.events.FightingStartEvent;
import cn.mxz.events.Listener;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.god.Hero;

/**
 * 战斗前 将战士气血恢复满
 * 
 * @author 林岑
 * 
 */
public class HpFullOnFightingStart implements Listener<FightingStartEvent> {

	@Override
	public void onEvent(FightingStartEvent event) {
		try {
			hpFull(event.getUnder());
		} catch (Exception e) {
			System.err.println("no problerm:" + e.getMessage());
		}
	}

	private void hpFull(PlayerCamp upper) {
		if (upper != null) {
			List<Hero> fs = upper.getFighters();
			for (Hero hero : fs) {
				hero.addHp(hero.getHpMax() - hero.getHpNow());
			}
		}
	}

}
