package cn.mxz.listeners;

import java.util.List;

import cn.mxz.events.Listener;
import cn.mxz.events.mission.MissionEnterEvent;
import cn.mxz.fighter.Fighter;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.user.team.god.Hero;

/**
 * 进入副本回满血
 * @author Administrator
 *
 */
public class FullHpEnterMission implements Listener<MissionEnterEvent> {

	@Override
	public void onEvent(MissionEnterEvent e) {
		try {
			hpFull(e.getUser().getFormation().getSelected());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void hpFull(PlayerCamp playerCamp) {
		List<Hero> fs = playerCamp.getFighters();
		for (Fighter hero : fs) {
			hero.addHp(hero.getHpMax() - hero.getHpNow());
		}
	}
}
