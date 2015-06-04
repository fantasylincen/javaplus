package cn.mxz.listeners;

import cn.mxz.events.HeroLevelUpEvent;
import cn.mxz.events.Listener;
import cn.mxz.fighter.HeroImpl;
import cn.mxz.fighter.HeroProperty;
import cn.mxz.user.team.god.Hero;
import db.dao.impl.DaoFactory;

/**
 * 战士升级后, 气血恢复最大值
 * @author 林岑
 *
 */
// 战士升级时, 气血恢复到最大值
public class HpToMax implements Listener<HeroLevelUpEvent> {

	@Override
	public void onEvent(HeroLevelUpEvent e) {

		Hero hero = e.getHero();

		if (hero instanceof HeroImpl) {

			HeroImpl h = (HeroImpl) hero;

			h.getDto().setV(HeroProperty.HP.getValue(), hero.getAttribute().getHp());

			DaoFactory.getNewFighterDao().update(h.getDto());
		}
	}
}
