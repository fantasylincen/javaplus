package cn.mxz.listeners;

import cn.mxz.city.City;
import cn.mxz.events.HeroLevelUpEvent;
import cn.mxz.events.Listener;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.debuger.Debuger;

/**
 * 战士升级后, 气血恢复最大值
 * 
 * @author 林岑
 * 
 */
// 战士升级时, 气血恢复到最大值
public class LogHeroLevelUp implements Listener<HeroLevelUpEvent> {

	@Override
	public void onEvent(HeroLevelUpEvent e) {

		Hero hero = e.getHero();

		if (hero.isPlayer()) {
			City city = hero.getCity();
			Debuger.debug("玩家" + city.getId() + ","
					+ city.getPlayer().getNick() + " 升级:" + city.getLevel());
		}
	}
}
