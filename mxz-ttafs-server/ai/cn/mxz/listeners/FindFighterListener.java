package cn.mxz.listeners;

import cn.mxz.events.Listener2;
import cn.mxz.findfighter.FindFighterEvent;


public class FindFighterListener implements Listener2 {

	@Override
	public void onEvent(Object e) {

//		FindFighterEvent event = (FindFighterEvent) e;
//
//		City city = event.getCity();
//
//		List<Hero> h = event.getHeros();
//
//		for (Hero hero : h) {
//
//			String name = FighterTempletConfig.get(hero.getTypeId()).getName();
//
//			int quality = hero.getQuality();
//			GodQurlityTemplet temp = GodQurlityTempletConfig.get(quality);
//			int step = temp.getStep();
//
//			int code = -1;
//
//			//普通 精良 西游 试试 闯说 神级 123456
//
//			if(step == 2) {
//
//				code = S.S71014;
//
//			} else if(step == 3) {
//
//				code = S.S71015;
//
//			} else if(step == 4) {
//
//				code = S.S71016;
//
//			} else if(step == 5) {
//
//				code = S.S71017;
//			}
//
//			if(code != -1) {
//
//				new MessageSenderToAllUp().sendMessage(code, city.getPlayer().getNick(), name);
//			}
//			if(step > D.SUPER_HERO) {
//
//				new MessageSenderToAllUp().sendMessage(S.S71004, city.getPlayer().getNick(), name);
//			}
//		}

	}

	@Override
	public Class<?> getEventListendClass() {
		return FindFighterEvent.class;
	}

}
