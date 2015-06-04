package cn.mxz.listeners;

import cn.mxz.Attribute;
import cn.mxz.city.AttributeRecorder;
import cn.mxz.city.City;
import cn.mxz.events.BeforeHeroExpAddEvent;
import cn.mxz.events.Listener;
import cn.mxz.user.team.god.Hero;

/**
 * 记录前一等级 玩家的属性
 *
 * @author 林岑
 *
 */
//战士增加经验前, 保存属性
public class SaveLastAttribute implements Listener<BeforeHeroExpAddEvent> {

	@Override
	public void onEvent(BeforeHeroExpAddEvent e) {
		Hero hero = e.getHero();
		City city = e.getCity();

		Attribute attribute = hero.getAttribute();
		int level = hero.getLevel();

		AttributeRecorder recorder = city.getAttributeRecorder();

		recorder.save(level, attribute);
	}
}
