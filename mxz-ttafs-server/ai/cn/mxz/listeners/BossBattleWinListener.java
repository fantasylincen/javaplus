package cn.mxz.listeners;
//package cn.mxz.activity.boss;
//
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.base.prize.PrizeSenderFactory;
//import cn.mxz.battle.BattlePrizeAble;
//import cn.mxz.battle.FightingWinEvent;
//import cn.mxz.city.City;
//import cn.mxz.event.Listener1;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.formation.PlayerCamp;
//
//
//public class BossBattleWinListener implements Listener1 {
//
//	private Boss	boss;
//
//	BossBattleWinListener(Boss boss) {
//
//		this.boss = boss;
//	}
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		BattlePrizeAble battle = (BattlePrizeAble) e.getSource();
//
//		PlayerCamp under = battle.getUnderPlayerCamp();
//
//		City city = under.getCity();
//
////		city.getBossMission().save(battle.getFighterPrize());
//
//		sendKillReward(boss, city); // 赠送Boss击杀奖励
//	}
//
//	private void sendKillReward(Boss boss, City city) {
//
//		PrizeSender ps = PrizeSenderFactory.getPrizeSender();
//
//		ps.send(city.getPlayer(), new BossKillReward(boss).getAwards());
//	}
//
//	@Override
//	public Class<?> getEventListendClass() {
//		return FightingWinEvent.class;
//	}
//}
