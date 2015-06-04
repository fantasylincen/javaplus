package cn.mxz.listeners;
//package cn.mxz.pvp;
//
//import cn.mxz.battle.Battle;
//import cn.mxz.battle.FightingStartEvent;
//import cn.mxz.city.City;
//import cn.mxz.city.PlayerProperty;
//import cn.mxz.event.Listener1;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.formation.PlayerCamp;
//
//class ReducePowerListener implements Listener1 {
//
//	private boolean	isFullFight;
//
//	
//	public ReducePowerListener(boolean isFullFight) {
//
//		this.isFullFight = isFullFight;
//	}
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		Battle source = (Battle) e.getSource();
//
//		PlayerCamp under = source.getUnderPlayerCamp();
//
//		City city = under.getCity();
//
//		if(isFullFight) {
//
//			city.getPlayer().reduce(PlayerProperty.POWER, 3);
//
//		} else {
//
//			city.getPlayer().reduce(PlayerProperty.POWER, 1);
//		}
//	}
//	@Override
//	public Class<?> getEventListendClass() {
//		return FightingStartEvent.class;
//	}
//
//}
