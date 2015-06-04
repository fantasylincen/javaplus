package cn.mxz.mission.old;
//package cn.mxz.mission;
//
//import java.util.List;
//
//import cn.mxz.battle.Battle;
//import cn.mxz.battle.FightingWinEvent;
//import cn.mxz.event.Listen;
//import cn.mxz.event.Listener;
//import cn.mxz.event.ServerEvent;
//import cn.mxz.fighter.Fighter;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.user.City;
//import cn.mxz.util.counter.CounterKey;
//import cn.mxz.util.debuger.Debuger;
//
///**
// * 当战斗胜利时, 给玩家增加通关评分
// * @author 林岑
// *
// */
//@Listen(eventClass = FightingWinEvent.class)
//public class AddScoreWhilePassMapListener implements Listener {
//
//	private int	mapId;
//	private static MapDemonStarConfig	config;
//
//	public AddScoreWhilePassMapListener(int mapId) {
//		this.mapId = mapId;
//	}
//
//	@Override
//	public void onEvent(ServerEvent e) {
//
//		FightingWinEvent event = (FightingWinEvent) e;
//		
//		int round = event.getRound();
//		MissionStarManager manager = getMissionStartManager(event);
//		int count = new MissionStarCalc().getCount(round);
//		Debuger.debug("AddScoreWhilePassMapListener.onEvent() 获得星星数:" + count);
//		manager.save(mapId, count);
////		":" + 地图ID + ":", 怪物ID
//		saveDemonStarForTask(event, count);
//	}
//
//	private void saveDemonStarForTask(FightingWinEvent event, int count) {
//
//		if(config == null) {
//			
//			config = MapDemonStarConfig.initFromAchieveTaskTempletConfig();
//		}
//		
//		Battle battle = (Battle) event.getSource();
//
//		List<? extends Fighter> all = battle.getUpper().getFighters();
//		
//		for (Fighter fighter : all) {
//			
//			int typeId = fighter.getTypeId();
//			
//			if(config.contains(mapId, typeId)) {	//是否需要记录到计数器中
//				
//				City city = battle.getUnder().getCity();
//				
//				city.getUserCounterHistory().set(CounterKey.MISSION_DEMON_STAR, count, ":" + mapId + ":" + typeId);
//			}
//		}
//	}
//
//
//	private MissionStarManager getMissionStartManager(FightingWinEvent event) {
//
//		City city = getCity(event);
//
//		return MissionStarFactory.getMissionStarManager(city.getId());
//	}
//
//	private City getCity(FightingWinEvent event) {
//		MissionBattleImpl battle = (MissionBattleImpl) event.getSource();
//		PlayerCamp under = battle.getUnder();
//		City city = under.getCity();
//		return city;
//	}
//}
