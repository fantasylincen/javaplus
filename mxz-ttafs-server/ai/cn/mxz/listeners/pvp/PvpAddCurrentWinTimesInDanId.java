package cn.mxz.listeners.pvp;

import cn.mxz.city.City;
import cn.mxz.events.Listener;
import cn.mxz.events.pvp.PvpFightingWinEvent;
import cn.mxz.formation.PlayerCamp;
import cn.mxz.newpvp.PvpBattle;
import cn.mxz.newpvp.PvpPlayer;

/**
 * PVP 战斗胜利   增加一次当前段位胜利次数
 * @author 林岑
 */
public class PvpAddCurrentWinTimesInDanId implements Listener<PvpFightingWinEvent> {

	@Override
	public void onEvent(PvpFightingWinEvent e) {
		PvpBattle battle = e.getBattle();
		PlayerCamp u = battle.getUnderPlayerCamp();
		City c = u.getCity();
		
		PvpPlayer p = c.getNewPvpManager().getPlayer();
		p.setCurrentWinTimesInDanId(p.getCurrentWinTimesInDanId() + 1);
	}

}
