package cn.mxz.listeners;

import cn.mxz.MissionMapTemplet;
import cn.mxz.MissionMapTempletConfig;
import cn.mxz.battle.Battle;
import cn.mxz.battle.MissionBattle;
import cn.mxz.city.City;
import cn.mxz.events.Events;
import cn.mxz.events.FightingWinEvent;
import cn.mxz.events.Listener;
import cn.mxz.events.MissionFightingWinEvent;
import cn.mxz.mission.type.GuidePlayer;

/**
 * 派发 副本胜利事件
 * 
 * @author 林岑
 * 
 */
public class DispatchMissionWinEvent implements Listener<FightingWinEvent> {

	@Override
	public void onEvent(FightingWinEvent event) {
		Battle b = event.getBattle();

		if (b instanceof MissionBattle) {
			MissionBattle battle = (MissionBattle) b;

			City user = event.getCity();

			int mapId = battle.getMapId();
			MissionMapTemplet temp = MissionMapTempletConfig.get(mapId);
			GuidePlayer gp = new GuidePlayer(temp, user); // 引导玩家

			Events.getInstance()
					.dispatch(
							new MissionFightingWinEvent(battle, user, mapId, gp
									.isNew()));
		}
	}

}
