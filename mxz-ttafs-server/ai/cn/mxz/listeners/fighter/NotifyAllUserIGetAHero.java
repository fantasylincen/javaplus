package cn.mxz.listeners.fighter;

import message.S;
import cn.mxz.city.City;
import cn.mxz.events.CreateNewHeroEvent;
import cn.mxz.events.Listener;
import cn.mxz.user.team.god.Hero;
import cn.mxz.util.message.MessageSenderToAllUp;

//玩家获得甲级伙伴是, 全服通知
public class NotifyAllUserIGetAHero implements Listener<CreateNewHeroEvent> {

	@Override
	public void onEvent(CreateNewHeroEvent e) {
		Hero hero = e.getHero();
		City city = e.getCity();
		
		if(hero.isPlayer()) {
			return;
		}
		
//		品阶 1 丁 2 丙 3 乙 4 甲 5 甲a 6 神
		int step = hero.getStep();
		
		if(step == 3) {
			new MessageSenderToAllUp().sendMessage(S.S72000, city.getPlayer().getNick(), hero.getName());

		} else if(step == 4 || step == 5) {
			
			new MessageSenderToAllUp().sendMessage(S.S72003, hero.getName(), city.getPlayer().getNick());
		}
		
	}

}
