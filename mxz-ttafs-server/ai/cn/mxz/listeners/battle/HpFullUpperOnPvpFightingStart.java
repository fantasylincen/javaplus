package cn.mxz.listeners.battle;

import java.util.List;

import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpFightingStartEvent;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.god.Hero;

/**
 * 战斗前 将上方战士气血恢复满
 * 
 * @author 林岑
 * 
 */
public class HpFullUpperOnPvpFightingStart implements Listener<PvpFightingStartEvent> {

	@Override
	public void onEvent(PvpFightingStartEvent event) {
		try {
			hpFull(event.getUpper());
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
