package cn.mxz.listeners;

import cn.mxz.battle.Battle;
import cn.mxz.battle.MissionBattle;
import cn.mxz.city.City;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.mission.IMissionManager;
import cn.mxz.mission.star.MissionStarManager;
import cn.mxz.script.ScriptOld;

//如果战斗结束后, 需要做其余的处理, 到这个监听器里面加
public class AddMissionStar implements Listener<FightingWinEvent> {

	@Override
	public void onEvent(FightingWinEvent event) {
		
		Battle battle = event.getBattle();
		
		if (battle instanceof MissionBattle) {
			
			MissionBattle b = (MissionBattle) battle;
			
			int round = event.getRound();
			
			City city = event.getCity();
			
			IMissionManager mission = city.getMission();
			MissionStarManager starManager = mission.getStarManager();
			
			int star = ScriptOld.battle.calcStar(round, b.getHpStart(), b.getUnder());
			
			
			if (b.isBoss()) {
				if (b.isMain()) {
					starManager.addBossStar(b.getMapId(), star);
				} else {
					starManager.addBranchBossStar(b.getMapId(), star);
				}
			} else {
				starManager.addDemonStar(b.getMapId(), star);
			}
		}
		
	}
}
