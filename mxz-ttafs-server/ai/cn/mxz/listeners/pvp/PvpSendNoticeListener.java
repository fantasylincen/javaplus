package cn.mxz.listeners.pvp;
//package cn.mxz.pvp.event;
//
//import message.S;
//import cn.mxz.battle.Battle;
//import cn.mxz.battle.Camp;
//import cn.mxz.city.City;
//import cn.mxz.city.CityFactory;
//import cn.mxz.event.Listener1;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.events.FightingWinEvent;
//import cn.mxz.fighter.Fighter;
//import cn.mxz.fighter.PlayerHero;
//import cn.mxz.util.message.MessageSenderToAllUp;
//
//
//public class PvpSendNoticeListener implements Listener1 {
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		FightingWinEvent e1  = (FightingWinEvent) e;
//
//		Battle battle = e1.getBattle();
//
//		Camp<? extends Fighter> under = battle.getUnder();
//
//		for (Fighter f : under.getFighters()) {
//
//			if (f instanceof PlayerHero) {
//
//				PlayerHero h = (PlayerHero) f;
//
//				String uname = h.getUname();
//
//				City city = CityFactory.getCity(uname);
//
//				new MessageSenderToAllUp().sendMessage(S.S10096, city.getPlayer().getNick());
//
//				break;
//			}
//		}
//	}
//
//	@Override
//	public Class<?> getEventListendClass() {
//		return FightingWinEvent.class;
//	}
//}
