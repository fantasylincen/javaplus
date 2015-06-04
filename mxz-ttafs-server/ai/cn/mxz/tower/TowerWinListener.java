//package cn.mxz.tower;
//
//import java.util.List;
//
//import cn.mxz.battle.Battle;
//import cn.mxz.battle.Camp;
//import cn.mxz.battle.FightingWinEvent;
//import cn.mxz.event.Listen;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.fighter.Fighter;
//import cn.mxz.user.City;
//
//@Listen(eventClass = FightingWinEvent.class) class TowerWinListener implements Listener {
//
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		Battle battle = (Battle) e.getSource();
//
//		buildTowerReward(battle);
//	}
//
//	/**
//	 * 爬塔积分奖励
//	 * @param battle
//	 */
//	private void buildTowerReward(Battle battle) {
//
//		City city = battle.getUnder().getCity();
//
//		Camp<? extends Fighter> upper = battle.getUpper();
//
//		List<? extends Fighter> all = upper.getFighters();
//
//		int score = 0;
//
//		for (Fighter fighter : all) {
//
//			score += fighter.getAttribute().getHp();
//
//			if(fighter instanceof BugFighter) {	//如果是裂缝怪物
//
//				score += 10000;
//			}
//		}
//
//		city.getTowerMission().save(new TowerFightingRewardImpl(score));
//	}
//}
