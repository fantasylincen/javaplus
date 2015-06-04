package cn.mxz.mission.old.listeners;

import cn.mxz.battle.Camp;
import cn.mxz.city.City;
import cn.mxz.mission.old.MissionAdaptor;
import cn.mxz.mission.old.events.EnterEvent;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

/**
 * 进入副本监听器
 * @author 林岑
 *
 */

public class RecoverHpListener extends MissionAdaptor{

	@Override
	public void onEnter(EnterEvent e) {

		City c = e.getSouce();

		Camp<Hero> selected = c.getFormation().getSelected();

		//所有上阵神将恢复满血

		for (Hero hero : selected.getFighters()) {

			Debuger.debug("EnterListener.onEnter()" + hero + "进入副本, 恢复满血: " + hero.getHpNow() + "/" + hero.getAttribute().getHp());

			hero.addHp(hero.getAttribute().getHp() - hero.getHpNow());	//恢复满血
		}
	}
}
