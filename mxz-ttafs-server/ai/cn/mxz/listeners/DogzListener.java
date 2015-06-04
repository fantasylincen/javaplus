package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.battle.Camp;
import cn.mxz.city.City;
import cn.mxz.dogz.Dogz;
import cn.mxz.dogz.DogzManager;
import cn.mxz.events.FightEndEvent;
import cn.mxz.events.Listener;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;

/**
 * 用于让玩家神兽加成作用失效
 * @author 林岑
 */
//战斗结束, 神兽xxx
public class DogzListener implements Listener<FightEndEvent> {

	@Override
	public void onEvent(FightEndEvent e) {

		Battle battle = e.getBattle();

		closeDogzSkill(battle.getUnder());

		closeDogzSkill(battle.getUpper());

		clearAngry(battle.getUnderPlayerCamp());
		clearAngry(battle.getUpperPlayerCamp());
	}

	private void clearAngry(PlayerCamp p) {
		if(p != null) {
			City c = p.getCity();
			
			DogzManager dm = c.getDogzManager();
			Dogz fighting = dm.getFighting();
			if(fighting != null) {
				fighting.unangry();
			}
		}
	}

	private void closeDogzSkill(Camp<? extends Fighter> upper2) {

		if (upper2 instanceof PlayerCamp) {

			PlayerCamp pc = (PlayerCamp) upper2;

			City c = pc.getCity();

			Dogz f = c.getDogzManager().getFighting();

			if(f != null) {

				f.deactivateSkill();
			}
		}
	}
}
